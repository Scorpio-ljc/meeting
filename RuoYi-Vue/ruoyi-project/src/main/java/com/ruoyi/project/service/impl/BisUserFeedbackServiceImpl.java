package com.ruoyi.project.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisUserFeedbackMapper;
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.service.IBisUserFeedbackService;

/**
 * 用户反馈Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
public class BisUserFeedbackServiceImpl implements IBisUserFeedbackService
{
    @Autowired
    private BisUserFeedbackMapper bisUserFeedbackMapper;

    /**
     * 查询用户反馈
     *
     * @param id 用户反馈ID
     * @return 用户反馈
     */
    @Override
    public BisUserFeedback selectBisUserFeedbackById(Long id)
    {
        return bisUserFeedbackMapper.selectBisUserFeedbackById(id);
    }

    /**
     * 查询用户反馈列表
     *
     * @param bisUserFeedback 用户反馈
     * @return 用户反馈
     */
    @Override
    public List<BisUserFeedback> selectBisUserFeedbackList(BisUserFeedback bisUserFeedback)
    {
        return bisUserFeedbackMapper.selectBisUserFeedbackList(bisUserFeedback);
    }

    /**
     * 新增用户反馈
     *
     * @param bisUserFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int insertBisUserFeedback(BisUserFeedback bisUserFeedback)
    {
        bisUserFeedback.setCreateTime(DateUtils.getNowDate());
        return bisUserFeedbackMapper.insertBisUserFeedback(bisUserFeedback);
    }

    /**
     * 修改用户反馈
     *
     * @param bisUserFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int updateBisUserFeedback(BisUserFeedback bisUserFeedback)
    {
        bisUserFeedback.setUpdateTime(DateUtils.getNowDate());
        return bisUserFeedbackMapper.updateBisUserFeedback(bisUserFeedback);
    }

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的用户反馈ID
     * @return 结果
     */
    @Override
    public int deleteBisUserFeedbackByIds(Long[] ids)
    {
        return bisUserFeedbackMapper.deleteBisUserFeedbackByIds(ids);
    }

    /**
     * 删除用户反馈信息
     *
     * @param id 用户反馈ID
     * @return 结果
     */
    @Override
    public int deleteBisUserFeedbackById(Long id)
    {
        return bisUserFeedbackMapper.deleteBisUserFeedbackById(id);
    }

    @Override
    public boolean submitFeedback(BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo) {
        Date currentDate = new Date();
        bisFeedbackTemplateDetailVo.getList().forEach(a->{
            a.getOptionList().forEach(b->{
                BisUserFeedback bisUserFeedback = new BisUserFeedback();
                bisUserFeedback.setFeedbackCode(bisFeedbackTemplateDetailVo.getFeedbackCode());
                bisUserFeedback.setMeetingId(bisFeedbackTemplateDetailVo.getMeetingId());
                bisUserFeedback.setKey(b.getKey());
                bisUserFeedback.setValue(b.getValue());
                bisUserFeedback.setPicking(b.getPicking());
                bisUserFeedback.setSubjectId(b.getSubjectId());
                bisUserFeedback.setUid(bisFeedbackTemplateDetailVo.getUid());
                bisUserFeedback.setCreateTime(currentDate);
                bisUserFeedbackMapper.insertBisUserFeedback(bisUserFeedback);
            });
        });
        return true;
    }
}
