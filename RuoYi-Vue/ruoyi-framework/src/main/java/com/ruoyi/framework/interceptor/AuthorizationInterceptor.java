package com.ruoyi.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.bean.UserContext;
import com.ruoyi.common.core.bean.UserInfor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author liujc
 * @create 2020-12-02 13:48:34
 **/
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    private RedisCache redisCache;
    /**
     * 存放鉴权信息的Header名称，默认是Authorization
     */
    private final String HTTP_HEADER_NAME = "token";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = ((HandlerMethod) handler).getMethod();
        //不进行验证
        if(method.getAnnotation(AnonymousAccess.class) != null || handlerMethod.getBeanType().getAnnotation(AnonymousAccess.class) != null){
            return true;
        }else{
            UserInfor userContextInfo = null;
            String token = request.getHeader(HTTP_HEADER_NAME);
            if(!StringUtils.isEmpty(token) && (userContextInfo = (UserInfor) redisCache.getCacheObject(token)) != null){
                UserContext.set(userContextInfo);
                return true;
            }else{
                AjaxResult jsonResult = AjaxResult.error("token失效");
                printMess(response,jsonResult);
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void printMess(HttpServletResponse response, AjaxResult jsonResult) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(JSON.toJSONString(jsonResult));
        } catch (Exception e) {
            log.error("response error",e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
