package com.ruoyi.common.utils.wx;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.wx.bean.WxQrCodeRequest;
import com.ruoyi.common.utils.wx.properties.WxMaConfiguration;
import com.ruoyi.common.utils.wx.properties.WxMaProperties;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * 微信工具类
 *
 * @author liujc
 * @create 2020-12-14 15:25:39
 **/
@Slf4j
@Component
public class WxUtil{
    @Autowired
    private WxMaProperties wxMaProperties;
    @Autowired
    private WxMaService wxMaService;

    /**
     * 生成小程序二维码
     *
     * @param wxQrCodeRequest
     */
    public void getQrcode(WxQrCodeRequest wxQrCodeRequest) {
        byte[] wxaCodeUnlimitBytes = null;
        try {
            wxaCodeUnlimitBytes = wxMaService.getQrcodeService().createWxaCodeUnlimitBytes(wxQrCodeRequest.getScene(), wxQrCodeRequest.getPage(),
                    wxQrCodeRequest.getWidth(), wxQrCodeRequest.isAutoColor(), wxQrCodeRequest.getLineColor(), wxQrCodeRequest.isHyaline());
            ServletOutputStream outputStream = null;
            HttpServletResponse httpServletResponse = wxQrCodeRequest.getHttpServletResponse();
            httpServletResponse.setContentType("image/png");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(wxQrCodeRequest.getFileName(), "UTF-8"));
            try {
                outputStream = httpServletResponse.getOutputStream();
                outputStream.write(wxaCodeUnlimitBytes);
                outputStream.close();
            } catch (IOException e) {
                log.error("返回图片失败===>", e);
            } finally {
                try {
                    Objects.requireNonNull(outputStream).close();
                } catch (IOException e) {
                    log.error("关闭流失败===>", e);
                }
            }
        } catch (WxErrorException e) {
            log.error("微信-获取二维码错误===>", e);
            throw new CustomException("生成二维码错误");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户openId
     *
     * @param loginCode 登录code
     * @return
     */
    public String getOpenId(String loginCode) {
        final WxMaService wxService = WxMaConfiguration.getMaService(wxMaProperties.getConfigs().get(0).getAppid());
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(loginCode);
            return session.getOpenid();
        } catch (WxErrorException e) {
            log.error("微信-getOpenId", e);
            return "";
        }

    }
    public WxMaUserInfo getMaUserInfo(String sessionKey,String encryptedData,String iv){
        return wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
    }

    /**
     * 获取小程序session、openid
     *
     * @param loginCode 登录code
     * @return 结果
     */
    public WxMaJscode2SessionResult getSessionResult(String loginCode){
        WxMaJscode2SessionResult wxMaJscode2SessionResult = null;
        try {
            wxMaJscode2SessionResult =  wxMaService.getUserService().getSessionInfo(loginCode);
        } catch (WxErrorException e) {
            log.error("微信-获取session===>",e);
        }
        return wxMaJscode2SessionResult;
    }
    /**
     * 微信订阅消息推送
     * @param wxMaSubscribeMessage 微信订阅请求消息
     */
    public void sendSubMess(WxMaSubscribeMessage wxMaSubscribeMessage){
        WxMaMsgService msgService = wxMaService.getMsgService();
        try {
            msgService.sendSubscribeMsg(wxMaSubscribeMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("微信-消息推送错误===>",e);
        }
    }

}
