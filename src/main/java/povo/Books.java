package povo;


import java.io.Serializable;

public class Books implements Serializable {

  private String bookId;
  private String bookName;
  private String type;
  private String publisher;
  private String author;
  private String place;
  private int status;
  private int time;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }




  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }


  @Override
  public String toString() {
    return "Books{" +
            "bookId='" + bookId + '\'' +
            ", bookName='" + bookName + '\'' +
            ", type='" + type + '\'' +
            ", publisher='" + publisher + '\'' +
            ", author='" + author + '\'' +
            ", place='" + place + '\'' +
            ", status=" + status +
            ", time=" + time +
            '}';
  }
}