package dao.face;

import povo.Borrow;

import java.util.List;

public interface IBorrowDao {
    //添加借阅信息
    public int insert(Borrow borrow) throws Exception;
    //修改借阅信息
    public int update(Borrow borrow) throws Exception;
    //删除借阅信息
    public int delete(Borrow borrow) throws Exception;
    //通过用户名查找借阅信息
    public List<Borrow> queryByUserName(String userName) throws Exception;
    //通过昵称查找借阅信息
    public List<Borrow> queryByNikName(String nikName) throws Exception;
    //通过书号查找借阅信息
    public List<Borrow> queryByBookId(String bookId) throws Exception;
    //通过书名查找借阅信息
    public List<Borrow> queryByBookName(String BookName) throws Exception;
}
