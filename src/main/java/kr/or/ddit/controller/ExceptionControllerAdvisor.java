package kr.or.ddit.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvisor {
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView sqlExceptionPage(Exception e) {
		e.printStackTrace();
		
		ModelAndView mnv = new ModelAndView();
		
		mnv.addObject("exception", e);
		mnv.setViewName("error/500");
		
		return mnv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView ExceptionPage(Exception e) {
		e.printStackTrace();
		
		ModelAndView mnv = new ModelAndView();
		
		mnv.setViewName("/error/500");
		mnv.addObject("exception", e);
		
		return mnv;
		
	}
	
	
	
	
}
