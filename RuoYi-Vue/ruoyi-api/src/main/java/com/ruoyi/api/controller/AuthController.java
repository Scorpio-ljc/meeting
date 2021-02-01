package com.ruoyi.api.controller;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.vo.BisUserVo;
import com.ruoyi.project.domain.vo.MiniLoginVo;
import com.ruoyi.project.service.IBisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liujc
 * @create 2020-12-14 20:56:20
 **/
@RestController
@RequestMapping("/api/mini/auth")
public class AuthController extends BaseController {
    @Autowired
    private IBisUserService bisUserService;
    @AnonymousAccess
    @PostMapping("/login")
    public AjaxResult login(@RequestBody MiniLoginVo miniLoginVo){
        if (StringUtils.isEmpty(miniLoginVo.getLoginCode())){
            return AjaxResult.error("参数异常");
        }
        return AjaxResult.success(bisUserService.wxLogin(miniLoginVo));
    }

}
