package servlet;

import dao.doIt.BooksDao;
import dao.doIt.BorrowDao;
import dao.factory.FactoryDao;
import povo.Books;
import povo.Borrow;
import util.dataBaseUtil.DataBaseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String borrowNum = req.getParameter("borrowNum");
        String[] arrStr = borrowNum.split(":");
        borrowNum = arrStr[0];
        System.out.println(borrowNum);
        //使用借阅号查询borrow记录, 然后使用书号查询图书信息
        BorrowDao borrDao = FactoryDao.getBorrowDao();
        Borrow borrow = borrDao.queryByBorrowId(borrowNum).get(0);
        BooksDao bookDao = FactoryDao.getBookDao();
        Books book = bookDao.queryByBookId(borrow.getBookId());
        PrintWriter out = resp.getWriter();
        //获取了相关记录 borrow book; 使用事务绑定, 进行数据库操作
        String sql1 = "update borrow set flag = 1 where num="+borrow.getNum();
        String sql2 = "update books set status = 1 where bookId=" + "'"+book.getBookId() + "'";

        System.out.println(sql1);

        System.out.println(sql2);
        DataBaseUtil db = new DataBaseUtil();
        Connection conn = db.getConnect();
        try {
            conn.setAutoCommit(false);
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql1);
            stat.executeUpdate(sql2);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            resp.sendRedirect("pointAction.jsp?tishi=ERROR&urlStr=returnBook.jsp");
        } finally {
            db.close();
        }
        resp.sendRedirect("pointAction.jsp?tishi=OK&urlStr=returnBook.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
