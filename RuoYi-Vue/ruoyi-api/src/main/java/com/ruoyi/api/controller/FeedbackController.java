package com.ruoyi.api.controller;

import com.ruoyi.common.core.bean.UserContext;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import com.ruoyi.project.service.IBisFeedbackTemplateService;
import com.ruoyi.project.service.IBisMeetingService;
import com.ruoyi.project.service.IBisUserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liujc
 * @create 2020-12-14 23:39:56
 **/
@RestController
@RequestMapping("/api/mini/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private IBisMeetingService bisMeetingService;
    @Autowired
    private IBisFeedbackTemplateService bisFeedbackTemplateService;
    @Autowired
    private IBisUserFeedbackService bisUserFeedbackService;

    /**
     * 获取会议的反馈模版
     *
     * @param meetingId 会议id
     * @return 结果
     */
    @GetMapping("/queryFeedbackById")
    public AjaxResult queryFeedbackById(Long meetingId){
        Long uid = UserContext.get().getUid();
        BisMeeting bisMeeting = bisMeetingService.selectBisMeetingById(meetingId);
        if(bisMeeting != null){
            BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo = bisFeedbackTemplateService.selectBisFeedbackTemplateByFeedbackCode(meetingId,bisMeeting.getFeedbackCode(),uid);
            return AjaxResult.success(bisFeedbackTemplateDetailVo);
        }else {
            return AjaxResult.error("未查询到会议");
        }
    }

    /**
     * 提交反馈
     *
     * @param bisFeedbackTemplateDetailVo  反馈对象
     * @return
     */
    @PostMapping("/submitFeedback")
    public AjaxResult submitFeedback(@RequestBody BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo){
        bisFeedbackTemplateDetailVo.setUid(UserContext.get().getUid());
        boolean b = bisUserFeedbackService.submitFeedback(bisFeedbackTemplateDetailVo);
        return b ? AjaxResult.success() : AjaxResult.error();
    }
}
