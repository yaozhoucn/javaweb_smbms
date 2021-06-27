package com.yaozhou.service.user;

import com.yaozhou.pojo.User;

import java.sql.Connection;
import java.util.List;

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

    /**
     *
     * @param id
     * @param userPassword
     * @return 修改密码
     */
    public boolean pwdModify(int id,String userPassword);

    /**
     * 获取用户数量
     * @param userName
     * @param userRole
     * @return
     */
    public int getUserCount(String userName, int userRole);

    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) throws Exception;
}
