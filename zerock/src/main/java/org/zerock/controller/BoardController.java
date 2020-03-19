package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value="/board/*")
@AllArgsConstructor
//@Data
public class BoardController {
	// @Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	@GetMapping(value="list/")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", boardService.getList());	
	}
	
	@PostMapping(value="regist/")
	public String regist(BoardVO board, RedirectAttributes rttr) {
		
		log.info("regist: " + board);
		
		boardService.register(board);
		
		// 등록 작업이 끝난 후 다시 목록 화면으로 이동하기 위함
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
		
		//  model.addAttribute("regist", boardService.register(board));
	}
	
	@GetMapping(value="get/")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("-------- get------------");
		model.addAttribute("board", boardService.get(bno));
		
	}
	
	@PostMapping(value="modify/")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		};
		
		return "redirect:/board/list";
		
	}
	
	@PostMapping(value="remove/")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
}
