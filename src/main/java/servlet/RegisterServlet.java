package servlet;

import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Users;
import service.UserPovo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上传的用户信息;使用服务获取
        Users user = UserPovo.getUserByReq(req);

        //上传数据库, 将数据更行, 因为再页面进行了验证, 所以不需要再验证, 提交后显示成功3s后跳转到管理员注册页面;
        UsersDao userDao = FactoryDao.getUserDao();
        int flag = userDao.insert(user);
        //输出提示
        //PrintWriter out = resp.getWriter();
        if(flag > 0) {
            resp.sendRedirect("pointAction.jsp?tishi=RegisterOK&urlStr=addUser.jsp");
            //out.println("<h2>注册成功, 将再3S后跳转到注册页面!</h2>");
            //out.println("<script>window.setTimeout(\"location.href='addUser.jsp'\", 3000)</script>");
            //out.close();
        } else {
            resp.sendRedirect("pointAction.jsp?tishi=ERROR&urlStr=addUser.jsp");
            //out.println("<h2>注册失败, 将再3S后跳转到注册页面!</h2>");
            //out.println("<script>window.setTimeout(\"location.href='addUser.jsp'\", 3000)</script>");
           // out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
