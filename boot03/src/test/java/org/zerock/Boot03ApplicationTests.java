package org.zerock;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	@Test
	public void testBnoOrderByPaging() {
		Pageable paging=PageRequest.of(0, 10);
		Collection<Board> results=boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
		results.forEach(board -> System.out.println(board));
	}

//	@Test
//	public void testbnoPagingSort() {
//		Pageable paging=PageRequest.of(0, 10, Sort.Direction.ASC,"bno");
//		Collection<Board> results=boardRepository.findByBnoGreaterThan(0L, paging);
//		results.forEach(board -> System.out.println(board));
//	}
	
	@Test
	public void testBnoPagingSort() {
		Pageable paging=PageRequest.of(0, 10, Sort.Direction.ASC,"bno");
		Page<Board> result=boardRepository.findByBnoGreaterThan(0L, paging);
		System.out.println("PAGE SIZE: " +result.getSize());
		System.out.println("TOTAL PAGES: " +result.getTotalPages());
		System.out.println("TOTAL COUNT: " +result.getTotalElements());
		System.out.println("NEXT: " +result.nextPageable());
		
		List<Board> list=result.getContent();
		list.forEach(board->System.out.println(board));
		
	}
	
}
