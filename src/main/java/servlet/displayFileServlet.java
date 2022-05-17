package servlet;

import dao.doIt.FileDao;
import dao.factory.FactoryDao;
import povo.File;
import povo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class displayFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
            //根据session中的Session取出user类, 使用userName取出文件列表;
            Users user = (Users) session.getAttribute("userId");
            boolean flag = false;//判断是否登入;
            String userName = null;
            if (user != null) {
                userName = user.getUserName();
                flag = true;
        }
        //查询文件类数据库;
        FileDao fileDao = FactoryDao.getFileDao();
        List<File> list = fileDao.queryByUserName(userName);
        String path = req.getContextPath();
        if (flag) {
            //如果已经登入, 那么将查询结果给request, 并转发到session;
            req.setAttribute("fileList", list);
            req.getRequestDispatcher("displayFile.jsp").forward(req,resp);
        } else{
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('请登入'); window.location.href='../login.jsp'</script>");
        }
    }
}
