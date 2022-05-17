package servlet;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookMenageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用传过来的参数进行页面转发
        String path = req.getContextPath();
        String bookId = req.getParameter("bookId");
        BooksDao bookDao = FactoryDao.getBookDao();
        Books bookPo = bookDao.queryByBookId(bookId);
        req.setAttribute("bookPo", bookPo);
        req.getRequestDispatcher("modifyBook.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
