package com.yaozhou.dao.role;

import com.yaozhou.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * Created by WXHang on HANG at 2021/6/25 17:10
 * Desc：
 */
public interface RoleDao {
    //获取角色列表
    public List<Role> getRoleList(Connection connection);
}
