package com.yaozhou.dao.user;

import com.yaozhou.pojo.User;

import java.sql.Connection;

/**
 * Created by WXHang on HANG at 2021/6/21 16:29
 * Desc：
 */
public interface UserDao {
    public User getLoginUser(Connection connection,String userCode);

}
