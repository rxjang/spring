package com.bit.framework.emp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.bit.framework.emp.model.entity.EmpVo;

public class EmpDao extends JdbcDaoSupport{
	
	public EmpDao() {
	}

	public List<EmpVo> selectAll() throws SQLException{
		String sql="select * from emp";
		
		return getJdbcTemplate().query(sql, new RowMapper<EmpVo>() {
			@Override
			public EmpVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new EmpVo(rs.getInt("empno"),rs.getString("name"),rs.getString("sub"),rs.getDate("nalja"),rs.getInt("pay"));
			}
			
		});
	}

	public void insertOne(String name, String sub, int pay) throws SQLException {
		String sql="insert into emp (name,sub,nalja,pay) values (?,?,now(),?)";
		Object[] params=new Object[] {name,sub,pay};
		getJdbcTemplate().update(sql,params);
	}

	public EmpVo selectOne(int empno) throws SQLException {
		String sql="select * from emp where empno=?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {empno}, new RowMapper<EmpVo>() {

			@Override
			public EmpVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new EmpVo(
						rs.getInt("empno"),rs.getString("name"),rs.getString("sub")
						,rs.getDate("nalja"),rs.getInt("pay")
						);
			}
			
		});
	}
	public int updateOne(int empno, String name, String sub, int pay) throws SQLException {
		final String SQL = "update emp set name=?,sub=?,pay=? where empno=?";
	    return getJdbcTemplate().update(SQL, new Object[] {name,sub,pay,empno});
		
//		String sql="update emp set name=?,sub=?,pay=? where empno=?";
//		try (
//				Connection conn=getConnection();
//				PreparedStatement pstmt=conn.prepareStatement(sql);
//				){
//			pstmt.setString(1,name);
//			pstmt.setString(2,sub);
//			pstmt.setInt(3, pay);
//			pstmt.setInt(4, empno);
//			return pstmt.executeUpdate();
			
//		}
	}
	
	public int deleteOne(int empno) {
		String sql="delete from emp where empno=?";
		return getJdbcTemplate().update(sql,new Object[] {empno});
	}

}
