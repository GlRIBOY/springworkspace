package com.yedam.app.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//전체조회 : URI = boardList / RETRUN = board/boardList 
	@GetMapping("boardList")
	public String getBoardList(Model model) {
		List<BoardVO> list = boardService.getBoardList();
		model.addAttribute("boardList", list);
		return "board/boardList";
	}
	//단건조회 : URI = boardInfo / Parameter = BoardVO / RETURN = board/boardInfo
	@GetMapping("boardInfo")
	public String getBoardInfo(BoardVO boardVO, Model model) {
		BoardVO vo = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", vo);
		return "board/boardInfo";
	}
	//등록 - 페이지 : URI = boardInsert / RETURN = board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		//등록 페이지를 호출할 때 미리 primary key를 보여야 하는 경우에 Model이 필요함.=>selectkey사용안함.
		return "board/boardInsert";
	}
	
	//등록 - 처리 : URI = boardInsert / Parameter = BoardVO / RETURN = 전체조회 다시 호출
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		boardService.insertBoardInfo(boardVO);
		return "redirect:boardList";
	}
	//수정 - 페이지 : URI = boardUpdate / Parameter = BoardVO / RETURN = board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO vo = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", vo);
		return "board/boardUpdate";
	}
	//수정 - 처리 : URI = boardUpdate / Parameter = BoardVO / RETURN = 실행결과
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO){
		Map<String, Object> map = new HashMap<>();
		int result = boardService.updateBoardInfo(boardVO);
		if(result > -1) {
			map.put("result",  true);
			map.put("bno", result);
		}else {
			map.put("result", false);
		}
		return map;
	}
	//삭제 - 처리 : URI = boardDelete / Parameter = bno / RETURN = 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(@RequestBody Integer bno) {
		boardService.deleteBoardInfo(bno);
		return "redirect:boardList";
	}
	
}
