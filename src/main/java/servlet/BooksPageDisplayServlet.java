package servlet;

import dao.doIt.BooksDao;
import dao.factory.FactoryDao;
import povo.Books;
import service.SearchBooks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BooksPageDisplayServlet extends HttpServlet {
    List<Books> list = new ArrayList<Books>();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        list = SearchBooks.doQueryBooks(req, resp);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        Integer page = null;
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        int listSize = list.size();
        int maxSize = 10;
        if (page == null || page < 1) {
            page = 1;
        }
        if (page > listSize/10) {
            page = listSize/10 +1;
        }
        int start = (page - 1)*maxSize;
        int end = page * maxSize;
        if (end > listSize) {
                end = listSize;
        }
        List<Books> subList = list.subList(start, end);
        boolean isHas = true;
        req.setAttribute("BookList", subList);
        req.setAttribute("isHas", isHas);
        req.getRequestDispatcher("/index.jsp?page="+page).forward(req, resp);
    }
}
