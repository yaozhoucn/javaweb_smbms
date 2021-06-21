package com.yaozhou.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by WXHang on HANG at 2021/6/17 16:43
 * Desc：
 * @author
 */

//操作数据库的公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，在类加载的时候就初始化了
    static {
        Properties properties = new Properties();
        //通过类加载器加载响应的资源
        InputStream resource = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");


    }
    //获取数据库的链接
    public static Connection getConnection(){
    //加载驱动
        Connection connection = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    //编写查询公共类
    public static ResultSet executeQuery(Connection connection, String sql,PreparedStatement presm,ResultSet resultSet1,Object...params) throws SQLException {
        ResultSet resultSet = null;

        //预编译sql
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }

        try {
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return resultSet;

    }

    /**
     * 编写增删改公共方法
     * @return
     */
    public static int executeUpdate(Connection connection,String sql,PreparedStatement presm ,Object...params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int updateRows = 0;
        for (int i = 0; i <params.length ; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        try {
            updateRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateRows;

    }

    /**
     *
     * @param connection
     * @param resultSet
     * @param preparedStatement
     * @return
     *
     * 关闭资源，先判断是否为空，再关闭，如果出现异常，则认为资源关闭异常，返回flag = false；
     */
    public static boolean closeResource(Connection connection,ResultSet resultSet,PreparedStatement preparedStatement){
        boolean flag = true;
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
            //GC回收
            resultSet = null;
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
            //GC回收
            preparedStatement = null;
        }
        if (connection != null){
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
            //GC回收
            connection = null;
        }
        return  flag;

    }
}
