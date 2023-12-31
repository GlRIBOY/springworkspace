package com.yedam.app.aop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.TempMapper;
import com.yedam.app.aop.service.TempService;
@Service
public class TempServiceImpl implements TempService {
	@Autowired
	TempMapper tempMapper;
	
	@Transactional//넣으니 둘다 삽입안됨 빼면 101은 됨
	@Override
	public void insert(String value) {
		tempMapper.insert("101");
		tempMapper.insert("a101");
	}
}
