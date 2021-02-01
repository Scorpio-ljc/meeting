package com.ruoyi.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.ruoyi.common.core.bean.UserInfor;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.GenUtil;
import com.ruoyi.common.utils.wx.WxUtil;
import com.ruoyi.project.domain.vo.BisUserVo;
import com.ruoyi.project.domain.vo.MiniLoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisUserMapper;
import com.ruoyi.project.domain.BisUser;
import com.ruoyi.project.service.IBisUserService;

/**
 * 用户Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
public class BisUserServiceImpl implements IBisUserService
{
    @Autowired
    private BisUserMapper bisUserMapper;
    @Autowired
    private WxUtil wxUtil;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询用户
     *
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public BisUser selectBisUserById(Long id)
    {
        return bisUserMapper.selectBisUserById(id);
    }

    /**
     * 查询用户列表
     *
     * @param bisUser 用户
     * @return 用户
     */
    @Override
    public List<BisUser> selectBisUserList(BisUser bisUser)
    {
        return bisUserMapper.selectBisUserList(bisUser);
    }

    /**
     * 新增用户
     *
     * @param bisUser 用户
     * @return 结果
     */
    @Override
    public int insertBisUser(BisUser bisUser)
    {
        bisUser.setCreateTime(DateUtils.getNowDate());
        return bisUserMapper.insertBisUser(bisUser);
    }

    /**
     * 修改用户
     *
     * @param bisUser 用户
     * @return 结果
     */
    @Override
    public int updateBisUser(BisUser bisUser)
    {
        bisUser.setUpdateTime(DateUtils.getNowDate());
        return bisUserMapper.updateBisUser(bisUser);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteBisUserByIds(Long[] ids)
    {
        return bisUserMapper.deleteBisUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteBisUserById(Long id)
    {
        return bisUserMapper.deleteBisUserById(id);
    }

    @Override
    public BisUserVo wxLogin(MiniLoginVo miniLoginVo) {
        BisUserVo bisUserVo = new BisUserVo();
        WxMaJscode2SessionResult seeionResult = wxUtil.getSessionResult(miniLoginVo.getLoginCode());
        String openid = Objects.requireNonNull(seeionResult).getOpenid();
        BisUser byOpenid = bisUserMapper.getByOpenid(openid);
        if(byOpenid == null){
            byOpenid = new BisUser();
            byOpenid.setOpenid(openid);
            byOpenid.setCreateTime(new Date());
            bisUserMapper.insertBisUser(byOpenid);
        }
        BeanUtils.copyProperties(byOpenid,bisUserVo);
        String token = GenUtil.generateToken();
        bisUserVo.setToken(token);
        UserInfor userInfor = new UserInfor();
        BeanUtils.copyProperties(bisUserVo,userInfor);
        userInfor.setUid(byOpenid.getId());
        redisCache.setCacheObject(token,userInfor);
        return bisUserVo;
    }


}
