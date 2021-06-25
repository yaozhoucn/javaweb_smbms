package com.yaozhou.service.user;

import com.yaozhou.dao.BaseDao;
import com.yaozhou.dao.user.UserDao;
import com.yaozhou.dao.user.UserDaoImpl;
import com.yaozhou.pojo.User;

import java.sql.Connection;

/**
 * Created by WXHang on HANG at 2021/6/21 17:00
 * Desc：
 */
public class UserServiceImpl implements UserService {
    /**
     * service层会调用dao层，必须引入dao
     * @param userCode
     * @param userPassword
     * @return
     */
    private UserDao userDao;
    //new UserServiceImpl 时，userDao就被实例化
    public UserServiceImpl(){
         userDao = new UserDaoImpl();
    }
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;
        connection = BaseDao.getConnection();
        user = userDao.getLoginUser(connection, userCode);

        BaseDao.closeResource(connection,null,null);

        return user;

    }

    /**
     * 主要与业务层交互
     * @param id
     * @param userPassword
     * @return
     */

    public int pwdModify(int id, String userPassword) {
        int pwdModify = 0;
        Connection connection = null;
        connection = BaseDao.getConnection();


            pwdModify = userDao.pwdModify(connection, id, userPassword);
            BaseDao.closeResource(connection,null,null);

        return pwdModify;
    }

}
