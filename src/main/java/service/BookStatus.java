package service;

import dao.doIt.BooksDao;
import dao.doIt.BorrowDao;
import dao.factory.FactoryDao;
import povo.Borrow;

import java.util.List;

public class BookStatus {
    public static boolean bookExist(String bookId) {
        BorrowDao borrowDao = FactoryDao.getBorrowDao();
        List<Borrow> list = borrowDao.queryByBookId(bookId);
        //通过书本id, 在借阅记录中可以查看到很多的记录,
        //如果没有查询到, 那么是有的状态;
        // 如果有记录,包括还了的, 没有还的;所以要检查每一条记录, 判断是否归还;
        if (list == null) {
            return true;
        }
        for(Borrow b:list) {
            if (b.getFlag() <= 0) {
                return false;
            }
        }
        return  true;
    }
}
