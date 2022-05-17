package service;

import povo.Books;

import javax.servlet.http.HttpServletRequest;

public class BookPovo {
    public static Books getBookByReq(HttpServletRequest req)
    {
        String bookId, bookName, type, publisher, author, place, time;
        Books book = new Books();
        bookId = req.getParameter("bookId");
        bookName = req.getParameter("bookName");
        type = req.getParameter("type");
        publisher = req.getParameter("publisher");
        author = req.getParameter("author");
        time = req.getParameter("time");
        place = req.getParameter("place");
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setType(type);
        book.setPublisher(publisher);
        book.setAuthor(author);
        book.setTime(Integer.parseInt(time));
        book.setPlace(place);
        book.setStatus(1);
        return book;
    }
}
