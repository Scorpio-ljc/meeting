package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.domain.vo.BisUserFeedbackVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户反馈Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisUserFeedbackMapper
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
     * 删除用户反馈
     *
     * @param id 用户反馈ID
     * @return 结果
     */
    public int deleteBisUserFeedbackById(Long id);

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisUserFeedbackByIds(Long[] ids);

    List<BisUserFeedbackVo> selectUserFeedbackVo(long meetingId);

    int selectCountByUserIdAndMeetingId(@Param("userId") Long userId, @Param("meetingId") Long meetingId);

}
