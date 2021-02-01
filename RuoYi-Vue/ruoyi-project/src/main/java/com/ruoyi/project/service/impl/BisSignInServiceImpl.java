package com.ruoyi.project.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.location.LocationUtils;
import com.ruoyi.common.utils.location.bean.LngLat;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.vo.BisSignInVo;
import com.ruoyi.project.domain.vo.SignInResponseVo;
import com.ruoyi.project.domain.vo.SignInVo;
import com.ruoyi.project.mapper.BisMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisSignInMapper;
import com.ruoyi.project.domain.BisSignIn;
import com.ruoyi.project.service.IBisSignInService;

/**
 * 签到信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
public class BisSignInServiceImpl implements IBisSignInService {
    @Autowired
    private BisSignInMapper bisSignInMapper;
    @Autowired
    private BisMeetingMapper bisMeetingMapper;

    /**
     * 查询签到信息
     *
     * @param id 签到信息ID
     * @return 签到信息
     */
    @Override
    public BisSignIn selectBisSignInById(Long id) {
        return bisSignInMapper.selectBisSignInById(id);
    }

    /**
     * 查询签到信息列表
     *
     * @param bisSignInVo 签到详情信息
     * @return 签到信息
     */
    @Override
    public List<BisSignInVo> selectBisSignInList(BisSignInVo bisSignInVo) {
        return bisSignInMapper.selectBisSignInVoList(bisSignInVo);
    }

    /**
     * 新增签到信息
     *
     * @param bisSignIn 签到信息
     * @return 结果
     */
    @Override
    public int insertBisSignIn(BisSignIn bisSignIn) {
        bisSignIn.setCreateTime(DateUtils.getNowDate());
        return bisSignInMapper.insertBisSignIn(bisSignIn);
    }

    /**
     * 修改签到信息
     *
     * @param bisSignIn 签到信息
     * @return 结果
     */
    @Override
    public int updateBisSignIn(BisSignIn bisSignIn) {
        bisSignIn.setUpdateTime(DateUtils.getNowDate());
        return bisSignInMapper.updateBisSignIn(bisSignIn);
    }

    /**
     * 批量删除签到信息
     *
     * @param ids 需要删除的签到信息ID
     * @return 结果
     */
    @Override
    public int deleteBisSignInByIds(Long[] ids) {
        return bisSignInMapper.deleteBisSignInByIds(ids);
    }

    /**
     * 删除签到信息信息
     *
     * @param id 签到信息ID
     * @return 结果
     */
    @Override
    public int deleteBisSignInById(Long id) {
        return bisSignInMapper.deleteBisSignInById(id);
    }

    @Override
    public SignInResponseVo signIn(SignInVo signInVo) {
        BisMeeting bisMeeting = bisMeetingMapper.selectBisMeetingById(signInVo.getMeetingId());
        if (bisMeeting == null) {
            throw new CustomException("未查询到该会议");
        }
        Date currentDate = new Date();
        SignInResponseVo signInResponseVo = new SignInResponseVo("1", currentDate, "1");
        //判断是否已经打卡
        BisSignIn bisSignIn = bisSignInMapper.selectByMeetingIdAndUid(signInVo.getMeetingId(), signInVo.getUid());
        if (bisSignIn != null) {
            signInResponseVo.setSignState("1");
            signInResponseVo.setSignTime(bisSignIn.getCreateTime());
            return signInResponseVo;
        }
        LngLat from = new LngLat(signInVo.getLng(), signInVo.getLat());
        LngLat to = new LngLat(bisMeeting.getLng(), bisMeeting.getLat());
        double matrix = LocationUtils.matrix(from, to);
        if (matrix > 1000) {
            signInResponseVo.setState("2");
            return signInResponseVo;
        }
        //判断是否已经结束
        if (currentDate.compareTo(bisMeeting.getMeetingEndTime()) > 0){
            signInResponseVo.setState("4");
            return signInResponseVo;
        }
        //判断当前时间是否在会议时间内
        if (bisMeeting.getMeetingTime() != null && bisMeeting.getEndTime() != null &&
                (currentDate.compareTo(bisMeeting.getMeetingTime()) < 0 || currentDate.compareTo(bisMeeting.getMeetingEndTime()) > 0)) {
            signInResponseVo.setState("3");
            return signInResponseVo;
        }
        //保存打卡信息
        bisSignIn = new BisSignIn();
        bisSignIn.setMeetingId(signInVo.getMeetingId());
        bisSignIn.setUid(signInVo.getUid());
        bisSignIn.setCreateTime(currentDate);
        bisSignInMapper.insertBisSignIn(bisSignIn);

        return signInResponseVo;
    }


}
