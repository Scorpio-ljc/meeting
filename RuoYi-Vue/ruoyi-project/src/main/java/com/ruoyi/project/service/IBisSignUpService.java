package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisSignUp;
import com.ruoyi.project.domain.vo.BisSignUpExcelVo;
import com.ruoyi.project.domain.vo.BisUserVo;

/**
 * 会议报名信息Service接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisSignUpService
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
     * 查询会议报名详情
     *
     * @param bisSignUp 报名信息
     * @return 结果
     */
     List<BisSignUpExcelVo> selectBisSignUpExcelVoList(BisSignUp bisSignUp);

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
     * 批量删除会议报名信息
     *
     * @param ids 需要删除的会议报名信息ID
     * @return 结果
     */
    public int deleteBisSignUpByIds(Long[] ids);

    /**
     * 删除会议报名信息信息
     *
     * @param id 会议报名信息ID
     * @return 结果
     */
    public int deleteBisSignUpById(Long id);

    /**
     * 报名会议
     *
     * @param bisUserVo 用户报名vo
     * @return 结果
     */
    boolean signUp(BisUserVo bisUserVo);

    /**
     * 审核报名信息
     *
     * @param bisSignUp 报名信息对象
     * @return 结果
     */
    boolean audit(BisSignUp bisSignUp);

    /**
     * 获取最新的用户信息
     *
     * @param uid 用户id
     * @return 结果
     */
    BisSignUp selectNewBisSignUpByUid(Long uid);

}
