package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisSignIn;
import com.ruoyi.project.domain.vo.BisSignInVo;
import org.apache.ibatis.annotations.Param;

/**
 * 签到信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisSignInMapper
{
    /**
     * 查询签到信息
     *
     * @param id 签到信息ID
     * @return 签到信息
     */
    public BisSignIn selectBisSignInById(Long id);

    /**
     * 查询签到信息列表
     *
     * @param bisSignIn 签到信息
     * @return 签到信息集合
     */
    public List<BisSignIn> selectBisSignInList(BisSignIn bisSignIn);

    /**
     * 查询签到详情列表
     *
     * @param bisSignInVo 签到详情
     * @return 签到详情集合
     */
    List<BisSignInVo> selectBisSignInVoList(BisSignInVo bisSignInVo);

    /**
     * 新增签到信息
     *
     * @param bisSignIn 签到信息
     * @return 结果
     */
    public int insertBisSignIn(BisSignIn bisSignIn);

    /**
     * 修改签到信息
     *
     * @param bisSignIn 签到信息
     * @return 结果
     */
    public int updateBisSignIn(BisSignIn bisSignIn);

    /**
     * 删除签到信息
     *
     * @param id 签到信息ID
     * @return 结果
     */
    public int deleteBisSignInById(Long id);

    /**
     * 批量删除签到信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisSignInByIds(Long[] ids);

    BisSignIn selectByMeetingIdAndUid(@Param("meetingId") Long meetingId, @Param("uid") Long uid);
}
