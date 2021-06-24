package com.yaozhou.servlet.user;

import com.mysql.jdbc.StringUtils;
import com.yaozhou.pojo.User;
import com.yaozhou.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WXHang on HANG at 2021/6/24 22:48
 */
public class UserServlet extends HttpServlet {
    /**
     * 实现servlet的复用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldpassword = req.getParameter("oldpassword");
        User user = (User) req.getSession().getAttribute(Constants.USER_SEESI0N);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (user == null){
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result","error");
        }else {
            if (oldpassword.equals(user.getUserPassword())){
                resultMap.put("result","true");
            }else {
                resultMap.put("result","false");
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}