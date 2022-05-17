package servlet;

import dao.doIt.FileDao;
import dao.factory.FactoryDao;
import povo.File;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class downloadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getContextPath();
        String fileId = req.getParameter("fileId");
        FileDao fDao = FactoryDao.getFileDao();
        File filePo =  fDao.queryByFileId(fileId);
        resp.setHeader("Content-disposition", "attachment; fileName=" + filePo.getFileName());
        java.io.File file = new java.io.File(filePo.getDirectory());
        FileInputStream inputFile = new FileInputStream(file);
        ServletOutputStream outFile = resp.getOutputStream();
        byte[] b = new byte[1024];
        int pos;
        while((pos = inputFile.read(b)) > 0) {
            outFile.write(b, 0, pos);
        }
        outFile.close();
        inputFile.close();
        resp.sendRedirect(path + "displayFile/displayFile");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
