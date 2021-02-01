package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.project.domain.BisSignIn;
import lombok.Data;

import java.util.Date;

/**
 * @author liujc
 * @create 2020-12-29 15:41:07
 **/
@Data
public class BisSignInVo extends BisSignIn {

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 会议标题 */
    @Excel(name = "会议标题")
    private String meetingTitle;

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
    /** 会议时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "会议开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date meetingTime;
    /**
     * 会议结束时间
     */
    @Excel(name = "会议结束时间",width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date meetingEndTime;
    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "报名时间",width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date createTime;
}
