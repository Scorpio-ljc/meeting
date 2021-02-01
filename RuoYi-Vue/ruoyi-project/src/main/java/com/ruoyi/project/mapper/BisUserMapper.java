package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisUser;

/**
 * 用户Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisUserMapper
{
    /**
     * 查询用户
     *
     * @param id 用户ID
     * @return 用户
     */
    public BisUser selectBisUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param bisUser 用户
     * @return 用户集合
     */
    public List<BisUser> selectBisUserList(BisUser bisUser);

    /**
     * 新增用户
     *
     * @param bisUser 用户
     * @return 结果
     */
    public int insertBisUser(BisUser bisUser);

    /**
     * 修改用户
     *
     * @param bisUser 用户
     * @return 结果
     */
    public int updateBisUser(BisUser bisUser);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    public int deleteBisUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisUserByIds(Long[] ids);

    /**
     * 根据openid获取用户信息
     *
     * @param openid 微信openid
     * @return
     */
    BisUser getByOpenid(String openid);
}
