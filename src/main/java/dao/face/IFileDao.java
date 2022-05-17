package dao.face;

import povo.File;

import java.util.List;

public interface IFileDao {
    //插入文件数据;
    public int insert(File file) throws Exception;
    //更改文件数据;
    public int update(File file) throws Exception;
    //删除文件数据;
    public int delete(File file) throws Exception;
   //使用fileId查找文件;
    public File queryByFileId(int fileId) throws Exception;
    //通过文件名查找文件;
    public List<File> queryByFileName(String fileName) throws Exception;
    //通过文件类型查找文件;
    public List<File> queryByFileType(String fileType) throws Exception;
}
