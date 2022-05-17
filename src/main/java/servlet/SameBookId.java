package servlet;

import dao.doIt.BooksDao;
import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Books;
import povo.Users;
import util.characterUtil.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SameBookId extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        //解决中文乱码
        bookId = StringUtil.changeChar(bookId);
        //查询数据库, 如果有此用户那么返回true
        BooksDao bookDao = FactoryDao.getBookDao();
        Books books = bookDao.queryByBookId(bookId);
        String title;
        if (books != null) {
            title = "true";
        } else {
            title = "false";
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
