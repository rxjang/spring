package org.zerock;

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

}
