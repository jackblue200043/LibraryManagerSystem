package dao.factory;

import dao.doIt.*;

public class FactoryDao {
    public static BooksDao getBookDao() {
        return new BooksDao();
    }
    public static BorrowDao getBorrowDao() {
        return new BorrowDao();
    }
    public static FileDao getFileDao() {
        return new FileDao();
    }
    public static MessageDao getMessageDao() {
        return new MessageDao();
    }
    public static UsersDao getUserDao() {
        return new UsersDao();
    }
}
