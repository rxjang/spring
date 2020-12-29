package org.zerock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.FreeBoard;
import org.zerock.domain.FreeBoardReply;
import org.zerock.persistence.FreeBoardReplyRepository;
import org.zerock.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit
class FreeBoardTests {

	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	@Test
	public void insertDummy() {
		IntStream.range(1, 200).forEach(i->{
			FreeBoard board = new FreeBoard();
			board.setTitle("Free Board ... " + i);
			board.setContent("Free Content.... " + i);
			board.setWriter("user"+ i%10 );
			
			boardRepo.save(board);
		});
	}
	
	@Test
	@Transactional
	public void insertReply2Wqy() {
		
		Optional<FreeBoard> result = boardRepo.findById(199L);
		
		result.ifPresent(board ->{
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply =new FreeBoardReply();
			reply.setReply("REPLY.........");
			reply.setReplyer("replyer00");
			reply.setBoard(board);
			
			replies.add(reply);
			
			boardRepo.save(board);
		});
	}
	
	@Test
	public void insertReply1Way() {
		
		FreeBoard board = new FreeBoard();
		board.setBno(199L);
		
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY.....");
		reply.setReplyer("replyer00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	
	@Test
	public void testList1() {
		Pageable page=PageRequest.of(0, 10,Sort.Direction.DESC, "bno");
		
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
			log.info(board.getBno() +": " +board.getTitle());
		});
	}
	
	@Transactional
	@Test
	public void testList2() {
		Pageable page = PageRequest.of(0, 10,Sort.Direction.DESC,"bno");
		
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board->{
			log.info(board.getBno()+": "+board.getTitle()+":"+board.getReplies().size());
		});
	}
	
	@Test
	public void testList3() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.getPage(page).forEach(arr->log.info(Arrays.toString(arr)));
	}

}