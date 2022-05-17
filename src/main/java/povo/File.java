package povo;


import java.io.Serializable;

public class File implements Serializable {

  private Long fileId;
  private String userName;
  private String fileName;
  private String fileType;
  private String size;
  private String directory;

    public File() {
    }

    @Override
  public String toString() {
    return "File{" +
            "fileId=" + fileId +
            ", userName='" + userName + '\'' +
            ", fileName='" + fileName + '\'' +
            ", fileType='" + fileType + '\'' +
            ", size='" + size + '\'' +
            ", directory='" + directory + '\'' +
            '}';
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }


  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }


  public String getDirectory() {
    return directory;
  }

  public void setDirectory(String directory) {
    this.directory = directory;
  }

}
