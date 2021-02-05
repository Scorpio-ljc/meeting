package com.ruoyi.web.controller.project;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liujc
 * @create 2020-12-14 14:22:41
 **/
@Api("登录")
@RestController
@RequestMapping("/api/web/auth")
public class BisAuthController extends BaseController {
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public AjaxResult loginNew(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.loginBase(loginBody.getUsername(), loginBody.getPassword());

        Map<String,String> map = new HashMap<>();
        map.put(Constants.TOKEN,token);
        map.put(Constants.ROLE_KEY,Constants.ADMIN.equals(loginBody.getUsername()) ? "1" : "2");
        return AjaxResult.success(map);
    }
    @ApiOperation("新增后端普通管理员")
    @RepeatSubmit
    @PostMapping("/addUser")
    public AjaxResult addUser(@RequestBody SysUser user){
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            return AjaxResult.error("参数异常");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }
    @ApiOperation("删除普通管理员")
    @PostMapping("/deleteUser")
    public AjaxResult deleteUser(@RequestBody SysUser sysUser){
        Long userId = sysUser.getUserId();
        if(userId != null){
            if(userId == 1){
                return AjaxResult.error("这是超级管理员，请不要删除");
            }
            int i = userService.deleteUserById(sysUser.getUserId());
            return i > 0 ? AjaxResult.success() : AjaxResult.error();
        }
        return AjaxResult.error();
    }
    /**
     * 获取用户列表
     */
    @GetMapping("/queryUserList")
    public TableDataInfo queryUserList(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
