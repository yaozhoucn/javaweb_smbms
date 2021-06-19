package com.yaozhou.dao;

import java.io.InputStream;
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


    }
    //获取数据库的链接

    //编写查询公共类
}
