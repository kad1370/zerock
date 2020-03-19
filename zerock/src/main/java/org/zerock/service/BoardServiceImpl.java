package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service 			// ����Ͻ� ������ ����ϴ� ��ü���� ǥ���ϴ� ������̼�
@AllArgsConstructor // ��� �Ķ���͸� �̿��ϴ� �����ڸ� �����.
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	// ��� 
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		mapper.insertSelectKey(board);
	}
	
	// �˻�(����) ��ȸ
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	// ����
	@Override
	public boolean modify(BoardVO board) {
		log.info("====== ������: " + board);
		return mapper.update(board) > 0;
	}
	
	// ����
	@Override
	public boolean remove(Long bno) {
		log.info("====== ������: " + bno);
		return mapper.delete(bno) > 0;
	}

	// ����Ʈ(��ü) ��ȸ
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

}
