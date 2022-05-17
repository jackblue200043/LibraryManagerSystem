package dao.doIt;

import dao.face.IBorrowDao;
import dao.factory.FactoryDao;
import povo.Books;
import povo.Borrow;
import povo.Users;
import util.dataBaseUtil.DataBaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao implements IBorrowDao {
    /*
     * 工具: 将Borrow javaBean转换为List<String>;
     * */
    private List<String> borrowToList(Borrow borrow) {
        List<String> list = new ArrayList<>();
        list.add(borrow.getNum()+"");
        list.add(borrow.getUserName());
        list.add(borrow.getBookId());
        list.add(borrow.getStart());
        list.add(borrow.getTimeLeft() + "");
        list.add(borrow.getFlag() + "");
        return list;
    }

    /*
     * 工具: 将ResultSet转换为List<Borrow>
     * */
    private List<Borrow> ResultSetToList(ResultSet result) throws SQLException {
        List<Borrow> list = new ArrayList<Borrow>();
        while (result.next()) {
            Borrow borrow = new Borrow();
            borrow.setNum(Integer.parseInt(result.getString(1)));
            borrow.setUserName(result.getString(2));
            borrow.setBookId(result.getString(3));
            borrow.setStart(result.getString(4));
            borrow.setTimeLeft(Integer.parseInt(result.getString(5)));
            borrow.setFlag(Integer.parseInt(result.getString(6)));
            list.add(borrow);
        }
        return list;
    }

    @Override
    public int insert(Borrow borrow){
        String sql = "insert into borrow values(?, ?, ?, ?, ?)";
        List<String> list = borrowToList(borrow);
        DataBaseUtil db = new DataBaseUtil();
        list.remove(0);
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int update(Borrow borrow){
        String sql = "update borrow set start = ?, timeLeft = ?," +
                "flag = ? where num = ?";
        List<String> list = borrowToList(borrow);
        String userName = list.remove(0);
        String bookId = list.remove(0);
        list.add(userName);
        list.add(bookId);
        String start = borrow.getStart();
        String timeLeft = borrow.getTimeLeft() + "";
        String flagb = borrow.getFlag() + "";
        String num = borrow.getNum() + "";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, start, timeLeft, flagb, num);
        db.close();
        return flag;
    }

    @Override
    public int delete(Borrow borrow){
        String sql = "delete from borrow where num = ?";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, borrow.getNum());
        db.close();
        return flag;
    }

    @Override
    public List<Borrow> queryByUserName(String userName) {
        String sql = "select * from borrow where userName = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, userName);
        List<Borrow> list = null;
        try {
            list = ResultSetToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
            return list;
        }

    }

    @Override
    public List<Borrow> queryByNikName(String nikName) throws SQLException {
        String sql = "select * from borrow where nikName = ?";
        List<Users> listUsers = FactoryDao.getUserDao().queryByNikNameFuzzy(nikName);
        List<Borrow> listBorrow = new ArrayList<Borrow>();
        DataBaseUtil db = new DataBaseUtil();
        for (Users user : listUsers) {
            ResultSet result = db.query(sql, user.getNikName());
            List<Borrow> list = ResultSetToList(result);
            listBorrow.addAll(list);
        }
        db.close();
        return listBorrow;
    }
    @Override
    public List<Borrow> queryByBookName(String bookName) throws SQLException {
        String sql = "select * from borrow where bookId = ?";
        BooksDao booksDao = new BooksDao();
        //获取指定书名list集合
        List<Books> listBook = booksDao.queryByBookNameFuzzy(bookName);
        List<Borrow> list = new ArrayList<Borrow>();
        DataBaseUtil db = new DataBaseUtil();
        for (Books b : listBook) {
            ResultSet result = db.query(sql, b.getBookId());
            List<Borrow> listBorrow = ResultSetToList(result);
            list.addAll(listBorrow);
        }
        db.close();
        return list;
    }
    @Override
    public List<Borrow> queryByBookId(String bookId){
        String sql = "select * from borrow where bookId = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, bookId);
        List<Borrow> list = null;
        try {
            list = ResultSetToList(result);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            db.close();
            return list;
        }
    }
    public List<Borrow> queryByBorrowId(String id) {
        String sql = "select * from borrow where num = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, id);
        List<Borrow> list = null;
        try {
            list = ResultSetToList(result);
        } catch (SQLException e) {
            //e.printStackTrace();
        } finally {
            db.close();
            return list;
        }
    }
}
