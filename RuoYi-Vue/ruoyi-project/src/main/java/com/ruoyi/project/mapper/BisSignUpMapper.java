package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisSignUp;
import com.ruoyi.project.domain.vo.BisSignUpExcelVo;

/**
 * 会议报名信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisSignUpMapper
{
    /**
     * 查询会议报名信息
     *
     * @param id 会议报名信息ID
     * @return 会议报名信息
     */
    public BisSignUp selectBisSignUpById(Long id);

    /**
     * 查询会议报名信息列表
     *
     * @param bisSignUp 会议报名信息
     * @return 会议报名信息集合
     */
    public List<BisSignUp> selectBisSignUpList(BisSignUp bisSignUp);

    /**
     * 查询报名导出列表
     *
     * @param bisSignUp 报名信息
     * @return 结果
     */
    List<BisSignUpExcelVo> selectBisSignUpExcelList(BisSignUp bisSignUp);

    /**
     * 查询报名详情lieb
     * @param bisSignUp 报名信息
     * @return 结果
     */
    List<BisSignUp> selectBisSignUpListDetail(BisSignUp bisSignUp);

    /**
     * 新增会议报名信息
     *
     * @param bisSignUp 会议报名信息
     * @return 结果
     */
    public int insertBisSignUp(BisSignUp bisSignUp);

    /**
     * 修改会议报名信息
     *
     * @param bisSignUp 会议报名信息
     * @return 结果
     */
    public int updateBisSignUp(BisSignUp bisSignUp);

    /**
     * 删除会议报名信息
     *
     * @param id 会议报名信息ID
     * @return 结果
     */
    public int deleteBisSignUpById(Long id);

    /**
     * 批量删除会议报名信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisSignUpByIds(Long[] ids);

    BisSignUp selectNewBisSignUpByUid(Long uid);
}
