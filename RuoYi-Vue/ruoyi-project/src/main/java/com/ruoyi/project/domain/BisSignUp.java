package com.ruoyi.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会议报名信息对象 bis_sign_up
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public class BisSignUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    /** 会议标题 */
    @Excel(name = "会议标题")
    private String meetingTitle;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uid;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 会议id */
    @Excel(name = "会议id")
    private Long meetingId;


    /** 身份证号 */
    @Excel(name = "身份证号")
    private String cardId;

    /** 职务 */
    @Excel(name = "职务")
    private String position;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String orgName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 微信openid */
    @Excel(name = "微信openid")
    private String openid;
    /**
     * 审核状态 0-未审核 1-审核通过 2-审核拒绝
     */
    @Excel(name = "审核状态 0-未审核 1-审核通过 2-审核拒绝")
    private String state;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    @Excel(name = "是否需要身份证")
    private String idCardNeed;
    @Excel(name = "是否需要伙食")
    private String foodNeed;
    @Excel(name = "是否需要住宿")
    private String stayNeed;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid()
    {
        return uid;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile()
    {
        return mobile;
    }
    public void setMeetingId(Long meetingId)
    {
        this.meetingId = meetingId;
    }

    public Long getMeetingId()
    {
        return meetingId;
    }
    public void setMeetingTitle(String meetingTitle)
    {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingTitle()
    {
        return meetingTitle;
    }
    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }

    public String getCardId()
    {
        return cardId;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getPosition()
    {
        return position;
    }
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return orgName;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIdCardNeed() {
        return idCardNeed;
    }

    public void setIdCardNeed(String idCardNeed) {
        this.idCardNeed = idCardNeed;
    }

    public String getFoodNeed() {
        return foodNeed;
    }

    public void setFoodNeed(String foodNeed) {
        this.foodNeed = foodNeed;
    }

    public String getStayNeed() {
        return stayNeed;
    }

    public void setStayNeed(String stayNeed) {
        this.stayNeed = stayNeed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("name", getName())
            .append("mobile", getMobile())
            .append("meetingId", getMeetingId())
            .append("meetingTitle", getMeetingTitle())
            .append("cardId", getCardId())
            .append("position", getPosition())
            .append("orgName", getOrgName())
            .append("email", getEmail())
            .append("openid", getOpenid())
            .append("state", getState())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("idCardNeed",getIdCardNeed())
            .append("foodNeed",getFoodNeed())
            .append("stayNeed",getStayNeed())
            .toString();
    }
}
