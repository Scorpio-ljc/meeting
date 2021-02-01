package com.ruoyi.common.utils.location;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.location.bean.LngLat;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 地址工具方法
 *
 * @author liujc
 * @create 2020-12-15 11:42:19
 **/
@Slf4j
public class LocationUtils {
    /**
     * 其他地图转换为腾讯地图
     *
     * @param lngLat 经纬度
     * @return 结果
     */
    public static LngLat toTencentLocation(LngLat lngLat) {
        String param = "locations=%s,%s&type=5&key=H63BZ-WHA66-7R7SW-MAKPZ-XR6QT-KFFEB";
        String format = String.format(param, lngLat.getLat(), lngLat.getLng());
        String result = HttpUtils.sendGet("https://apis.map.qq.com/ws/coord/v1/translate", format);
        log.info("地图转换结果===>{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer status = jsonObject.getInteger("status");
        if (status == 0) {
            JSONArray locations = jsonObject.getJSONArray("locations");
            lngLat.setLng(locations.getJSONObject(0).getBigDecimal("lng"));
            lngLat.setLat(locations.getJSONObject(0).getBigDecimal("lat"));
        }
        return lngLat;
    }

    /**
     * 计算两点距离
     *
     * @param from
     * @param to
     */
    public static double matrix(LngLat from, LngLat to) {
         return getDistance(Double.parseDouble(from.getLat().toString())
                , Double.parseDouble(from.getLng().toString())
                , Double.parseDouble(to.getLat().toString())
                , Double.parseDouble(to.getLng().toString()));
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    public static void main(String[] args) {
        LngLat lngLat = new LngLat(new BigDecimal("104.04311"), new BigDecimal("30.64242"));
        LngLat to = new LngLat(new BigDecimal("104.076514"), new BigDecimal("30.580992"));
        System.out.println(toTencentLocation(lngLat));
        System.out.println(matrix(lngLat, to));

    }

}
