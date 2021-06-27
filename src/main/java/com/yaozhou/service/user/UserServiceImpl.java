package com.yaozhou.service.user;

import com.yaozhou.dao.BaseDao;
import com.yaozhou.dao.user.UserDao;
import com.yaozhou.dao.user.UserDaoImpl;
import com.yaozhou.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/21 17:00
 * Desc：
 * @author HANG
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

    public boolean pwdModify(int id, String userPassword) {
        boolean pwdModify = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            //开启jdbc事务
            connection.setAutoCommit(false);
            int i  = userDao.pwdModify(connection, id, userPassword);
            connection.commit();
            if (i > 0){
                pwdModify = true;
            }else {
                //出现异常进行回滚
                connection.rollback();
                System.out.println("密码修改失败");
                System.out.println("rollback=============");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return pwdModify;
    }

    public int getUserCount(String userName, int userRole) {
        Connection connection = BaseDao.getConnection();
        int count = userDao.getUserCount(connection, userName, userRole);
        BaseDao.closeResource(connection,null,null);
        return count;
    }

    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        Connection connection = BaseDao.getConnection();
        List<User> userList = userDao.getUserList(connection, userName, userRole, currentPageNo, pageSize);
        BaseDao.closeResource(connection,null,null);
        return userList;
    }

}
