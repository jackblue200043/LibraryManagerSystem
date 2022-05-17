package service;

import dao.doIt.UsersDao;
import dao.factory.FactoryDao;
import povo.Users;
import servlet.LoginServlet;

import java.sql.SQLException;

public class LoginVerify {
    /**
     * 查看用户名是否在此数据库中存在
     * @param userName 用户名
     * @return 存在返回true, 不存在返回false;
     */
    public static Boolean isUsers(String userName) {
        UsersDao usrDao = FactoryDao.getUserDao();
        boolean flag = false;
        Users user = usrDao.queryByUserName(userName);
        if (user == null) return false;
        return true;
    }
    /**
     * 查询数据库指定用户的用户名
     * @return
     */
    public static Users getUsers(String userName) {
        UsersDao userDao = FactoryDao.getUserDao();
        Users users = null;
        if (LoginVerify.isUsers(userName)) {
            users = userDao.queryByUserName(userName);
        }
        return users;
    }

    public static void main(String[] args) {
        //boolean flag = LoginVerify.isUsers("123456");
        //System.out.println(flag);
        Users user = LoginVerify.getUsers("123456");
        if(user != null) System.out.println(user);
    }
}
