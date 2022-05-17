package servlet;

import dao.doIt.MessageDao;
import dao.factory.FactoryDao;
import povo.MessageBoard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MessageBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        MessageDao messDao = FactoryDao.getMessageDao();
        List<MessageBoard> list = null;
        list = messDao.queryByUserBookId(bookId);
        req.setAttribute("messageList", list);
        req.getRequestDispatcher("/messageBoard/displayMessage.jsp").forward(req, resp);
    }
}
