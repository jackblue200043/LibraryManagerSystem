package dao.face;

import povo.MessageBoard;

import java.util.List;

public interface IMessageBoard {
    //增加留言
    public int insert(MessageBoard mess) throws Exception;
    //修改留言
    public int update(MessageBoard mess) throws Exception;
    //删除留言
    public int delete(MessageBoard mess) throws Exception;
    //通过bookId查看留言
    public List<MessageBoard> queryByUserBookId(String bookId) throws Exception;
    //通过userName查看留言
    public List<MessageBoard> queryByUserName(String userName) throws Exception;
    //通过昵称查询留言
    public List<MessageBoard> queryByNikName(String nikName) throws Exception;
    //通过message模糊查询留言
    public List<MessageBoard> queryByMessageFuzzy(String Message) throws Exception;
}
