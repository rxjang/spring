package org.zerock;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit
class WebBoardRepositoryTest {

	@Autowired
	WebBoardRepository repo;
	
	@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach( i ->{
			
			WebBoard board = new WebBoard();
			
			board.setTitle("Sample Board Title " + i);
			board.setContent("Content Sample ..." + i + " of Board ");
			board.setWriter("user0" + (1%10));
			
			repo.save(board);
		});
	}
}
