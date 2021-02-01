package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.vo.BisMeetingVo;
import com.ruoyi.project.domain.vo.SignInQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 会议信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisMeetingMapper
{
    /**
     * 查询会议信息
     *
     * @param id 会议信息ID
     * @return 会议信息
     */
    public BisMeeting selectBisMeetingById(Long id);

    /**
     * 查询会议信息列表
     *
     * @param bisMeeting 会议信息
     * @return 会议信息集合
     */
    public List<BisMeeting> selectBisMeetingList(BisMeeting bisMeeting);

    /**
     * 根据用户id查询会议详情列表
     *
     * @param signInQueryVo 签到查询对象
     * @return 结果
     */
    List<BisMeetingVo> selectBisMeetingListByUserId(SignInQueryVo signInQueryVo);

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
     * 删除会议信息
     *
     * @param id 会议信息ID
     * @return 结果
     */
    public int deleteBisMeetingById(Long id);

    /**
     * 批量删除会议信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisMeetingByIds(Long[] ids);
}
