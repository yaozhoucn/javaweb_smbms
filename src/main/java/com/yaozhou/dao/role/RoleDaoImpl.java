package com.yaozhou.dao.role;

import com.yaozhou.dao.BaseDao;
import com.yaozhou.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/25 17:17
 * Desc：
 */
public class RoleDaoImpl implements RoleDao {
    public List<Role> getRoleList(Connection connection) {
        String sql = "select * from smbms_role";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object[] parms = {};
        List<Role> roleArrayList = new ArrayList<Role>();
        if (connection !=null){
            try {
                resultSet = BaseDao.executeQuery(connection, sql, preparedStatement,resultSet, parms);

                while (resultSet.next()){
                    Role _role = new Role();
                    _role.setId(resultSet.getInt("id"));
                    _role.setRoleCode(resultSet.getString("roleCode"));
                    _role.setRoleName(resultSet.getString("roleName"));
                    roleArrayList.add(_role);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            BaseDao.closeResource(null,resultSet,preparedStatement);
        return  roleArrayList;
    }
}
