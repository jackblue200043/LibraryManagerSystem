package povo;


import java.io.Serializable;

public class Users implements Serializable {

  private String userName;
  private String nikName;
  private String passWord;
  private String sex;
  private String address;
  private String phoneNumber;
  private String status;


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getNikName() {
    return nikName;
  }

  public void setNikName(String nikName) {
    this.nikName = nikName;
  }


  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Users{" +
            "userName='" + userName + '\'' +
            ", nikName='" + nikName + '\'' +
            ", passWord='" + passWord + '\'' +
            ", sex='" + sex + '\'' +
            ", address='" + address + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
  }
}
