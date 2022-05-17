package service;

import povo.Users;

import javax.servlet.http.HttpServletRequest;

public class UserPovo {
    public static Users getUserByReq(HttpServletRequest req) {
        Users user = new Users();
        String userName, nikName, passWord, sex, address, phoneNumber, status;
        userName = req.getParameter("userName");
        nikName = req.getParameter("nikName");
        passWord = req.getParameter("passWord");
        sex = req.getParameter("sex");
        address = req.getParameter("address");
        phoneNumber = req.getParameter("phoneNumber");
        status = req.getParameter("status");
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setNikName(nikName);
        user.setSex(sex);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setStatus(status);
        return user;
    }
}
