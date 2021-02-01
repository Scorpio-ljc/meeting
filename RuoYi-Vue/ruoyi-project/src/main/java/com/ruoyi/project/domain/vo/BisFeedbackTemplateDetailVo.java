package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liujc
 * @create 2020-12-14 19:20:46
 **/
@Data
public class BisFeedbackTemplateDetailVo {
    /**
     * 会议id
     */
    private Long meetingId;
    /**
     * 反馈模版编码
     */
    private String feedbackCode;
    /**
     * 反馈模版名称
     */
    private String templateName;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 题列表
     */
    private List<BisFeedbackTemplateVo> list;
}
