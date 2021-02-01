package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;

/**
 * 用户反馈Service接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisUserFeedbackService
{
    /**
     * 查询用户反馈
     *
     * @param id 用户反馈ID
     * @return 用户反馈
     */
    public BisUserFeedback selectBisUserFeedbackById(Long id);

    /**
     * 查询用户反馈列表
     *
     * @param bisUserFeedback 用户反馈
     * @return 用户反馈集合
     */
    public List<BisUserFeedback> selectBisUserFeedbackList(BisUserFeedback bisUserFeedback);

    /**
     * 新增用户反馈
     *
     * @param bisUserFeedback 用户反馈
     * @return 结果
     */
    public int insertBisUserFeedback(BisUserFeedback bisUserFeedback);

    /**
     * 修改用户反馈
     *
     * @param bisUserFeedback 用户反馈
     * @return 结果
     */
    public int updateBisUserFeedback(BisUserFeedback bisUserFeedback);

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的用户反馈ID
     * @return 结果
     */
    public int deleteBisUserFeedbackByIds(Long[] ids);

    /**
     * 删除用户反馈信息
     *
     * @param id 用户反馈ID
     * @return 结果
     */
    public int deleteBisUserFeedbackById(Long id);

    /**
     * 提交反馈
     *
     * @param bisFeedbackTemplateDetailVo 用户反馈信息vo
     * @return 结果
     */
    boolean submitFeedback(BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo);
}
