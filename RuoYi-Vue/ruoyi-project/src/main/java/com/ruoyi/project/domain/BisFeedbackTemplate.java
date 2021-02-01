package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 反馈模版对象 bis_feedback_template
 * 
 * @author ruoyi
 * @date 2020-12-11
 */
public class BisFeedbackTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 反馈模版code */
    @Excel(name = "反馈模版code")
    private String feedbackCode;

    /** 题目 */
    @Excel(name = "题目")
    private String subject;

    /** 题目类型（1-选择题 2-简答题） */
    @Excel(name = "题目类型", readConverterExp = "1=-选择题,2=-简答题")
    private String subjectType;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeedbackCode(String feedbackCode) 
    {
        this.feedbackCode = feedbackCode;
    }

    public String getFeedbackCode() 
    {
        return feedbackCode;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setSubjectType(String subjectType) 
    {
        this.subjectType = subjectType;
    }

    public String getSubjectType() 
    {
        return subjectType;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feedbackCode", getFeedbackCode())
            .append("subject", getSubject())
            .append("subjectType", getSubjectType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
