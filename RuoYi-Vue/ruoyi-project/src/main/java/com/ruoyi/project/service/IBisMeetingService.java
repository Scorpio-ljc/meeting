package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.vo.BisMeetingVo;
import com.ruoyi.project.domain.vo.MeetingDetailQueryVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 会议信息Service接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisMeetingService
{
    /**
     * 查询会议信息
     *
     * @param id 会议信息ID
     * @return 会议信息
     */
    public BisMeeting selectBisMeetingById(Long id);

    /**
     * 查询会议详情
     *
     * @param meetingDetailQueryVo 查询详情参数
     * @return 结果
     */
    BisMeetingVo selectBisMeetingDetail(MeetingDetailQueryVo meetingDetailQueryVo);

    /**
     * 查询会议信息列表
     *
     * @param bisMeeting 会议信息
     * @return 会议信息集合
     */
    public List<BisMeeting> selectBisMeetingList(BisMeeting bisMeeting);

    /**
     * 根据用户id获取会议列表
     *
     * @param code 用户code
     * @return 会议列表
     */
    List<BisMeetingVo> selectBisMeetingListByWxCode(String code,Long currentMeetingId);

    /**
     * 新增会议信息
     *
     * @param bisMeeting 会议信息
     * @return 结果
     */
    public int insertBisMeeting(BisMeeting bisMeeting);

    /**
     * 修改会议信息
     *
     * @param bisMeeting 会议信息
     * @return 结果
     */
    public int updateBisMeeting(BisMeeting bisMeeting);

    /**
     * 批量删除会议信息
     *
     * @param ids 需要删除的会议信息ID
     * @return 结果
     */
    public int deleteBisMeetingByIds(Long[] ids);

    /**
     * 删除会议信息信息
     *
     * @param id 会议信息ID
     * @return 结果
     */
    public int deleteBisMeetingById(Long id);

    /**
     * 根据会议id获取会议二维码
     *
     * @param id
     */
    void getQrCode(Long id, HttpServletResponse httpServletResponse);

    void exportUserFeedback(BisMeeting bisMeeting,HttpServletResponse response);
}
