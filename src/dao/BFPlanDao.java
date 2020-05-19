package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Plan;
import util.StringUtil;

public class BFPlanDao {
	/**
	 * 计划时间添加
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Plan plan) throws Exception{
		String sql = "insert into bfplan value(null, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, plan.getTime()); // 界面传来的数据
		return pstmt.executeUpdate(); // return更新的条数
	}
	
	/**
	 * 查询计划时间集合
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public ResultSet planlist(Connection con, Plan plan) throws Exception{
		StringBuffer sql = new StringBuffer("SELECT @i:=@i+1 AS rownum, time,id FROM bfplan,(SELECT @i:=0) VARS");
		if(StringUtil.isNotEmpty(plan.getTime())) {
			sql.append(" where time like '%"+plan.getTime()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 计划时间数组
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public String[] planArr(Connection con) throws Exception{
		String sql = "select * from bfplan";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建一个PreparedStatement对象
		ResultSet rs = pstmt.executeQuery();// 创建结果集
		
		int planNum = 0;
		while(rs.next()) {
			planNum ++;
		}
		String[] planArr = new String[planNum];
		rs = pstmt.executeQuery();// 特别重要，否则取到的全是0。因为执行上面的while(rs.next())后，ResultSet对象的下标已指到0。
		for(int i=0; rs.next(); i++) {
			planArr[i] = rs.getString("time"); //使用rs时，一定要用rs.next来移动光标，使其下移一行
		}
		return planArr;
	}
	
	/**
	 * 船只删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con, String id) throws Exception{
		String sql = "delete from bfplan where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id); // 界面传来的数据
		int deleteNum = pstmt.executeUpdate(); // return更新的条数
		
		return deleteNum;
		
	}
	
	/**
	 * 更新计划
	 * @param con
	 * @param ship
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con, Plan plan) throws Exception{
		String sql = "update bfplan set time=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, plan.getTime()); // 界面传来的数据
		pstmt.setInt(2, plan.getId());
		return pstmt.executeUpdate(); // return更新的条数
	}

}
