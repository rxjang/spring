package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

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
}
