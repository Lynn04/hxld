package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

/**
 * 用户Dao类
 * @author lynn
 *
 */
public class UserDao {
	
	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception{
		User resultUser = null;
		String sql = "select * from user where username=? and password=?"; // 获取user表格
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName()); // 界面传来的数据
		pstmt.setString(2, user.getPassWord());
		ResultSet rs = pstmt.executeQuery();
		// 判断结果集是否有下一条记录，如果查到，就对其实例化
		if(rs.next()) { 
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassWord(rs.getString("password"));
		}
		return resultUser;
	}

}
