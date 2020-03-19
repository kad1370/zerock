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
	
	// ��� ��� �׽�Ʈ
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setContent("��� ������");
		board.setTitle("��� ����");
		board.setWritter("�����");
		
		boardService.register(board);
		
		log.info("--------- ������ �Խñ��� ��ȣ: " + board.getBno());
	}
	
	// ����Ʈ ��ȸ �׽�Ʈ
	@Test
	public void testGetList() {
		boardService.getList().forEach(board -> log.info(board));
	}
	
	// ���� ��ȸ �׽�Ʈ
	@Test
	public void testGetRow() {
		log.info("--------- ��ȸ�� �Խñ� ������: " + boardService.get(3L));
	}
	
	// ���� �����׽�Ʈ
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setContent("������ ����");
		board.setTitle("���� ����");
		board.setWritter("������11");
		board.setBno(3L);
		
		boolean result = boardService.modify(board);
		log.info("--------- ���� ���: " + result);
	}
	
	// ���� �����׽�Ʈ
	@Test
	public void testDelete() {
		boolean result = boardService.remove(4L);
		log.info("--------- ���� ���: " + result);
	}
}
                                                                     