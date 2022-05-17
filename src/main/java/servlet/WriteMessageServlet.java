package servlet;

import dao.doIt.MessageDao;
import dao.factory.FactoryDao;
import povo.MessageBoard;
import povo.Users;
import util.time.GetNowDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class WriteMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取输入框内容 书号
        String messageText = req.getParameter("messageText");
        String bookId = req.getParameter("bookId");
        String dateTime = GetNowDate.getDate();
        //获取用户名
        String userName = ((Users)req.getSession().getAttribute("userId")).getUserName();
        MessageDao meDao = FactoryDao.getMessageDao();
        //创建MessageBoard java实例
        MessageBoard messBo = new MessageBoard();
        messBo.setBookId(bookId);
        messBo.setMessage(messageText);
        messBo.setTime(dateTime);
        messBo.setUserName(userName);
        int flag = meDao.insert(messBo);
        //获取输出对象
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        if (flag > 0) {
            out.println("<script>window.opener.location.reload();alert('OK,插入成功!');window.close();</script>");
        }else {
            out.println("<script>alert('error,插入失败!');window.close();</script>");
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
