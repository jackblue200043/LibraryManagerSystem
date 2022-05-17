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

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用工具获取上传的书籍信息
        Books book = BookPovo.getBookByReq(req);
        System.out.println(book);
        //上传到数据库, 因为使用js进行了验证所以不需要验证
        BooksDao bookDao = FactoryDao.getBookDao();
        int flag = bookDao.insert(book);

        //输出提示
//        PrintWriter out = resp.getWriter();
        if(flag > 0) {
//            out.println("<h2>添加书籍成功, 将再1S后跳转到添加页面!</h2>");
//            out.println("<script>window.setTimeout(\"location.href='addBook.jsp'\", 1000)</script>");
//            out.close();
            resp.sendRedirect("pointAction.jsp?tishi=OK&urlStr=addBook.jsp");
        } else {
//            out.println("<h2>添加书籍失败, 将再3S后跳转到添加页面!</h2>");
//            out.println("<script>window.setTimeout(\"location.href='addBook.jsp'\", 3000)</script>");
//            out.close();
            resp.sendRedirect("pointAction.jsp?tishi=NO&urlStr=addBook.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
