package com.ruoyi.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.wx.WxUtil;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.BisSignUp;
import com.ruoyi.project.mapper.BisMeetingMapper;
import com.ruoyi.project.mapper.BisSignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisMeetingPublishMapper;
import com.ruoyi.project.domain.BisMeetingPublish;
import com.ruoyi.project.service.IBisMeetingPublishService;

/**
 * 会议信息发布Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-19
 */
@Service
public class BisMeetingPublishServiceImpl implements IBisMeetingPublishService
{
    @Value("${wx.miniapp.mq.psTemplateId}")
    private String psTemplateId;
    @Value("${wx.miniapp.mq.miniState}")
    private String miniState;
    @Value("${wx.miniapp.mq.page}")
    private String miniPage;
    @Autowired
    private BisMeetingPublishMapper bisMeetingPublishMapper;
    @Autowired
    private BisMeetingMapper bisMeetingMapper;
    @Autowired
    private BisSignUpMapper bisSignUpMapper;
    @Autowired
    private WxUtil wxUtil;

    /**
     * 查询会议信息发布
     *
     * @param id 会议信息发布ID
     * @return 会议信息发布
     */
    @Override
    public BisMeetingPublish selectBisMeetingPublishById(Long id)
    {
        return bisMeetingPublishMapper.selectBisMeetingPublishById(id);
    }

    /**
     * 查询会议信息发布列表
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 会议信息发布
     */
    @Override
    public List<BisMeetingPublish> selectBisMeetingPublishList(BisMeetingPublish bisMeetingPublish)
    {
        return bisMeetingPublishMapper.selectBisMeetingPublishList(bisMeetingPublish);
    }

    /**
     * 新增会议信息发布
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 结果
     */
    @Async
    @Override
    public void insertBisMeetingPublish(BisMeetingPublish bisMeetingPublish)
    {
        bisMeetingPublish.setCreateTime(DateUtils.getNowDate());
        BisMeeting bisMeeting = bisMeetingMapper.selectBisMeetingById(bisMeetingPublish.getMeetingId());
        BisSignUp param = new BisSignUp();
        param.setMeetingId(bisMeetingPublish.getMeetingId());
        List<BisSignUp> bisSignUpList = bisSignUpMapper.selectBisSignUpListDetail(param);
        WxMaSubscribeMessage wxMaSubscribeMessage = new WxMaSubscribeMessage();
        wxMaSubscribeMessage.setTemplateId(psTemplateId);
        List<WxMaSubscribeMessage.Data> dataList = new ArrayList<>();
        WxMaSubscribeMessage.Data data1 = new WxMaSubscribeMessage.Data();
        data1.setName("thing11");
        data1.setValue(bisMeeting.getMeetingTitle());
        dataList.add(data1);
        WxMaSubscribeMessage.Data data2 = new WxMaSubscribeMessage.Data();
        data2.setName("thing16");
        data2.setValue(bisMeetingPublish.getTitle());
        dataList.add(data2);
        wxMaSubscribeMessage.setData(dataList);
        wxMaSubscribeMessage.setMiniprogramState(miniState);
        bisMeetingPublishMapper.insertBisMeetingPublish(bisMeetingPublish);
        wxMaSubscribeMessage.setPage(miniPage + "?id=" + bisMeetingPublish.getId());
        for (BisSignUp bisSignUp: bisSignUpList) {
            wxMaSubscribeMessage.setToUser(bisSignUp.getOpenid());
            wxUtil.sendSubMess(wxMaSubscribeMessage);
        }
    }

    /**
     * 修改会议信息发布
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 结果
     */
    @Override
    public int updateBisMeetingPublish(BisMeetingPublish bisMeetingPublish)
    {
        bisMeetingPublish.setUpdateTime(DateUtils.getNowDate());
        return bisMeetingPublishMapper.updateBisMeetingPublish(bisMeetingPublish);
    }

    /**
     * 批量删除会议信息发布
     *
     * @param ids 需要删除的会议信息发布ID
     * @return 结果
     */
    @Override
    public int deleteBisMeetingPublishByIds(Long[] ids)
    {
        return bisMeetingPublishMapper.deleteBisMeetingPublishByIds(ids);
    }

    /**
     * 删除会议信息发布信息
     *
     * @param id 会议信息发布ID
     * @return 结果
     */
    @Override
    public int deleteBisMeetingPublishById(Long id)
    {
        return bisMeetingPublishMapper.deleteBisMeetingPublishById(id);
    }
}
