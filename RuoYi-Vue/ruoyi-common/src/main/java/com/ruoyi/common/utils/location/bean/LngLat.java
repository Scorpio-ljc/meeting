package com.ruoyi.common.utils.location.bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author liujc
 * @create 2020-12-15 13:53:46
 **/
@Data
@ToString
public class LngLat {
    /**
     * 经度
     */
    private BigDecimal lng;
    /**
     * 纬度
     */
    private BigDecimal lat;

    public LngLat(BigDecimal lng, BigDecimal lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
