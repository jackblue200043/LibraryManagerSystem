package servlet;

import dao.doIt.FileDao;
import dao.factory.FactoryDao;
import povo.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class deleteFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("fileId");
        FileDao fDao = FactoryDao.getFileDao();
        File filepo = new File();
        filepo = fDao.queryByFileId(fileId);
        //删除文件;
        String fileDir = filepo.getDirectory();
        java.io.File file = new java.io.File(fileDir);
        if (file.delete() || !file.exists()) {
            //删除数据库
            fDao.delete(filepo);
        }
        String path = req.getContextPath();
        resp.sendRedirect(path + "/displayFile/displayFile");
    }
}
