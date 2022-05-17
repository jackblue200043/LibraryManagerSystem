package dao.doIt;

import com.mysql.cj.conf.DatabaseUrlContainer;
import dao.face.IBooksDao;
import povo.Books;
import povo.Users;
import util.characterUtil.StringUtil;
import util.dataBaseUtil.DataBaseUtil;


import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDao implements IBooksDao {
    /*工具:将Books实例转化为List集合*/
    private List<String> bookToList(Books book) {
        List<String> list = new ArrayList<String>();
        list.add(book.getBookId());
        list.add(book.getBookName());
        list.add(book.getType());
        list.add(book.getPublisher());
        list.add(book.getAuthor());
        list.add(book.getPlace());
        list.add(book.getTime() + "");
        list.add(book.getStatus()+"");
        return list;
    }
    /*
    * 工具: 将ResultSet实例, 转化为List集合;
    * */
    private List<Books> resultSetToList(ResultSet result){
        List<Books> list = new ArrayList<Books>();
        try {
            while(result.next()) {
                Books book = new Books();
                book.setBookId(result.getString(1));
                book.setBookName(result.getString(2));
                book.setType(result.getString(3));
                book.setPublisher(result.getString(4));
                book.setAuthor(result.getString(5));
                book.setPlace(result.getString(6));
                book.setTime(result.getInt(7));
                book.setStatus(Integer.parseInt(result.getString(8)));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }

    }
    @Override
    public int insert(Books book) {
        String sql = "insert into books values(?, ?, ?, ?, ?, ?, ?, ?)";
        List<String> list = bookToList(book);
        DataBaseUtil db = new DataBaseUtil();
        int flag = -1;
        try {
            flag = db.update(sql, list.toArray());
        } finally {
            db.close();
            return flag;
        }
    }

    @Override
    public int update(Books books) {
        String sql = "update books set bookName = ?, type = ?, publisher = ?, author = ?," +
                "place = ?, time = ?, status = ? where bookId = ?";
        List<String> list = bookToList(books);
        String bookId = list.remove(0);
        list.add(bookId);
        DataBaseUtil db = new DataBaseUtil();
        int flag = 0;
        try {
            flag = db.update(sql, list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
            return flag;
        }

    }

    @Override
    public int delete(Books books){
        String sql = "delete from books where bookId = ?";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, books.getBookId());
        db.close();
        return flag;
    }

    @Override
    public Books queryByBookId(String bookId){
        String sql = "select * from books where bookId = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, bookId);
        List<Books> list = resultSetToList(result);
        Books book = null;
        try {
            book = list.get(0);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            db.close();
            return book;
        }

    }

    @Override
    public List<Books> queryByBookName(String bookName) throws SQLException {
        String sql = "select * from books where bookName = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, bookName);
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }

    @Override
    public List<Books> queryByBookType(String bookType) throws SQLException {
        String sql = "select * from books where bookType = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, bookType);
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }

    @Override
    public List<Books> queryByBookPublisher(String publisher) throws SQLException {
        String sql = "select * from books where publisher = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, publisher);
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }

    @Override
    public List<Books> queryByAuthor(String author) throws SQLException {
        String sql = "select * from books where author = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, author);
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<Books> queryByBookNameFuzzy(String bookName) throws SQLException {
        String sql = "select * from books where bookName Regexp ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(bookName, "+"));
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<Books> queryByBookTypeFuzzy(String bookType) throws SQLException {
        String sql = "select * from books where bookType Regexp ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(bookType, "+"));
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<Books> queryByBookPublisherFuzzy(String publisher) throws SQLException {
        String sql = "select * from books where publisher regexp ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(publisher, "+"));
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<Books> queryByAuthorFuzzy(String author) throws SQLException {
        String sql = "select * from books where author regexp ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(author, "+"));
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
    //flag: 是否模糊
    public synchronized List<Books> query(String field, String fieldValue, String flag) throws Exception {
        String sql = "select * from books";
        if (flag.equals("yes")) {
            sql = "select * from books where " + field + " regexp ?";
            fieldValue = StringUtil.insertChar(fieldValue, "+");
        } else {
            sql = "select * from books where " + field + " = ?";
        }
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result =  db.query(sql, fieldValue);
        List<Books> list = resultSetToList(result);
        db.close();
        return list;
    }
}
