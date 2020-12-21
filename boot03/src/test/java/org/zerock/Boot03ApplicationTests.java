package org.zerock;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@SpringBootTest
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void testInsert200() {
		for(int i = 1; i <=200 ; i++) {
			Board board = new Board();
			board.setTitle("제목.." + i);
			board.setContent("내용..."+ i + " 채우기 ");
			board.setWriter("user0"+(i%10));
			boardRepository.save(board);
		}
	}
	
	@Test
	public void testByTitle() {
		boardRepository.findBoardByTitle("제목..177").forEach(board -> System.out.println(board));
	}

	@Test
	public void testByWriter() {
		Collection<Board> results=boardRepository.findByWriter("user00");
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriterContaining() {
		Collection<Board> results = boardRepository.findByWriterContaining("05");
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		Collection<Board> results=boardRepository.findByTitleContainingOrContentContaining("19", "99");
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testfindByTitleContainingAndBnoGreaterThan() {
		Collection<Board> results=boardRepository.findByTitleContainingAndBnoGreaterThan("99", 100L);
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testBnoOrderBy() {
		Collection<Board> results =  boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board -> System.out.println(board));
		
	}
}
