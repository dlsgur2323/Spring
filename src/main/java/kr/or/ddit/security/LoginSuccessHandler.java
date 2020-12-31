package kr.or.ddit.security;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.ddit.dto.MemberVO;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {
		
		User user = (User) authentication.getDetails();
		
		MemberVO loginUser = user.getMember();
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		
		logFile(loginUser, request);
		
		//cookie
		String rememberMe = request.getParameter("rememberMe");
		String id = request.getParameter("id");
		
		if(rememberMe != null && rememberMe.equals("check")) {
			Cookie cookie = new Cookie("loginUser", id);
			cookie.setMaxAge(3*24*60*60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	private void logFile(MemberVO loginUser, HttpServletRequest request) throws IOException{
		String tag = "[login:user]";
		String log = tag
				+ loginUser.getId()
				+ loginUser.getPhone()
				+ loginUser.getEmail()
				+ request.getRemoteAddr()
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		//로그 파일 생성.
		String savePath = "/Users/kim-inhyeok/mainFolder/log";
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String logFilePath = savePath + File.separator+"login_user_log.txt";
		BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath, true));
		
		// 로그를 기록
		out.write(log);
		out.newLine();
		
		out.close();
	}
}
