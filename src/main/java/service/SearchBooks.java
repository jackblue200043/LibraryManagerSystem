package service;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SearchBooks {
    //服务, 用于查询数据库文件, 查询图书
    public static List<Books> doQueryBooks(HttpServletRequest req, HttpServletResponse resp) {
        String fieldValue = req.getParameter("fieldValue");
        String field = req.getParameter("field");
        String moHu = req.getParameter("flag");

        if (field != null && !field.equals("")) {
            if (field.equals("书本编号")) field = "bookId";
            if (field.equals("书名")) field = "bookName";
            if (field.equals("类型")) field = "type";
            if (field.equals("出版社")) field = "publisher";
            if (field.equals("作者")) field = "author";
        }

        //判断参数, 判断是否模糊搜索, 如果为yes, 那么dao处理为模糊
        if (moHu != null && moHu.equals("yes")) {
            moHu = "yes";
        } else {
            moHu = "no";
        }
        BooksDao bookDao = FactoryDao.getBookDao();
        List<Books> list = new ArrayList<Books>();
        try {
            list = bookDao.query(field, fieldValue, moHu);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("BooksPageDisplayService" + "访问数据库失败"    );
        }
        return list;
    }
}
