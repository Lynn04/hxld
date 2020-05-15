package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Ship;
import util.StringUtil;

/**
 * 船只Dao类
 * @author lynn
 *
 */
public class ShipDao {

	/**
	 * 船只添加
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Ship ship) throws Exception{
		String sql = "insert into ship value(null, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ship.getShipName()); // 界面传来的数据
		return pstmt.executeUpdate(); // return更新的条数
	}
	
	/**
	 * 查询船只集合
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public ResultSet shiplist(Connection con, Ship ship) throws Exception{
		StringBuffer sql = new StringBuffer("SELECT @i:=@i+1 AS rownum, shipname,id FROM ship,(SELECT @i:=0) VARS");
		if(StringUtil.isNotEmpty(ship.getShipName())) {
			sql.append(" where shipname like '%"+ship.getShipName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 船只删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String shipname) throws Exception{
		String sql = "delete from ship where shipname=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, shipname); // 界面传来的数据
		return pstmt.executeUpdate(); // return更新的条数
	}
	
	/**
	 * 更新船只
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Ship ship) throws Exception{
		String sql = "update ship set shipname=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ship.getShipName()); // 界面传来的数据
		pstmt.setInt(2, ship.getId());
		return pstmt.executeUpdate(); // return更新的条数
	}
	
	/**
	 * 船只数组
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public String[] shipArr(Connection con) throws Exception{
		String sql = "select * from ship";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建一个PreparedStatement对象
		ResultSet rs = pstmt.executeQuery();// 创建结果集
		
		int shipNum = 0;
		while(rs.next()) {
			shipNum ++;
		}
		String[] shipArr = new String[shipNum];
		rs = pstmt.executeQuery();// 特别重要，否则取到的全是0。因为执行上面的while(rs.next())后，ResultSet对象的下标已指到0。
		for(int i=0; rs.next(); i++) {
			shipArr[i] = rs.getString("shipname"); //使用rs时，一定要用rs.next来移动光标，使其下移一行
		}
		return shipArr;
	}
	
}
