package servlet;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;
import service.BookPovo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModifyBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getContextPath();
        Books book = BookPovo.getBookByReq(req);
        String status = req.getParameter("status");
        if (status.equals("存在")) {
            book.setStatus(1);
        } else {
            book.setStatus(0);
        }
        System.out.println(book.toString());
        BooksDao bookDao = FactoryDao.getBookDao();
        int flag = bookDao.update(book);
        PrintWriter out = resp.getWriter();
        if(flag > 0) {
            out.println("<h2 style=\" text-align:center; \">更改成功, 将再3S后跳转到首页!</h2>");
            out.println("<script>window.setTimeout(\"location.href='addUser.jsp'\", 3000)</script>");
            out.close();
        } else {
            out.println("<h2 style=\" text-align:center; \">更改失败, 将再3S后跳转到首页!</h2>");
            out.println("<script>window.setTimeout(\"location.href='addUser.jsp'\", 3000)</script>");
            out.close();
        }

    }
}
