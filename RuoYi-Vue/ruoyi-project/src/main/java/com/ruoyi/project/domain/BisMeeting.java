package com.ruoyi.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会议信息对象 bis_meeting
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public class BisMeeting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 会议标题 */
    @Excel(name = "会议标题")
    private String meetingTitle;

    /** 会议时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "会议时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date meetingTime;

    /** 会议地址 */
    @Excel(name = "会议地址")
    private String meetingAddr;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal lat;

    /** signUp-报名 signIn-签到（多个用英文逗号分开） */
    @Excel(name = "signUp-报名 signIn-签到", readConverterExp = "多=个用英文逗号分开")
    private String process;

    /** 反馈模版id */
    @Excel(name = "反馈模版id")
    private String feedbackCode;

    /** 会议详情 */
    @Excel(name = "会议详情")
    private String content;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    /** 显示状态（0-隐藏 1-显示） */
    private String show;
    /**
     * 会议结束时间
     */
    @Excel(name = "会议结束时间",width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date meetingEndTime;
    /**
     * 会议结束时间
     */
    @Excel(name = "报名截止时间",width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date signUpEndTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMeetingTitle(String meetingTitle)
    {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingTitle()
    {
        return meetingTitle;
    }
    public void setMeetingTime(Date meetingTime)
    {
        this.meetingTime = meetingTime;
    }

    public Date getMeetingTime()
    {
        return meetingTime;
    }
    public void setMeetingAddr(String meetingAddr)
    {
        this.meetingAddr = meetingAddr;
    }

    public String getMeetingAddr()
    {
        return meetingAddr;
    }
    public void setLng(BigDecimal lng)
    {
        this.lng = lng;
    }

    public BigDecimal getLng()
    {
        return lng;
    }
    public void setLat(BigDecimal lat)
    {
        this.lat = lat;
    }

    public BigDecimal getLat()
    {
        return lat;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }

    public String getProcess()
    {
        return process;
    }

    public String getFeedbackCode() {
        return feedbackCode;
    }

    public void setFeedbackCode(String feedbackCode) {
        this.feedbackCode = feedbackCode;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public Date getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(Date meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Date getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Date signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("meetingTitle", getMeetingTitle())
            .append("meetingTime", getMeetingTime())
            .append("meetingAddr", getMeetingAddr())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("process", getProcess())
            .append("feedbackCode", getFeedbackCode())
            .append("content", getContent())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("meetingEndTime", getMeetingEndTime())
            .append("show", getShow())
            .append("signUpEndTime", getSignUpEndTime())
            .toString();
    }
}
