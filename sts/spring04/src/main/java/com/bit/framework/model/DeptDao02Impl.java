package com.bit.framework.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bit.framework.model.entity.DeptVo;

public class DeptDao02Impl implements DeptDao{
	JdbcTemplate jdbcTemplate;
	PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	private RowMapper rowMapper=new RowMapper<DeptVo>() {

		public DeptVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new DeptVo(rs.getInt(1),rs.getString(2),rs.getString(3));
		}
		
	};
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<DeptVo> selectAll() throws SQLException{
		final String sql="select * from dept order by deptno desc";
		PreparedStatementCreator psc=null;
		psc=new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql);
			}
			
		};
		return jdbcTemplate.query(psc, rowMapper);
		
	}

	public DeptVo selectOne(final int key) throws SQLException {
		final String sql="select * from dept where deptno=?";
		
		PreparedStatementCreator psc=null;
		psc=new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, key);
				return pstmt;
			}
			
		};
		return (DeptVo) jdbcTemplate.query(psc,rowMapper).get(0);
	}

	public void insertOne(final DeptVo bean) throws SQLException {
		final String sql="insert into dept (dname,loc) values (?,?)";
		final String sql2="insert into dept (dname,loc) values (?,?)";
//		final String sql2="insert into dept (dname,loc) values (1,?,?)";	//에러->롤백시킴
		
		PreparedStatementCreator psc=null;
		TransactionStatus status=null;
		TransactionDefinition definition=null;
		definition=new DefaultTransactionDefinition();
		status=transactionManager.getTransaction(definition);
		try {
			psc=new PreparedStatementCreator() {
	
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					System.out.println(con);
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setString(1, bean.getDname());
					pstmt.setNString(2, bean.getLoc());
					return pstmt;
				}
				
			};
			jdbcTemplate.update(psc);
			psc=new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					System.out.println(con);
					PreparedStatement pstmt=con.prepareStatement(sql2);
					pstmt.setString(1, bean.getDname());
					pstmt.setNString(2, bean.getLoc());
					return pstmt;
				}
				
			};
			jdbcTemplate.update(psc);
			transactionManager.commit(status);
		}catch(Exception e) {
			transactionManager.rollback(status);
			
		}
	}

	public int updateOne(DeptVo bean) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteOne(int key) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}	
