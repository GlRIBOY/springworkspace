package com.yedam.app.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> list = boardMapper.selectBoardList();
		return list;
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		if (boardMapper.insertBoard(boardVO) == 1) {
			return Integer.parseInt(boardVO.getBno());
		} else {
			return -1;
		}
	}

	@Override
	public int updateBoardInfo(BoardVO boardVO) {
		if (boardMapper.updateBoard(boardVO) == 1) {
			return Integer.parseInt(boardVO.getBno());
		} else {
			return -1;
		}
	}

	@Override
	public int deleteBoardInfo(int boardNo) {
		return boardMapper.deleteBoard(boardNo);
	}

}
