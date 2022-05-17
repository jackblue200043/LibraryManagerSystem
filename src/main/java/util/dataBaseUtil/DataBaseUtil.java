package util.dataBaseUtil;


import java.sql.*;

/**
 * 使用数据库连接池类的静态方法获取数据库对象, 并使用
 * Connection对象进行执行sql语句;
 */
public class DataBaseUtil {
	private static Connection conn=null;
	private static PreparedStatement stmt=null;
	private static ResultSet result=null;
	//获取连接对象
	public Connection getConnect(){
		return conn;
	}

	/**
	 * 使用sql语句, 和先言陈述对象进行sql查询, 并返回sql对象;
	 * @param sql 查询 sql语句
	 * @param params ?替代的值
	 * @return ResultSet事例对象;
	 */
	public ResultSet query(String sql,Object... params){
		if(conn!=null){
			try {
				stmt=conn.prepareStatement(sql);
				for(int i=0;(params!=null)&&(i<params.length);i++)
					stmt.setObject(i+1, params[i]);
				result=stmt.executeQuery();
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[错误]:数据库查询失败...");
			}
		}
		return result;
	}

	/**
	 *  使用sql进行更新/删除操作
	 * @param sql alert / update /insert语句进行表的操作
	 * @param params ?替代的值
	 * @return 影响的行数
	 */
	public int update(String sql,Object... params){
		int result=-1;
		if(conn!=null){
			try {
				stmt=conn.prepareStatement(sql);
				for(int i=0;(params!=null)&&(i<params.length);i++)
					stmt.setObject(i+1, params[i]);
				result=stmt.executeUpdate();
			} 
			catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[错误]:数据库更新/增加失败...");
			}finally {
				return result;
			}
		}
			return result;
	}

	/**
	 * 关闭数据库, 并释放资源;
	 */
	public void close(){
		try {
			if(result!=null){
				result.close();
				result=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("[错误]:释放数据库资源失败...");
		}
	}

	public DataBaseUtil(){
		try {
			conn = DataBasePool.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
	}

	//private static String driver, url, userName, passWord;

//	static {
//		Properties prop = new Properties();
//		try {
//			prop.load(DataBaseUtil.class.getResourceAsStream("config.properties"));
//			driver = prop.getProperty("dataBase.driver");
//			url = prop.getProperty("dataBase.url");
//			userName = prop.getProperty("dataBase.userName");
//			passWord = prop.getProperty("dataBase.passWord");
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userName, passWord);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println("properties文件打开异常!");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
