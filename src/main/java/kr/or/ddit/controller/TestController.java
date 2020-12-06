package kr.or.ddit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class TestController {
	
	//액션이라고 생각하면 됨
	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response) {
		String url = "test";
		request.setAttribute("message", "Hello StringMVC !!");
		return url;
	}
	
	
}
