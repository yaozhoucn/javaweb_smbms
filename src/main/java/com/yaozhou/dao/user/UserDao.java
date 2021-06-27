package com.yaozhou.dao.user;

import com.yaozhou.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/21 16:29
 * Desc：
 */
public interface UserDao {
    public User getLoginUser(Connection connection,String userCode);
    public int pwdModify(Connection connection,int id,String userPassword);

    /**
     *
     * @param connection
     * @param userName
     * @param userRole
     * @return 获取用户数量，全部或者角色为xxx的用户数量
     */
    public int getUserCount(Connection connection,String userName,int userRole);

    /**
     *
     * @param connection
     * @param userName
     * @param userRole
     * @param currentPageNo
     * @param pageSize
     * @return
     * @docs 获取用户列表；
     */
    public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo,int pageSize) throws Exception;

}
