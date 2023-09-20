package com.yedam.app.board.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;
@Service
public class BoardServiceImpl implements BoardService {

@Autowired 
BoardMapper boardMapper;
@Override
	public List<BoardVO> getBoardList() {
List<BoardVO> list = boardMapper.selectBoardList(new BoardVO);
		return list;
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		return null;
	}

	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		return 0;
	}

	@Override
	public int updateBoardInfo(BoardVO boardVO) {
		return 0;
	}

	@Override
	public int deleteBoardInfo(int boardNo) {
		return 0;
	}
	
}
