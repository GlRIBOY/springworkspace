package com.yedam.app.emp.service;

import java.util.List;

public interface EmpService {
	//전조
	public List<EmpVO> getEmpList();
	//단조
	public EmpVO getEmpInfo(EmpVO empVO);
	//등록
	public int insertEmpInfo(EmpVO empVO);
}
