package com.yedam.app.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.service.UserVO;

@CrossOrigin("*")//cors 일시적허용
@Controller
public class UserController {
	
	//vo안에 필드들이 name속성에 대한 key값임
	
	@GetMapping("getAjax") //get방식은 json이 안됨
	@ResponseBody //비동기통신이라면 반드시 사용해야하는 Annotation
	public Map<String, Object> insertGetAjax(UserVO userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
		map.put("data", userVO);
		
		return map;
	}
	
	@PostMapping("postAjax")
	@ResponseBody
	public Map<String, Object> insertPostAjax(UserVO userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
		map.put("data", userVO);
		
		return map;
	}
	
	@PostMapping("jsonAjax")
	@ResponseBody
	public Map<String, Object> insertJsonAjax(@RequestBody List<UserVO> userVO){ //@RequestBody는 json으로 데이터를 보낼꺼면 반드시 사용해야함
		Map<String, Object> map = new HashMap<>();
		map.put("result", true);
		map.put("data", userVO); 
		
		return map;
	}
}
