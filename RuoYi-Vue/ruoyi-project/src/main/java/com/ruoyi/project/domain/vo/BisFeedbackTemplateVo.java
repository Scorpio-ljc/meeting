package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liujc
 * @create 2020-12-14 18:03:05
 **/
@Data
public class BisFeedbackTemplateVo {
    /**
     * 反馈模版编码
     */
    private String feedbackCode;
    /**
     * 题目名称
     */
    private String subject;
    /**
     * 选项
     */
    private List<BisSubjectVo> optionList;


}
