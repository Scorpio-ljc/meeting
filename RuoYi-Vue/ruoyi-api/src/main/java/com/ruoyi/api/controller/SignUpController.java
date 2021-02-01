package com.ruoyi.api.controller;

import com.ruoyi.common.core.bean.UserContext;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.domain.vo.MeetingDetailQueryVo;
import com.ruoyi.project.service.IBisSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujc
 * @create 2021-01-21 10:09:02
 **/
@RestController
@RequestMapping("/api/mini/siginUp")
public class SignUpController {
    @Autowired
    private IBisSignUpService bisSignUpService;
    @GetMapping("/querySignUpDetail")
    public AjaxResult querySignUpDetail()
    {
        return AjaxResult.success(bisSignUpService.selectNewBisSignUpByUid(UserContext.get().getUid()));
    }
}
