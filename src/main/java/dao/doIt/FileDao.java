package dao.doIt;

import dao.face.IFileDao;
import povo.File;
import util.characterUtil.StringUtil;
import util.dataBaseUtil.DataBaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDao implements IFileDao{
    /*
    *工具: 将File类实例转换为代表它的List集合;
    * */
    private List<String> fileToList(File file) {
        List<String> list = new ArrayList<String>();
        list.add(file.getFileId()+"");
        list.add(file.getUserName());
        list.add(file.getFileName());
        list.add(file.getFileType());
        list.add(file.getSize());
        list.add(file.getDirectory());
        return list;
    }
    /*
    * 工具: 将ResultSet对象转换为List<File>;
    * */
    private List<File> resultSetToList(ResultSet result){
        List<File> list = new ArrayList<File>();
        try {
            while(result.next()) {
                File file = new File();
                file.setFileId(result.getInt(1)+0L);
                file.setUserName(result.getString(2));
                file.setFileName(result.getString(3));
                file.setFileType(result.getString(4));
                file.setSize(result.getString(5));
                file.setDirectory(result.getString(6));
                list.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
    @Override
    public int insert(File file) {
        String sql = "insert into file(userName, fileName, fileType, size, directory)" +
                " values(?, ?, ?, ?, ?)";
        List<String> list = fileToList(file);
        list.remove(0);
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int update(File file) {
        String sql = "update file  set userName = ?, fileName = ?, fileType = ?, size = ?, " +
                "directory = ? where fileId = ?";
        List<String> list = fileToList(file);
        String fileId = list.remove(0);
        list.add(fileId);
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, list.toArray());
        db.close();
        return flag;
    }

    @Override
    public int delete(File file) {
        String sql = "delete from file where fileId = ?";
        DataBaseUtil db = new DataBaseUtil();
        int flag = db.update(sql, file.getFileId());
        db.close();
       return flag;
    }

    @Override
    public File queryByFileId(int fileId){
        String sql = "select * from file where fileId = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, fileId);
        List<File> list = resultSetToList(result);
        File file = list.get(0);
        db.close();
        return file;
    }
    public File queryByFileId(String fileId){
       return queryByFileId(Integer.parseInt(fileId));
    }
    @Override
    public List<File> queryByFileName(String fileName) throws SQLException {
        String sql = "select * from file where fileName = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, fileName);
        List<File> list = resultSetToList(result);
        db.close();
        return list;
    }

    @Override
    public List<File> queryByFileType(String fileType) throws SQLException {
        String sql = "select * from file where fileType = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, fileType);
        List<File> list = resultSetToList(result);
        db.close();
        return list;
    }

    public List<File> queryByFileNameFuzzy(String fileName) {
        String sql = "select * from file where fileName Regexp ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, StringUtil.insertChar(fileName, "+"));
        List<File> list = resultSetToList(result);
        db.close();
        return list;
    }
    public List<File> queryByUserName(String userName) {
        String sql = "select * from file Where userName = ?";
        DataBaseUtil db = new DataBaseUtil();
        ResultSet result = db.query(sql, userName);
        List<File> list = resultSetToList(result);
        db.close();
        return list;
    }
}
