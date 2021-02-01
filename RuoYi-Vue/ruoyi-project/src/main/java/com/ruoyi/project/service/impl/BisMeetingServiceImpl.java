package com.ruoyi.project.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.bean.UserContext;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.location.LocationUtils;
import com.ruoyi.common.utils.location.bean.LngLat;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.common.utils.wx.WxUtil;
import com.ruoyi.common.utils.wx.bean.WxQrCodeRequest;
import com.ruoyi.project.base.BusiContans;
import com.ruoyi.project.domain.BisSubject;
import com.ruoyi.project.domain.BisUser;
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.domain.vo.*;
import com.ruoyi.project.mapper.BisSubjectMapper;
import com.ruoyi.project.mapper.BisUserFeedbackMapper;
import com.ruoyi.project.mapper.BisUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisMeetingMapper;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.service.IBisMeetingService;

import javax.servlet.http.HttpServletResponse;

/**
 * 会议信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Slf4j
@Service
public class BisMeetingServiceImpl implements IBisMeetingService {
    @Autowired
    private BisMeetingMapper bisMeetingMapper;
    @Autowired
    private BisUserFeedbackMapper bisUserFeedbackMapper;
    @Autowired
    private BisUserMapper bisUserMapper;
    @Autowired
    private BisSubjectMapper bisSubjectMapper;
    @Autowired
    private WxUtil wxUtil;

    /**
     * 查询会议信息
     *
     * @param id 会议信息ID
     * @return 会议信息
     */
    @Override
    public BisMeeting selectBisMeetingById(Long id) {
        return bisMeetingMapper.selectBisMeetingById(id);
    }

    @Override
    public BisMeetingVo selectBisMeetingDetail(MeetingDetailQueryVo meetingDetailQueryVo) {
        BisMeetingVo bisMeetingVo = new BisMeetingVo();
        if (meetingDetailQueryVo.getMeetingId() == null) {
            bisMeetingVo.setNewUser(true);
            return bisMeetingVo;
        }
        Date currentDate = new Date();
        List<BisMeetingVo> bisMeetingVoList = bisMeetingMapper.selectBisMeetingListByUserId(new SignInQueryVo(meetingDetailQueryVo.getUserId(), meetingDetailQueryVo.getMeetingId(), null, getSignInDate()));
        bisMeetingVo = bisMeetingVoList.get(0);
        if ("1".equals(bisMeetingVo.getState()) && bisMeetingVo.getSignUpEndTime() != null && bisMeetingVo.getSignUpEndTime().compareTo(currentDate) < 0) {
            bisMeetingVo.setState("6");
            return bisMeetingVo;
        }
        LngLat from = new LngLat(meetingDetailQueryVo.getLng(), meetingDetailQueryVo.getLat());
        LngLat to = new LngLat(bisMeetingVo.getLng(), bisMeetingVo.getLat());
        double matrix = LocationUtils.matrix(from, to);
        if (matrix <= 1000) {
            bisMeetingVo.setSignInArea(true);
        }
        int i = bisUserFeedbackMapper.selectCountByUserIdAndMeetingId(meetingDetailQueryVo.getUserId(), meetingDetailQueryVo.getMeetingId());
        if (i > 0) {
            bisMeetingVo.setFeedback(true);
        }
        //设置会议已结束
        if (bisMeetingVo.getMeetingEndTime() != null && currentDate.compareTo(bisMeetingVo.getMeetingEndTime()) > 0) {
            bisMeetingVo.setState("4");
        }
        //设置会议未开始
        if ("2".equals(bisMeetingVo.getState()) && bisMeetingVo.getMeetingEndTime() != null && currentDate.compareTo(bisMeetingVo.getMeetingTime()) < 0) {
            bisMeetingVo.setState("5");
        }
        return bisMeetingVo;
    }

    private Date getSignInDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        Date date12 = DateUtil.date(calendar);
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        Date date17 = DateUtil.date(calendar);
        if (currentDate.compareTo(date12) > 0 && currentDate.compareTo(date17) < 0) {
            return date12;
        }
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        Date date22 = DateUtil.date(calendar);
        if (currentDate.compareTo(date17) > 0 && currentDate.compareTo(date22) < 0) {
            return date17;
        }
        if(currentDate.compareTo(date22) > 0){
            return date22;
        }
        return null;
    }

    /**
     * 查询会议信息列表
     *
     * @param bisMeeting 会议信息
     * @return 会议信息
     */
    @Override
    public List<BisMeeting> selectBisMeetingList(BisMeeting bisMeeting) {
        return bisMeetingMapper.selectBisMeetingList(bisMeeting);
    }

    @Override
    public List<BisMeetingVo> selectBisMeetingListByWxCode(String code, Long currentMeetingId) {

        BisUser byOpenid = bisUserMapper.selectBisUserById(UserContext.get().getUid());
        Date currentDate = new Date();
        if (byOpenid != null) {
            startPage();
            List<BisMeetingVo> bisMeetingVoList = bisMeetingMapper.selectBisMeetingListByUserId(new SignInQueryVo(byOpenid.getId(), null, currentMeetingId, getSignInDate()));
            bisMeetingVoList.forEach(a -> {
                if ("1".equals(a.getState()) && a.getSignUpEndTime() != null && a.getSignUpEndTime().compareTo(currentDate) < 0) {
                    a.setState("6");
                }else{
                    //设置会议已结束
                    if (a.getMeetingEndTime() != null && currentDate.compareTo(a.getMeetingEndTime()) > 0) {
                        a.setState("4");
                    }
                    //设置会议未开始
                    if ("2".equals(a.getState()) && a.getMeetingEndTime() != null && currentDate.compareTo(a.getMeetingTime()) < 0) {
                        a.setState("5");
                    }
                }

            });
            return bisMeetingVoList;
        }
        return null;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }


    /**
     * 新增会议信息
     *
     * @param bisMeeting 会议信息
     * @return 结果
     */
    @Override
    public int insertBisMeeting(BisMeeting bisMeeting) {
        bisMeeting.setCreateTime(DateUtils.getNowDate());
        return bisMeetingMapper.insertBisMeeting(bisMeeting);
    }

    /**
     * 修改会议信息
     *
     * @param bisMeeting 会议信息
     * @return 结果
     */
    @Override
    public int updateBisMeeting(BisMeeting bisMeeting) {
        bisMeeting.setUpdateTime(DateUtils.getNowDate());
        return bisMeetingMapper.updateBisMeeting(bisMeeting);
    }

    /**
     * 批量删除会议信息
     *
     * @param ids 需要删除的会议信息ID
     * @return 结果
     */
    @Override
    public int deleteBisMeetingByIds(Long[] ids) {
        return bisMeetingMapper.deleteBisMeetingByIds(ids);
    }

    /**
     * 删除会议信息信息
     *
     * @param id 会议信息ID
     * @return 结果
     */
    @Override
    public int deleteBisMeetingById(Long id) {
        return bisMeetingMapper.deleteBisMeetingById(id);
    }

    @Override
    public void getQrCode(Long id, HttpServletResponse httpServletResponse) {
        BisMeeting bisMeeting = bisMeetingMapper.selectBisMeetingById(id);
        if (bisMeeting == null) {
            throw new CustomException("未查询到此会议");
        }
        String process = bisMeeting.getProcess();
        if (StringUtils.isNotEmpty(process)) {
            WxQrCodeRequest wxQrCodeRequest = new WxQrCodeRequest(
                    String.valueOf(id)
                    , "pages/meeting/index"
                    , 0
                    , true
                    , null
                    , false
                    , httpServletResponse
            );
            wxQrCodeRequest.setHttpServletResponse(httpServletResponse);
            wxQrCodeRequest.setFileName(id + ".png");
            wxUtil.getQrcode(wxQrCodeRequest);
        }
    }

    @Override
    public void exportUserFeedback(BisMeeting bisMeeting, HttpServletResponse response) {
        //List<BisUserFeedbackVo> bisUserFeedbackVoList = bisUserFeedbackMapper.selectUserFeedbackVo(bisMeeting.getId());
        //Map<String, List<BisUserFeedbackVo>> collect = bisUserFeedbackVoList.stream().collect(Collectors.groupingBy(BisUserFeedbackVo::getSubject));
        List<UserFeedbackStatistic> userFeedbackStatisticList = new ArrayList<>();
        //Set<Map.Entry<String, List<BisUserFeedbackVo>>> entries = collect.entrySet();

//        entries.forEach(a -> {
//            UserFeedbackStatistic userFeedbackStatistic = new UserFeedbackStatistic();
//            userFeedbackStatistic.setSubjectTitle(a.getKey());
//
//            List<SubStatistic> subStatisticList = new ArrayList<>();
//            Map<String, List<BisUserFeedbackVo>> optionList = a.getValue().stream().collect(Collectors.groupingBy(BisUserFeedback::getValue));
//            optionList.forEach((key, value) -> {
//                SubStatistic subStatistic = new SubStatistic();
//                subStatistic.setOption(key);
//                subStatistic.setCount(value.size());
//                subStatisticList.add(subStatistic);
//            });
//            userFeedbackStatistic.setSubStatisticList(subStatisticList);
//            userFeedbackStatisticList.add(userFeedbackStatistic);
//        });
        BisSubject bisSubject = new BisSubject();
        BisMeeting dbBisMeeting = bisMeetingMapper.selectBisMeetingById(bisMeeting.getId());
        bisMeeting.setFeedbackCode(dbBisMeeting.getFeedbackCode());
        bisSubject.setFeedbackCode(bisMeeting.getFeedbackCode());
        List<BisSubjectVo> bisSubjectList = bisSubjectMapper.selectBisSubjectVoList(bisSubject);
        Map<Long, List<BisSubjectVo>> subjectList = bisSubjectList.stream().collect(Collectors.groupingBy(BisSubjectVo::getSubjectId));
        int[] max = {0};
        subjectList.forEach((key, value) -> {
            max[0] = Math.max(max[0], value.size());
            UserFeedbackStatistic userFeedbackStatistic = new UserFeedbackStatistic();
            List<SubStatistic> subStatisticList = new ArrayList<>();
            String subject = value.get(0).getSubject();
            userFeedbackStatistic.setSubjectTitle(subject);
            BisUserFeedback param = new BisUserFeedback();
            param.setMeetingId(bisMeeting.getId());
            param.setSubjectId(key);
            param.setMeetingId(bisMeeting.getId());
            param.setPicking("1");
            List<BisUserFeedback> userFeedbackVoList = bisUserFeedbackMapper.selectBisUserFeedbackList(param);
            value.forEach(a -> {
                SubStatistic subStatistic = new SubStatistic();
                subStatistic.setOption(a.getValue());
                long count = userFeedbackVoList.stream().filter(b -> a.getSubjectId().equals(b.getSubjectId()) && a.getValue().equals(b.getValue())).count();
                subStatistic.setCount(Integer.parseInt(String.valueOf(count)));
                subStatisticList.add(subStatistic);
            });
            userFeedbackStatistic.setSubStatisticList(subStatisticList);
            userFeedbackStatisticList.add(userFeedbackStatistic);
        });
        //导出excel表格
        List<Map<String, Object>> list = new ArrayList<>();
        userFeedbackStatisticList.forEach(a -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("问题", a.getSubjectTitle());
            for (int i = 1; i <= max[0]; i++) {
                map.put("选项" + i, i > a.getSubStatisticList().size() ? "" : a.getSubStatisticList().get(i - 1).getOption());
                map.put("人数" + i, i > a.getSubStatisticList().size() ? "" : a.getSubStatisticList().get(i - 1).getCount());
            }
            list.add(map);
        });
        try {
            ExcelUtil.downloadExcel(list, response);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("导出异常===>", e);
        }
    }

}
