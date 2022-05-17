package servlet;

import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Users;
import util.characterUtil.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        //解决中文乱码
        userName = StringUtil.changeChar(userName);
        //查询数据库, 如果有此用户那么返回true
        UsersDao userDao = FactoryDao.getUserDao();
        Users user = userDao.queryByUserName(userName);
        String title;
        if (user != null) {
            title = "账号:"+user.getUserName()+" " +"用户名:" + user.getNikName();
        } else {
            title = userName + ":不存在此用户";
        }
//        resp.setHeader("Cache-Control","no-store");//HTTP1.1
//        resp.setHeader("Pragma","no-cache");//HTTP1.0
//        resp.setDateHeader("Expires",1);//禁止在服务器中缓存
        resp.getWriter().write(title);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
