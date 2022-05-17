package servlet;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;
import util.characterUtil.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookAjaxServlet extends HttpServlet {
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
            title = "书号:"+books.getBookId()+ " " + "书名:" + books.getBookName() + " " + "类型:" + books.getType()+ " "
                    + "作者:" + books.getAuthor() + " " + "出版社:" + books.getPublisher() + " " + "可借天数:" + books.getTime()+
                    (books.getStatus() > 0 ? "存在" : "借出");
        } else {
            title = bookId + ":不存在此书";
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
