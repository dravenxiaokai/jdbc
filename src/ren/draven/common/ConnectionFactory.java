package ren.draven.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

/**
 * 数据库连接工厂类
 * @author dravenxiaokai
 *
 */
public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	/**
	 * 静态块
	 * 类加载的时候只执行一次
	 */
	static {
		Properties properties = new Properties();
		InputStream iStream = ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		try {
			properties.load(iStream);
			DRIVER = properties.getProperty("mysql.driver");
			URL = properties.getProperty("mysql.url");
			USER = properties.getProperty("mysql.user");
			PASSWORD = properties.getProperty("mysql.password");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("数据库连接信息错误");
		}
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			//加载driver驱动器
			Class.forName(DRIVER);
			connection = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		System.out.println(ConnectionFactory.getConnection());
	}
}
