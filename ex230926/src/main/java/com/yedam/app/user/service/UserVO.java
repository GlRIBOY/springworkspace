package com.yedam.app.user.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserVO {
	private String id;
	private String password;
	private String gender;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")//타입이데이트 일 때는 무조건 사용해줘야함.
	private Date joinDate;
}
