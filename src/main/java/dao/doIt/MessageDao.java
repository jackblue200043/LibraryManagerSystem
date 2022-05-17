package dao.doIt;

import com.mysql.cj.protocol.Message;
import dao.face.IMessageBoard;
import dao.factory.FactoryDao;
import povo.MessageBoard;
import povo.Users;
import util.characterUtil.StringUtil;
import util.dataBaseUtil.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements IMessageBoard {
    /*
    * 工具: 将Message类实例转换为List<String>
    * */
    private List<String> messageToList(MessageBoard ms) {
        List<String> list = new ArrayList<String>();
        list.add(ms.getNum() + "");
        list.add(ms.getBookId());
        list.add(ms.getUserName());
        list.add(ms.getTime());
        list.add(ms.getMessage());
        return list;
    }
    /*
    * 工具: 将ResultSet实例转化为List<Message>;
    * */
    private List<MessageBoard> resultSetToList(ResultSet result) throws SQLException {
        List<MessageBoard> list = new ArrayList<>();
        while(result.next()) {
            MessageBoard ms = new MessageBoard();
            ms.setNum(result.getInt(1));
            ms.setBookId(result.getString(2));
            ms.setUserName(result.getString(3));
            ms.setTime(result.getString(4));
            ms.setMessage(result.getString(5));
            list.add(ms);
        }
        return list;
    }

    @Override
    public int insert(MessageBoard mess)  {
        String sql = "insert into messageBoard(bookId, userName, time, message)" +
                " values(?, ?, ?, ?)";

        List<String> list = messageToList(mess);
        list.remove(0);
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int update(MessageBoard mess)  {
        String sql = "update messageBoard set userName = ?, time = ?, message = ?" +
                "where num = ?";
        DataBaseUtil db = new DataBaseUtil();
        List<String> list = messageToList(mess);
        String num = list.remove(0);
        list.add(num);
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int delete(MessageBoard mess)  {
        String sql = "delete from messageBoard where num = ?";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, mess.getNum());
        db.close();
        return flag;
    }

    @Override
    public List<MessageBoard> queryByUserBookId(String bookId) {
        String sql = "select * from messageBoard where bookId = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, bookId);
        List<MessageBoard> list = null;
        try {
            list = resultSetToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
            return list;
        }
    }

    @Override
    public List<MessageBoard> queryByUserName(String userName) throws SQLException {
        String sql = "Select * from messageBoard where userName = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, userName);
        List<MessageBoard> list = resultSetToList(result);
        db.close();
        return list;
    }

    @Override
    public List<MessageBoard> queryByNikName(String nikName) throws SQLException {
        String sql = "Select * from messageBoard where userName Regexp ?";
        UsersDao usDao = FactoryDao.getUserDao();
        Users user = usDao.queryByNikName(nikName);
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, user.getUserName());
        List<MessageBoard> list = resultSetToList(result);
        db.close();
        return list;
    }
    @Override
    //这里是模糊搜索
    public List<MessageBoard> queryByMessageFuzzy(String message) throws SQLException {
        String sql = "Select * from messageBoard where message = ? ";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(message, "+"));
        List<MessageBoard> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<MessageBoard> queryByNikNameFuzzy(String nikName) throws SQLException {
        String sql = "Select * from messageBoard where userName = ?";
        UsersDao usDao = new UsersDao();
        List<Users> listUser = usDao.queryByNikNameFuzzy(nikName);
        List<MessageBoard> listMessage = new ArrayList<>();
        DataBaseUtil db = new DataBaseUtil();
        for(Users user : listUser) {
            ResultSet result = db.query(sql, user.getUserName());
            List<MessageBoard> list = resultSetToList(result);
            listMessage.addAll(list);
        }
        db.close();
        return listMessage;
    }
}
