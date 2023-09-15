package com.yedam.app.java.spring;

import org.springframework.stereotype.Component;

@Component
public class SamsungTV implements TV{

	@Override
	public void on() {
		System.out.println("삼성TV를 켰습니다.");
	}
}
