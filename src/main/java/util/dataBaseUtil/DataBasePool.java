package util.dataBaseUtil;


import com.mchange.v2.c3p0.*;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/**
 *  DataBasePool类使用xx.properties文件加载数据库连接配置信息,
 *  进行数据库连接, 获得数据库连接对象Connection;
 */
public class DataBasePool {
    private static ComboPooledDataSource ds;
    private static String driver, url, userName, passWord;

    static {
        Properties prop = new Properties();
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "config.properties";
            System.out.println(path);
            prop.load( new FileInputStream(path));
            driver = prop.getProperty("dataBase.driver");
            url = prop.getProperty("dataBase.url");
            userName = prop.getProperty("dataBase.userName");
            passWord = prop.getProperty("dataBase.passWord");
            prop.clear();
            //设置连接所需要的驱动
            ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            //设置连接数据库所需要的url
            ds.setJdbcUrl(url);
            //设置数据库用户名,密码
            ds.setUser(userName);
            ds.setPassword(passWord);
            //设置连接池最大连接数
            ds.setMaxPoolSize(40);
            //设置连接词最下连接数
            ds.setMinPoolSize(2);
            //设置连接池的初始化连接数
            ds.setInitialPoolSize(10);
            //设置连接池Statement的最大缓存数;
            ds.setMaxStatements(40);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("properties文件打开异常!");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            System.err.println("连接池建立失败!");
        }
    }

    /**
     * 获取数据连接对象
     * @return 返回Connection对象;
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException {
            return ds.getConnection();
    }
    public DataBasePool() {

    }
}
