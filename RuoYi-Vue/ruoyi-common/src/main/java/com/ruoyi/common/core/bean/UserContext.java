package com.ruoyi.common.core.bean;
import com.ruoyi.common.core.bean.UserInfor;
/**
 * @author liujc
 * @create 2020-12-02 13:52:23
 **/
public class UserContext {
    private static final ThreadLocal<UserInfor> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static UserInfor get(){
        if(USER_INFO_THREAD_LOCAL.get() == null){
            return new UserInfor();
        }
        return USER_INFO_THREAD_LOCAL.get();
    }
    public static void set(UserInfor userInforInfo){
        USER_INFO_THREAD_LOCAL.set(userInforInfo);
    }

    /**
     * 回收线程
     */
    public static void remove(){
        USER_INFO_THREAD_LOCAL.remove();
    }
}
