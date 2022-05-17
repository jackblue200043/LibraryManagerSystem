package servlet;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;
import util.dataBaseUtil.DataBaseUtil;
import util.time.GetNowDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrowBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //第一步: 获取提交信息
        String userName = req.getParameter("userName");
        String bookId = req.getParameter("bookId");
        //因为已经使用js进行用户名密码验证, 所以不用使用验证;
        //取出book信息, 如果已经借出, 输出提示
        BooksDao bookDao = FactoryDao.getBookDao();
        Books book = bookDao.queryByBookId(bookId);


        PrintWriter out = resp.getWriter();

        //处理外借图书
        //将图书状态改为0(借出), 增加一条借阅记录
        //问: 要将两条操作进行事务绑定, 以免再操作时碰到问题;
        if (book.getStatus() > 0) {
            DataBaseUtil db = new DataBaseUtil();
            Connection conn = db.getConnect();
            //修改图书状态与添加借阅记录
            String sql1 = "update books set status = 0 where bookId = " + "'"+ book.getBookId() + "';";
            String sql2 = "insert into borrow(userName, bookId, start, timeLeft, flag) " +
                    "values("+ "'" + userName + "'" + ","+ "'" +book.getBookId() + "'" + "," +
                    "'" + GetNowDate.getDate() + "'" + "," + book.getTime() + "," + "0" + ");";
            System.out.println(sql1);
            System.out.println(sql2);
            try {
                conn.setAutoCommit(false);
                Statement stat = conn.createStatement();
                stat.executeUpdate(sql1);
                stat.executeUpdate(sql2);
                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//                out.println("<h2 style=\"text-align:center;\">未知原因, 无法借出</h2>");
//                out.println("<script>window.setTimeout(\"location.href='borrowBook.jsp'\", 3000)</script>");
//                out.close();
                resp.sendRedirect("pointAction.jsp?tishi=ERROR&urlStr=borrowBook.jsp");
            }finally {
                db.close();
            }
//            out.println("<h2 style=\"text-align:center;\">借阅成功, 将再2S后跳转到借阅页面!</h2>");
//            out.println("<script>window.setTimeout(\"location.href='borrowBook.jsp'\", 3000)</script>");
//            out.close();
            resp.sendRedirect("pointAction.jsp?tishi=YES&urlStr=borrowBook.jsp");
        } else {
//            out.println("<h2 style=\"text-align:center;\">借阅失败, 本书以被借出</h2>");
//            out.println("<script>window.setTimeout(\"location.href='borrowBook.jsp'\", 3000)</script>");
//            out.close();
            resp.sendRedirect("pointAction.jsp?tishi=ERROR&urlStr=borrowBook.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
