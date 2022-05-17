package servlet;

import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class modifyUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = new Users();
        user.setUserName(req.getParameter("userName"));
        user.setPassWord(req.getParameter("passWord"));
        user.setNikName(req.getParameter("nikName"));
        user.setSex(req.getParameter("sex"));
        user.setAddress(req.getParameter("address"));
        user.setPhoneNumber(req.getParameter("phoneNumber"));
        user.setStatus(req.getParameter("status"));
        //更行到数据库
        UsersDao userDao = FactoryDao.getUserDao();
        userDao.update(user);
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") != null) {
            session.setAttribute("userId", null);
        }
        resp.sendRedirect("index.jsp");
    }
}
