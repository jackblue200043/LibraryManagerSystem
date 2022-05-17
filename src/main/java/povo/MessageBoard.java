package povo;


import java.io.Serializable;

public class MessageBoard implements Serializable {

  private long num;
  private String bookId;
  private String userName;
  private String time;
  private String message;

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "MessageBoard{" +
            "num=" + num +
            ", bookId='" + bookId + '\'' +
            ", userName='" + userName + '\'' +
            ", time='" + time + '\'' +
            ", message='" + message + '\'' +
            '}';
  }
}
