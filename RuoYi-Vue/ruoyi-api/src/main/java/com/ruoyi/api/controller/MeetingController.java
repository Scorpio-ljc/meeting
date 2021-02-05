package com.ruoyi.api.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.bean.UserContext;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.domain.vo.*;
import com.ruoyi.project.service.IBisMeetingService;
import com.ruoyi.project.service.IBisSignInService;
import com.ruoyi.project.service.IBisSignUpService;
import com.ruoyi.project.service.IBisUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会议controller
 *
 * @author liujc
 * @create 2020-12-14 21:56:28
 **/
@RestController
@RequestMapping("/api/mini/meeting")
public class MeetingController extends BaseController {
    @Autowired
    private IBisSignUpService bisSignUpService;
    @Autowired
    private IBisSignInService bisSignInService;
    @Autowired
    private IBisMeetingService bisMeetingService;

    /**
     * 会议报名
     *
     * @param bisUserVo 用户vo
     * @return 结果
     */
    @RepeatSubmit
    @PostMapping("/signUp")
    public AjaxResult signUp(@RequestBody BisUserVo bisUserVo){
        bisUserVo.setUid(UserContext.get().getUid());
        boolean b = bisSignUpService.signUp(bisUserVo);
        return b ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 签到
     *
     * @param signInVo 签到vo
     * @return 结果
     */
    @RepeatSubmit
    @PostMapping("/signIn")
    public AjaxResult signIn(@RequestBody SignInVo signInVo){
        signInVo.setUid(UserContext.get().getUid());
        SignInResponseVo signInResponseVo = bisSignInService.signIn(signInVo);
        return AjaxResult.success(signInResponseVo);
    }

    /**
     * 用户会议列表
     *
     * @param bisUserVo 用户id
     * @return 结果
     */
    @GetMapping("/queryUserMeetingPage")
    public TableDataInfo userMeetingPage(BisUserVo bisUserVo){
        List<BisMeetingVo> bisMeetingVoList = bisMeetingService.selectBisMeetingListByWxCode(bisUserVo.getCode(),bisUserVo.getCurrentMeetingId());
        return getDataTable(bisMeetingVoList);
    }
    /**
     * 获取会议信息详细信息
     */
    @ApiOperation("获取会议信息详细信息根据id")
    @GetMapping("/queryMeetingDetail")
    public AjaxResult queryMeetingDetail(MeetingDetailQueryVo meetingDetailQueryVo)
    {
        meetingDetailQueryVo.setUserId(UserContext.get().getUid());
        return AjaxResult.success(bisMeetingService.selectBisMeetingDetail(meetingDetailQueryVo));
    }
}
