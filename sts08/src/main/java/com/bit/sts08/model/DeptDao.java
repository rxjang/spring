package com.bit.sts08.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import com.bit.sts08.model.entity.DeptVo;

public interface DeptDao {

	@Select("select * from dept03 order by deptno desc")
	List<DeptVo> selectAll() throws DataAccessException;
	
	@Select("select * from dept03 where deptno=#{val}")
	DeptVo selectOne(int deptno) throws DataAccessException;
	
	@Insert("insert into dept03 (dname,loc) values (#{dname},#{loc})")
	void insertOne(DeptVo bean) throws DataAccessException;
	
	@Update("update dept03 set dname=#{dname},loc=#{loc} where deptno=#{deptno}")
	int updateOne(DeptVo bean) throws DataAccessException;
	
	@Delete("delete from dept03 where deptno=#{deptno}")
	int deleteOne(@Param("deptno") int deptno) throws DataAccessException;
	
	@Select("select count(*) from dept03")
	int selectSize() throws DataAccessException;
}
