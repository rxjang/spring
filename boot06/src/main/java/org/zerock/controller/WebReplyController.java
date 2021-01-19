package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.WebReply;
import org.zerock.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

	@Autowired
	private WebReplyRepository replyRepo;
	
	@PostMapping("/{bno}")
	public ResponseEntity<Void> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply){
		
		log.info("addReply............");
		log.info("BNO: " + bno);
		log.info("REPLY: " + reply);
		
		//코드를 이용해 직접 Http Response의 상태코드와 데이터를 제어 처리 
		return new ResponseEntity<>(HttpStatus.CREATED);
	};
}
