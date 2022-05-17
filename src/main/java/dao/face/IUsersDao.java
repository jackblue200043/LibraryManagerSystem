package dao.face;

import povo.Users;
import java.util.List;

public interface IUsersDao {
    //插入数据;
    public int insert(Users user) throws Exception;
    //更新数据;
    public int update(Users user) throws Exception;
    //根据用户名删除数据;
    public int delete(String userId) throws Exception;
    //返回全部数据
    public List queryAll() throws Exception;
    //按usrName查找数据
    public Users queryByUserName(String userName) throws Exception;
    //通过昵称查询数据
    public Users queryByNikName(String nikName) throws Exception;
}
