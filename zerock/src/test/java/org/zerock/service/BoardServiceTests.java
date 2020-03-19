package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;
	
	@Test
	public void testExist() {
		log.info(boardService);
		assertNotNull(boardService);
	}
	
	// 등록 기능 테스트
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setContent("등록 컨텐츠");
		board.setTitle("등록 제목");
		board.setWritter("등록자");
		
		boardService.register(board);
		
		log.info("--------- 생성된 게시글의 번호: " + board.getBno());
	}
	
	// 리스트 조회 테스트
	@Test
	public void testGetList() {
		boardService.getList().forEach(board -> log.info(board));
	}
	
	// 선택 조회 테스트
	@Test
	public void testGetRow() {
		log.info("--------- 조회한 게시글 데이터: " + boardService.get(3L));
	}
	
	// 수정 단위테스트
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setContent("컨텐츠 수정");
		board.setTitle("제목 수정");
		board.setWritter("수정자11");
		board.setBno(3L);
		
		boolean result = boardService.modify(board);
		log.info("--------- 수정 결과: " + result);
	}
	
	// 삭제 단위테스트
	@Test
	public void testDelete() {
		boolean result = boardService.remove(4L);
		log.info("--------- 삭제 결과: " + result);
	}
}
                                                                     