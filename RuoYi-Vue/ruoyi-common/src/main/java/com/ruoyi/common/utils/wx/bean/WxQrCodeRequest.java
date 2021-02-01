package com.ruoyi.common.utils.wx.bean;

import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liujc
 * @create 2020-12-14 15:37:24
 **/
@Data
public class WxQrCodeRequest {
    /**
     * 二维码参数
     */
    private String scene;
    /**
     * 二维码页面
     */
    private String page;
    /**
     *默认true 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     */
    private int width;
    private boolean autoColor;
    /**
     * lineColor autoColor 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"}
     */
    private WxMaCodeLineColor lineColor;
    /**
     * 是否需要透明底色， is_hyaline 为true时，生成透明底色的小程序码
     */
    private boolean isHyaline;

    private HttpServletResponse httpServletResponse;

    /**
     * 文件名称
     */
    private String fileName;

    public WxQrCodeRequest(String scene, String page, int width, boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline,HttpServletResponse httpServletResponse) {
        this.scene = scene;
        this.page = page;
        this.width = width;
        this.autoColor = autoColor;
        this.lineColor = lineColor;
        this.isHyaline = isHyaline;
    }
}
