package com.yaozhou.service.user;

import com.yaozhou.pojo.User;

/**
 * Created by WXHang on HANG at 2021/6/21 16:57
 * Desc：
 * @author HANG
 */
public interface UserService {
    /**
     * 用户登录
     * @param userCode
     * @param userPassword
     * @return
     */
    public User login(String userCode,String userPassword);
}
