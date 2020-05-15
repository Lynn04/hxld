package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类
 * @author lynn
 *
 */

public class MySqlUtil {

	private String url = "jdbc:mysql://localhost:3306/TimetableSystem"; // 连接数据库
	private String userName = "root"; // 用户名
	private String passWord = "zxcvbnm123"; // 密码
	private String jdbcName = "com.mysql.cj.jdbc.Driver"; // 驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(url, userName, passWord);
		return con;
	}
	
	/**
	 * 关闭数据库
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if(con!=null) {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		MySqlUtil util = new MySqlUtil();
		try {
			util.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
