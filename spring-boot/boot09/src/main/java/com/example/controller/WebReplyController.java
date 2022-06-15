package com.example.controller;

import com.example.domain.WebBoard;
import com.example.domain.WebReply;
import com.example.persistence.WebReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Slf4j
public class WebReplyController {

    private final WebReplyRepository webReplyRepository;

    @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/{bno}")
    @Transactional
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {

      log.info("addReply.........");
      log.info("bno: {}", bno);
      log.info("Reply: {}", reply);

      WebBoard board = new WebBoard();
      board.setBno(bno);

      reply.setBoard(board);

      webReplyRepository.save(reply);

      return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
    @DeleteMapping("/{bno}/{rno}")
    @Transactional
    public ResponseEntity<List<WebReply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno") Long rno) {

        log.info("delete reply: {}", rno);
        webReplyRepository.deleteById(rno);

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PutMapping("/{bno}")
    @Transactional
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {

        log.info("modify reply: {}", reply);
        webReplyRepository.findById(reply.getRno()).ifPresent(origin -> {
            origin.setReplyText(reply.getReplyText());

            webReplyRepository.save(origin);
        });

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    @GetMapping("/{bno}")
    public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno")Long bno) {

        log.info("get All Replies..........");

        WebBoard board = new WebBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    private List<WebReply> getListByBoard(WebBoard board) throws RuntimeException {
        log.info("getListByBoard...." + board);

        return webReplyRepository.getRepliesOfBoard(board);
    }
}
