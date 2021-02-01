package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户反馈对象 bis_user_feedback
 * 
 * @author ruoyi
 * @date 2020-12-11
 */
public class BisUserFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 反馈模版code */
    @Excel(name = "反馈模版code")
    private String feedbackCode;

    /** 题目id */
    @Excel(name = "题目id")
    private Long subjectId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uid;

    /** 会议id */
    @Excel(name = "会议id")
    private Long meetingId;

    /** 选项 */
    @Excel(name = "选项")
    private String key;

    /** 值 */
    @Excel(name = "值")
    private String value;

    /** 0-未选中 1-选中 */
    @Excel(name = "0-未选中 1-选中")
    private String picking;

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
    public void setSubjectId(Long subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() 
    {
        return subjectId;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setMeetingId(Long meetingId) 
    {
        this.meetingId = meetingId;
    }

    public Long getMeetingId() 
    {
        return meetingId;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setPicking(String picking) 
    {
        this.picking = picking;
    }

    public String getPicking() 
    {
        return picking;
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
            .append("subjectId", getSubjectId())
            .append("uid", getUid())
            .append("meetingId", getMeetingId())
            .append("key", getKey())
            .append("value", getValue())
            .append("picking", getPicking())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
