package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisSignIn;
import com.ruoyi.project.domain.vo.BisSignInVo;
import com.ruoyi.project.domain.vo.SignInResponseVo;
import com.ruoyi.project.domain.vo.SignInVo;

/**
 * 签到信息Service接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisSignInService
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
     * @param bisSignInVo 签到信息
     * @return 签到信息集合
     */
    public List<BisSignInVo> selectBisSignInList(BisSignInVo bisSignInVo);

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
     * 批量删除签到信息
     *
     * @param ids 需要删除的签到信息ID
     * @return 结果
     */
    public int deleteBisSignInByIds(Long[] ids);

    /**
     * 删除签到信息信息
     *
     * @param id 签到信息ID
     * @return 结果
     */
    public int deleteBisSignInById(Long id);

    /**
     * 用户签到
     *
     * @param signInVo
     * @return
     */
    SignInResponseVo signIn(SignInVo signInVo);
}
