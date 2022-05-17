package servlet;

import dao.doIt.MessageDao;
import dao.factory.FactoryDao;
import povo.MessageBoard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class deleteMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取num, 并在数据库中删除
        String num = req.getParameter("num");
        System.out.println(num);
        MessageDao meDao = FactoryDao.getMessageDao();
        MessageBoard messB = new MessageBoard();
        messB.setNum(Integer.parseInt(num));
        int flag = meDao.delete(messB);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        if (flag > 0) {
            out.println("<script>alter('删除成功')</script>");
        } else {
            out.println("<script>alter('删除失败')</script>");
        }
        //获取bookId, 进入留言页面
        String bookId = req.getParameter("bookId");
        resp.sendRedirect("MessageBoard?bookId="+bookId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
