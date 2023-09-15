package com.yedam.app.java.spring;

import org.springframework.stereotype.Component;

@Component("tv")
public class LGTV implements TV {

	@Override
	public void on() {
		System.out.println("LGTV를 켰습니다.");
	}

}
