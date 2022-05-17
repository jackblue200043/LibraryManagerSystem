package dao.doIt;

import dao.face.IUsersDao;
import povo.Users;
import util.characterUtil.StringUtil;
import util.dataBaseUtil.DataBaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements IUsersDao{
    /*
     *  作用: 此方法将查询的ResultSet类型数据包装称为Users对象, 然后添加到List集合中;
     * */
    private List<Users> resultToUserList(ResultSet result) throws SQLException {
        List<Users> list = new ArrayList<Users>();
        try {
            while (result.next()) {
                Users user = new Users();
                user.setUserName(result.getString(1));
                user.setNikName(result.getString(2));
                user.setPassWord(result.getString(3));
                user.setSex(result.getString(4));
                user.setAddress(result.getString(5));
                user.setPhoneNumber(result.getString(6));
                user.setStatus(result.getString(7));
                list.add(user);
            }
        }finally {
            return list;
        }

    }
    /*工具: 将Users实例转换为一个List集合;
    *
    * */
    private List<String> userToList(Users user) {
        List<String> list = new ArrayList<String>();
        String userName = user.getUserName();
        String nikName = user.getNikName();
        String passWord = user.getPassWord();
        String sex = user.getSex();
        String address = user.getAddress();
        String phoneNumber = user.getPhoneNumber();
        String status = user.getStatus();
        if (sex == null) {
            sex = "不详";
        }
        if (address == null) {
            address = "不详";
        }
        if (phoneNumber == null) {
            phoneNumber = "不详";
        }
        list.add(userName);
        list.add(nikName);
        list.add(passWord);
        list.add(sex);
        list.add(address);
        list.add(phoneNumber);
        list.add(status);
        return list;
    }
    @Override
    public int insert(Users user) {
        String sql = "insert into users values (?, ?, ?, ?, ?, ?, ?)";
        List<String> list= userToList(user);
        DataBaseUtil db;
        int flag = -1;
        db = new DataBaseUtil();
        try {
            flag = db.update(sql, list.toArray());
        } finally {
            db.close();
            return flag;
        }
    }

    @Override
    public int update(Users user) {
        String sql = "update users set nikName = ?, passWord = ?, " +
                "sex = ?, address = ?, phoneNumber = ?, status = ? where userName = ?";
        List<String> list= userToList(user);
        String userName = list.remove(0);
        list.add(userName);
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int delete(String userId) {
        String sql = "delete from users where userName = ?";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, userId);
        db.close();
        return flag;
    }

    @Override
    public Users queryByUserName(String userName){
        String sql = "select * from users where userName = ?";
        Users user = null;
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, userName);
        List<Users> list = null;
        try {
            list = resultToUserList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
            if (list.size() > 0)  user = list.get(0);
            return user;
        }

    }

    @Override
    public Users queryByNikName(String nikName) throws SQLException {
        String sql = "select * from users where nikName = ?";
        Users user;
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, nikName);
        List<Users> list = resultToUserList(result);
        user = list.get(0);
        db.close();
        return user;
    }

    @Override
    public List queryAll() throws SQLException {
        String sql = "select * from users";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql);
        List<Users> list= resultToUserList(result);
        db.close();
        return list;
    }
    public List<Users> queryByNikNameFuzzy(String nikName) throws SQLException {
        String sql = "select * from users where nikName regexp ?";
        Users user;
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(nikName, "+"));
        List<Users> list = resultToUserList(result);
        db.close();
        return list;
    }
}
