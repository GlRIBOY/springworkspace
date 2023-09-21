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
		return boardMapper.selectBoardList();
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
	}

	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		int result = boardMapper.insertBoard(boardVO);
		if (result == 1) {
			return Integer.valueOf(boardVO.getBno());
		} else {
			return -1;
		}
	}//조건문 이걸 권장. 즉, 변수를 선언해서 담아서 사용. 안그럼 복잡할땐 코드 반복일어날수도있음.

	@Override
	public int updateBoardInfo(BoardVO boardVO) {
		return boardMapper.updateBoard(boardVO) == 1 ? Integer.valueOf(boardVO.getBno()) : -1;
	}//조건 ? true : false; / 조건문이 단순할땐 이게 더 직관적일수있음

	@Override
	public int deleteBoardInfo(int boardNo) {
		int result = boardMapper.deleteBoard(boardNo);
		if (result == 1) {
			return boardNo;
		} else {
			return -1;
		}
	}

}
