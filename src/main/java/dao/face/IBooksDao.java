package dao.face;

import povo.Books;

import java.util.List;

public interface IBooksDao {
    //插入图书数据
    public int  insert(Books book) throws Exception;
    //更新图书数据
    public int update(Books books) throws Exception;
    //删除图书数据
    public int delete(Books books) throws Exception;
    //通过书名查询数据
    public Books queryByBookId(String bookId) throws Exception;
    //通过书名查找图书
    public List<Books> queryByBookName(String bookName) throws Exception;
    //通过图书类型查询数据
    public List<Books> queryByBookType(String BookType) throws Exception;
    //通过出版社查询数据
    public List<Books> queryByBookPublisher(String publisher) throws Exception;
    //通过作者查询数据
    public List<Books> queryByAuthor(String author) throws Exception;
}
