package com.yedam.app.emp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;
@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpMapper empMapper;
	
	@Override
	public List<EmpVO> getEmpAll() {
		List<EmpVO> findList = empMapper.selectEmpAllList(new EmpVO());
		return findList;
	}

	@Override
	public EmpVO getEmp(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int insertEmp(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		
		if(result == 1) {
			return empVO.getEmployeeId();
		}else {
			return -1;
		}
	}

	@Override
	public String updateEmpSal(int empId, int raise) {
		String message = null;
		
		int result = empMapper.updateEmpSal(empId, raise);
		
		if(result == 1) {
			message = "정상적으로 급여가 갱신되었다.";
		}else {
			message = "작업실패.";
		}
		return message;
	}

	@Override
	public Map<String, String> updateEmp(EmpVO empVO) {
		Map<String, String> map = new HashMap<>();
		
		map.put("사원번호", String.valueOf(empVO.getEmployeeId()));
		
		int result = empMapper.updateEmpInfo(empVO);
		
		if(result==1) {
			map.put("결과", "Success");
		}else {
			map.put("결과", "fail");
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteEmp(List<Integer> list) {
		boolean isSuccessed = false; // 성공유무
		List<Integer> successList = new ArrayList<>(); // 성공한 대상
		int count = 0; // 성공횟수
		for(int empId : list) {
			int result = empMapper.deleteEmpInfo(empId);
			
			if(result == 1) {// 정상적으로 삭제한 경우
				count +=1;
				successList.add(empId);
			}
		}
		
		if(count>0) isSuccessed = true; //한건이라도 성공한 경우
		
		Map<String, Object> map = new HashMap<>();
		map.put("total", list.size());
		map.put("result", isSuccessed);
		map.put("list", successList);
		return map;
	}

}
