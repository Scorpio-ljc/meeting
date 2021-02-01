package com.ruoyi.project.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.project.domain.BisSignUp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author liujc
 * @create 2021-01-22 18:08:29
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BisSignUpExcelVo extends BisSignUp {
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
