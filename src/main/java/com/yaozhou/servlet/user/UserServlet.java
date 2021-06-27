package com.yaozhou.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.yaozhou.pojo.Role;
import com.yaozhou.pojo.User;
import com.yaozhou.service.role.RoleService;
import com.yaozhou.service.role.RoleServiceImpl;
import com.yaozhou.service.user.UserService;
import com.yaozhou.service.user.UserServiceImpl;
import com.yaozhou.util.Constants;
import com.yaozhou.util.PageSupport;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if (method!=null && method.equals("pwdmodify")){
            this.pwdmodify(req,resp);
        }else if (method!=null && method.equals("savepwd")){
            this.updatePwd(req,resp);
        }else if (method!=null && method.equals("query") ){
            this.query(req,resp);
        }


    }
    //使用ajax判断密码是否正确
    private void pwdmodify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oldpassword = req.getParameter("oldpassword");
        //从session当中获取当前密码
        User user = (User) req.getSession().getAttribute(Constants.USER_SEESI0N);
        System.out.println(user.getUserPassword());
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

        //把resultMap转换成json对象输出
        resp.setContentType("application/json");
        PrintWriter outWrite = resp.getWriter();
        outWrite.write(JSONArray.toJSONString(resultMap));
        outWrite.flush();
        outWrite.close();
    }
    //修改密码
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean flag = false;
        User user = (User) req.getSession().getAttribute(Constants.USER_SEESI0N);
        String newpassword = req.getParameter("newpassword");
        if (user != null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            Integer id = user.getId();
            flag = userService.pwdModify(id, newpassword);
            if (flag){
                req.setAttribute(Constants.SYS_MESSAGE,"密码修改成功,请退出并使用新密码重新登录！");
                //退出重新登陆
                //注销session
               req.getSession().removeAttribute(Constants.USER_SEESI0N);
            }else {
                req.setAttribute(Constants.SYS_MESSAGE,"密码修改失败！");
            }
        }else {
            req.setAttribute(Constants.SYS_MESSAGE,"密码修改失败！");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);


    }
    private void query(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //从前端获取数据
        String queryname = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        System.out.println(queryname);
        int queryUserRole = 0;
        System.out.println(queryUserRole);
        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        System.out.println(roleList);
        //判断获取的queryname是否为空；
        if(queryname == null){
            queryname = "";
        }
        //不判空会报空指针异常
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);//给查询赋值
        }
        //第一次进入页面为固定
        //设置页面容量
        int pageSize = Constants.pageSize;
        //设置当前页
        int currentPageNo = 1;
        if (pageIndex != null){
            currentPageNo = Integer.valueOf(pageIndex);
        }
        //获取总数以便分页
        int totalCount = userService.getUserCount(queryname, queryUserRole);
        //获取总页数
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount(totalCount, pageSize);
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        List<User> userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
        System.out.println("role++++"+queryUserRole);
        req.setAttribute("userList",userList);
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("pageSize",pageSize);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalCount",totalCount);
        req.getRequestDispatcher("/jsp/userlist.jsp").forward(req,resp);
        //queryUserRole
        //queryUserName
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}