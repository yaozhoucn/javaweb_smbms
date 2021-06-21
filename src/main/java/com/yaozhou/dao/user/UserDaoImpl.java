package com.yaozhou.dao.user;

import com.yaozhou.dao.BaseDao;
import com.yaozhou.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}