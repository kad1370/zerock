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
	
	@GetMapping(value="/list")
	public void list(Model model) {
		log.info("================= list ======================");
		model.addAttribute("list", boardService.getList());	
	}
	
	@GetMapping(value="register")
	public void register() {
		log.info("================= get Register =================");
	}
	
	
	@PostMapping(value="register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("================= post Register =================");
		log.info("register: " + board);
		
		boardService.register(board);
		
		// addFlashAttribute : 보관 된 데이터는 단 한번만 사용할 수 있게 보관한다.
		rttr.addFlashAttribute("result", board.getBno());
		// 등록 작업이 끝난 후 다시 목록 화면으로 이동하기 위함
		return "redirect:/board/list";
		
		//  model.addAttribute("regist", boardService.register(board));
	}
	
	// 상세조회 및 수정
	@GetMapping({"get","modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("-------- get------------");
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PostMapping(value="modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("-------- post Modify------------");
		
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		};
		
		return "redirect:/board/list";
		
	}
	
	@PostMapping(value="remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("-------- post remove------------");
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
}
