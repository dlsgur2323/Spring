package kr.or.ddit.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.utils.GetUploadPath;

public class AttachFileLogInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Map<String,Object> modelMap = modelAndView.getModel();
		List<AttachVO> attachList = (List<AttachVO>) modelMap.get("attachList");
		
		MemberVO loginUser = (MemberVO) request.getSession().getAttribute("loginUser");
		
		System.out.println("======= logHandler start");
		String log = "[File]" // 등록자아이디, 등록자이름, 날짜, {파일명1 파일명2 ... }, 경로
				+ loginUser.getId() +", "
				+ loginUser.getName() +", "
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ", {";
		if(attachList != null) {
			System.out.println("========= logHandler attachList ");
			for(AttachVO attach : attachList) {
				log += attach.getFileName() + " ";
			}
		}
		log += "}, "
				+ attachList.get(0).getUploadPath();
		
		//로그 파일 생성.
				String savePath = "/Users/kim-inhyeok/mainFolder/log";
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdirs();
				}
				String logFilePath = savePath + File.separator+"attach_file_log.txt";
				BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath, true));
				
				// 로그를 기록
				out.write(log);
				out.newLine();
				
				out.close();
	}
}
























