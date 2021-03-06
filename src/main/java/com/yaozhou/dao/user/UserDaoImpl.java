package com.yaozhou.dao.user;

import com.mysql.jdbc.StringUtils;
import com.yaozhou.dao.BaseDao;
import com.yaozhou.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/21 16:32
 * Desc：
 * @author HANG
 */
public class UserDaoImpl implements UserDao {
    public User getLoginUser(Connection connection, String userCode) {
        User user = null;
        PreparedStatement presm = null;
        ResultSet rs = null;
        String sql = "select * from smbms_user where userCode = ?";
        Object params = userCode;
        if (connection !=null){
            try {
                rs = BaseDao.executeQuery(connection, sql, presm, rs, params);
                if (rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getDate("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getDate("modifyDate"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BaseDao.closeResource(null,rs,presm );
        }
        return user;
    }

    /**
     * 主要与数据库交互
     * @param connection
u.bir     * @param id
     * @param userPassword
     * @return
     */
    public int pwdModify(Connection connection, int id,String userPassword) {
        boolean flag = false;
        int executeUpdate = 0;
        PreparedStatement presm = null;
        Object[] params = {userPassword,id};

        String sql = "update smbms.smbms_user set userPassword = ? where id = ?";

            if (connection != null){
                try {
                     executeUpdate = BaseDao.executeUpdate(connection, sql, presm, params);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (executeUpdate > 0){
                flag = true;
            }else {
                flag = false;
            }
            BaseDao.closeResource(null,null,presm);

        return executeUpdate;
    }

    public int getUserCount(Connection connection, String userName, int userRole) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sql = new StringBuffer();
        Object[] params = {};
        int count = 0;
        if (connection != null){
            List<Object> list = new ArrayList<Object>();
            sql.append("select count(1) as count from smbms_user u ,smbms_role r where u.userRole = r.id ");
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append("and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if (userRole > 0){
                sql.append(" and r.id = ?");
                list.add(userRole);
            }
            params = list.toArray();
            System.out.println("sql --->>"+sql);
            try {
                resultSet = BaseDao.executeQuery(connection, sql.toString(), preparedStatement, resultSet,params);
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BaseDao.closeResource(null,resultSet,preparedStatement);
        }

        return count;
    }

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object[] params = {};
        List<User> userList = new ArrayList<User>();
        if (connection != null){
            ArrayList<Object> list = new ArrayList<Object>();
            StringBuffer sql = new StringBuffer();
            //sql.append("select u.userCode,u.userName,u.gender,(year(sysdate())-year(u.birthday)) as age,u.phone,u.userRole,r.roleName as userRoleName from smbms_user u ,smbms_role r where u.userRole = r.id");
            sql.append("select u.userCode,u.userName,u.gender,u.birthday,u.phone,u.userRole,r.roleName as userRoleName from smbms_user u ,smbms_role r where u.userRole = r.id");
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if (userRole > 0 ){
                sql.append(" and r.id = ?");
                list.add(userRole);
            }
            sql.append(" order by u.id DESC limit ?,?");
            //分页参数
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            params = list.toArray();

            System.out.println("sql ===>>"+sql);
            try {
                resultSet = BaseDao.executeQuery(connection,sql.toString(),preparedStatement,resultSet,params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            while (resultSet.next()){
                User user = new User();
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
                userList.add(user);
            }
        }
        BaseDao.closeResource(null,resultSet,preparedStatement);
        return userList;
    }

}
