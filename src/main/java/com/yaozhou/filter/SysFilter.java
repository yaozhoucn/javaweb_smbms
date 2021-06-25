package com.yaozhou.filter;

import com.yaozhou.pojo.User;
import com.yaozhou.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WXHang on HANG at 2021/6/22 17:36
 * Desc：
 */
public class SysFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //判断session里面的user是否为空
        User user = (User) req.getSession().getAttribute(Constants.USER_SEESI0N);
        if (user == null){
            resp.sendRedirect("../error.html");
        }else {
            chain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
