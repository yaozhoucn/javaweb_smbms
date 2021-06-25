package com.yaozhou.service.role;

import com.yaozhou.dao.BaseDao;
import com.yaozhou.dao.role.RoleDao;
import com.yaozhou.dao.role.RoleDaoImpl;
import com.yaozhou.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/25 17:40
 * Desc：
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }
    public List<Role> getRoleList() {
        Connection connection = null;
        connection = BaseDao.getConnection();

        List<Role> roleList = roleDao.getRoleList(connection);
        System.out.println(roleList);
        BaseDao.closeResource(connection,null,null);
        return roleList;
    }
}
