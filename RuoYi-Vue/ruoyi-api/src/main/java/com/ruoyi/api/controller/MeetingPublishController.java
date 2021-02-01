package com.ruoyi.api.controller;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.service.IBisMeetingPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujc
 * @create 2020-12-19 14:47:35
 **/
@RestController
@RequestMapping("/api/mini/meetingPublish")
public class MeetingPublishController {
    @Autowired
    private IBisMeetingPublishService bisMeetingPublishService;
    /**
     * 获取会议信息发布详细信息
     */
    @GetMapping(value = "/queryMeetingPublishById")
    @AnonymousAccess
    public AjaxResult queryMeetingPublishById(Long id)
    {
        return AjaxResult.success(bisMeetingPublishService.selectBisMeetingPublishById(id));
    }
}
