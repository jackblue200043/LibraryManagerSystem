package servlet;

import dao.doIt.FileDao;
import dao.factory.FactoryDao;
import povo.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class viewOneFileByTxtImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取项目就对路径
        String path = req.getContextPath();
        //使用fileid查看文件类型, 如果是txt, 那么使用输出流输出, 如果是图片使用img表现
        String fileId = req.getParameter("fileId");
        FileDao fDao = FactoryDao.getFileDao();
        File filePo = fDao.queryByFileId(fileId);
        if (filePo != null) {
            //获取真实路径
            HttpSession session = req.getSession();
            String pathReal = this.getServletContext().getRealPath("/tempDir");
            //先将图片移动到此servlet中的一个临时文件夹, 然后, 再使用下一个页面访问;
            //创建临时文件夹
            java.io.File fileDir = new java.io.File(pathReal);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            //下面读取txt文本文件
            if (filePo.getFileType().equals("txt")) {
                //创建输入流
                FileInputStream inputF = new FileInputStream(filePo.getDirectory());
                //创建临时文件
                java.io.File fileTxt = new java.io.File(fileDir, filePo.getFileId() + filePo.getFileName());
                fileTxt.deleteOnExit();
                FileOutputStream outputF = new FileOutputStream(fileTxt);
                //使用字符输入流读取数据
                byte[] b = new byte[1024];
                int pos;
                while ((pos = inputF.read(b)) > 0) {
                    outputF.write(b, 0, pos);
                }
                outputF.close();
                inputF.close();
                resp.sendRedirect("../tempDir/"+filePo.getFileId()+filePo.getFileName());
            } else {
                fileDir.deleteOnExit();
                java.io.File fileImg = new java.io.File(fileDir, filePo.getFileId()+filePo.getFileName());
                fileImg.deleteOnExit();
                FileInputStream input = new FileInputStream(filePo.getDirectory());
                FileOutputStream out = new FileOutputStream(fileImg);
                //将文件写入临时文件out中
                byte[] b = new byte[1024];
                int pos;
                while ((pos = input.read(b)) > 0) {
                    out.write(b, 0, pos);
                }
                input.close();
                out.close();
                req.setAttribute("imgSrc", filePo.getFileId()+filePo.getFileName());
                req.getRequestDispatcher("/displayFile/displayByImg.jsp").forward(req, resp);
            }
        }else {
            PrintWriter out = resp.getWriter();
            out.println("<h1>"+ "文件读取失败"+"</h1>");
        }
    }
}
