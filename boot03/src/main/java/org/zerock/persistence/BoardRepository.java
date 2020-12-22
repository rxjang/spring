package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>,QuerydslPredicateExecutor<Board>{

	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);
	
	public List<Board> findBoardByTitle(String title);
	
	public Collection<Board> findByWriter(String wirter);
	
	// 작성자에 대한 like % 키워드 %
	public Collection<Board> findByWriterContaining(String writer);
	
	//or의 조건처리
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	//title Lile % ? % and BNO > ?
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
	
	// bno > ? order by bno desc
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// bno > ? oreder by bno desc limit ?,?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno,Pageable paging);
	
//	public List<Board> findByBnoGreaterThan(Long bno,Pageable paging);
	
	public Page<Board> findByBnoGreaterThan(Long bno,Pageable paging);
	
	@Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String content);
	
	@Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByWriter2(String writer);
	
	@Query("SELECT b.bno, b,title, b.writer, b.regdate FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle2(String title);
	
	@Query(value = "SELECT bno, title, writer from tbl_boards WHERE title LIKE CONCAT('%', ?1, '%') AND bno > 0 ORDER BY bno DESC",
			nativeQuery=true)
	public List<Object[]> findByTitle3(String title);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByPage(Pageable pageable);
}
