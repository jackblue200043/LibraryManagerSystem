package povo;


import java.io.Serializable;

public class Borrow implements Serializable {

  private int num;
  private String userName;
  private String bookId;
  private String start;
  private int timeLeft;
  private int  flag;

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }


  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }


  public long getTimeLeft() {
    return timeLeft;
  }

  public void setTimeLeft(int timeLeft) {
    this.timeLeft = timeLeft;
  }


  public long getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "Borrow{" +
            "userName='" + userName + '\'' +
            ", bookId='" + bookId + '\'' +
            ", start='" + start + '\'' +
            ", timeLeft=" + timeLeft +
            ", flag=" + flag +
            '}';
  }
}
