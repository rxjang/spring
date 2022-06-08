package com.example.controller;

import com.example.domain.WebBoard;
import com.example.persistence.WebBoardRepository;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards")
@Slf4j
@RequiredArgsConstructor
public class WebBoardController {

    private final WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(PageVO vo, Model model) {

        Pageable page = vo.makePageable(0, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate(vo.getType(), vo.getKeyword()), page);

        log.info("{}", page);
        log.info("{}", result);

        model.addAttribute("result", new PageMaker(result));
    }

    @GetMapping("/register")
    public void registerScreen(@ModelAttribute("vo") WebBoard vo) {
        log.info("register screen");
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {

        log.info("register");
        log.info("{}", vo);

        webBoardRepository.save(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {

        log.info("BNO: {}", bno);
        webBoardRepository.findById(bno)
                .ifPresent(webBoard -> model.addAttribute("vo", webBoard));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("MODIFY BNO: {}", bno);

        webBoardRepository.findById(bno)
                .ifPresent(webBoard -> model.addAttribute("vo", webBoard));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {

        log.info("Delete BNO: {}", bno);

        webBoardRepository.deleteById(bno);

        rttr.addFlashAttribute("msg", "success");
        // 페이징과 검색했던 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }

    @PostMapping("/modify")
    public String modify(WebBoard board, PageVO vo, RedirectAttributes rttr) {

        log.info("Modify WebBoard: {}", board);

        webBoardRepository.findById(board.getBno())
                .ifPresent(origin -> {
                    origin.setTitle(board.getTitle());
                    origin.setContent(board.getContent());

                    webBoardRepository.save(origin);
                    rttr.addFlashAttribute("msg", "success");
                    rttr.addAttribute("bno", origin.getBno());
                });

        rttr.addFlashAttribute("msg", "success");
        // 페이징과 검색했던 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/view";
    }

}
