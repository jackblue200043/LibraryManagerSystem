package servlet;

import povo.Users;
import service.LoginVerify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //因为要进行过滤, 只有登入的用户才可以访问除主页外的其他页面, 所以, 将登入的用户存放到session;
        //获取登入的用户名, 密码;
        Users user = null;
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        //如果存在此用户那验证密码;
        if (userName != null && LoginVerify.isUsers(userName)) {
            user = LoginVerify.getUsers(userName);
            String pass = user.getPassWord();
            //账号密码匹配, 登入成功
            if (passWord != null && passWord.equals(pass)) {

                req.getSession().setAttribute("userId", user);
                if (user.getStatus().equals("root")) {
                    resp.sendRedirect("administrator/home.jsp");
                } else {
                    resp.sendRedirect("index.jsp");
                }
                //密码错误, 进入登入页面, 并提示密码错误;
            } else {
                resp.sendRedirect("login.jsp?point=" + 1);
            }
        } else {
            //没有此用户
            resp.sendRedirect("login.jsp?point=" + 2);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
}
