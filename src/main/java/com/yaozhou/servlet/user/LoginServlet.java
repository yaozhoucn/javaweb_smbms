package com.yaozhou.servlet.user;

import com.yaozhou.pojo.User;
import com.yaozhou.service.user.UserService;
import com.yaozhou.service.user.UserServiceImpl;
import com.yaozhou.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WXHang on HANG at 2021/6/21 17:36
 * Desc：
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        if (userCode != null || userPassword != null){
            UserService userService = new UserServiceImpl();
            User user = userService.login(userCode, userPassword);
            if(user !=null){
                if (user.getUserPassword().equals(userPassword)){
                    req.getSession().setAttribute(Constants.USER_SEESI0N,user);
                    resp.sendRedirect("/jsp/frame.jsp");
                }else {
                    req.setAttribute("error","用户名或密码有误，请重新登陆！");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                }
            }else{
                req.setAttribute("error","用户名或密码有误，请重新登陆！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
