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
@Service 			// 비즈니스 영역을 담당하는 객체임을 표시하는 어노테이션
@AllArgsConstructor // 모든 파라미터를 이용하는 생성자를 만든다.
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	// 등록 
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		mapper.insertSelectKey(board);
	}
	
	// 검색(선택) 조회
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	// 수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("====== 데이터: " + board);
		return mapper.update(board) > 0;
	}
	
	// 제거
	@Override
	public boolean remove(Long bno) {
		log.info("====== 데이터: " + bno);
		return mapper.delete(bno) > 0;
	}

	// 리스트(전체) 조회
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

}
