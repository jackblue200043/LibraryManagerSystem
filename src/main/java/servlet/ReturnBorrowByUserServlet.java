package servlet;

import dao.doIt.BorrowDao;
import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Borrow;
import povo.Users;
import util.characterUtil.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReturnBorrowByUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        //解决中文乱码
        userName = StringUtil.changeChar(userName);
        //查询数据库, 如果有此用户那么返回true
        BorrowDao borrDao = FactoryDao.getBorrowDao();
        List<Borrow> list = borrDao.queryByUserName(userName);
        StringBuffer strBuff = new StringBuffer();
        if (list != null && list.size() > 0) {
           for (Borrow b: list) {
               if (b.getFlag() <= 0){
                   strBuff.append(b.getNum()+":");
                   if (b.getTimeLeft() <= 0) {
                       strBuff.append("逾");
                   }
                   strBuff.append(b.getTimeLeft());
                   strBuff.append(":"+b.getBookId());
                   strBuff.append(",");
               }
           }
        } else {
            strBuff.append("无记录! ");
        }
//        resp.setHeader("Cache-Control","no-store");//HTTP1.1
//        resp.setHeader("Pragma","no-cache");//HTTP1.0
//        resp.setDateHeader("Expires",1);//禁止在服务器中缓存
        String str = new String(strBuff);
        resp.getWriter().write(str);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
