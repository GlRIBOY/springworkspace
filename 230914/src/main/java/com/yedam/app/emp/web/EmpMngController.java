package com.yedam.app.emp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpMngController {
	@Autowired
	EmpService empService;
	
	//조회(데이터, 일반페이지) -> GET
	//등록, 수정, 삭제 -> POST(예외, 단건삭제 - GET)
	//조회는 get, 데이터조작 post
	
	//전체조회
	@GetMapping("empList")
	public String getEmpAllList(Model model) {
		model.addAttribute("empList", empService.getEmpAll());
		return "emp/empList";
	}
	//단건조회
	@GetMapping("empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {
		model.addAttribute("empInfo", empService.getEmp(empVO));
		return"emp/empInfo";
	}
	//등록은 페이지요청 get방식과 실제로 처리하는 기능 post방식 둘다 요청함
	//등록 - form
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/empInsert";
	}
	//등록 - process
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO, RedirectAttributes attr) {
		int empId = empService.insertEmp(empVO);
		
		String result = null;
		if(empId == -1) {
			result = "정상적으로 등록되지 않았슴다";
		}else {
			result = "정상즉으로 등록됐슴둥. \n등록된 사원의 사원번호는 "+empId+"입니다";
		}
		attr.addAttribute("type", "insert");
		attr.addFlashAttribute("result", result);
		
		return"redirect:empList";
	}
	//수정 : 1) 단건조회-> 2) 수정=========================================================
	//수정 - form
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.getEmp(empVO);
		model.addAttribute("empInfo", findVO);
		return"emp/empUpdate";
	}
	//수정 - process : ajax => 필수어노테이션은 @ResponseBody 리퀘는 굳이 필수는 아님
	//아작스는 제이슨이 필수가 아닌데 제이슨은 아작스가 필수임
	@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, String> empUpdateProcess(@RequestBody EmpVO empVO){
		return empService.updateEmp(empVO);
	}
	//=================================================================================
	//삭제 - 단건삭제
	
	//삭제 - 선택삭제
	
}
