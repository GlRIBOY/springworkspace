package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	//전체조회
	public List<EmpVO> selectEmpAllList(EmpVO empVO);
	//단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	//등록
	public int insertEmpInfo(EmpVO empVO);
	//수정 - 급여를 정해진 비율로 인상
	/* 입력 값이 이름없이 값만 넘어가기때문에 @Param으로 값에 이름 지정해줌. EmpVO같은 객체는 안에 필드명이있어서 갠춘 */
	public int updateEmpSal(@Param("empId")int employeeId, @Param("raise") int raise);
	//수정 - 사원 정보 수정
	public int updateEmpInfo(EmpVO empVO);
	//삭제
	public int deleteEmpInfo(int employeeId);
}
