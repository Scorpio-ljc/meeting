package com.ruoyi.project.domain.vo;

import com.ruoyi.project.domain.BisSignUp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leo
 * @date 04 Feb 2021
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BisSignUpVo extends BisSignUp {
    private String idCardNeed;
    private String foodNeed;
    private String stayNeed;
}
