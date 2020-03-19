package org.zerock.mapper;

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
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWritter("new");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWritter("new");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}	
	
	@Test
	public void testRead() {
		
		BoardVO board = mapper.read(1L);
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		log.info("=========== DELETE COUNT: " + mapper.delete(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 수정");
		board.setContent("내용 수정");
		board.setWritter("수정자");
		board.setBno(2L);
		
		int count = mapper.update(board);
		
		log.info("=========== UPDATE COUNT: " + count);
	}
}
