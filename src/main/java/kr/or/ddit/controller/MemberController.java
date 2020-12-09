package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws SQLException{
		
		String url = "member/list";
		
		Map<String, Object> dataMap = memberService.getSearchMemberList(cri);
		
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		String url = "member/regist";
		return url;
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		String result="";
		HttpStatus status = null;
		
		if((result=savePicture(oldPicture, multi))==null) {
			result = "업로드 실패했습니다!";
			status = HttpStatus.BAD_REQUEST;
		} else {
			status = HttpStatus.OK;
		}
		
		entity = new ResponseEntity<String>(result,status);
		
		return entity;
	}
	
	@Resource(name="picturePath")
	private String picturePath;
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception{
		String fileName=null;
		
		if(!(multi==null || multi.isEmpty() || multi.getSize() > 1024* 1024* 5)) {
			String uploadPath = picturePath;
			fileName = UUID.randomUUID().toString().replace("-", "")+".jpg";
			File storeFile = new File(uploadPath, fileName);
			
			multi.transferTo(storeFile);
			
			if(!oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if(oldFile.exists()) {
					oldFile.delete();
				}
			}
			
		}
		
		return fileName;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url ="";
		
		//response로 보내기
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			//Exception처리
			//if(true) throw new SQLException("테스트 ExceptionLoggerHelper");
			
			memberService.regist(member);

			out.println("<script>");
			out.println("alert('회원등록이 정상적으로 되었습니다.');");
			out.println("window.opener.location.href='"+request.getContextPath()+"/member/list.do';");  //절대주소
			out.println("window.close();");
			out.println("</script>");
			
		} catch (SQLException e) {
			out.println("<script>");
			out.println("alert('회원등록이 실패했습니다.');");
			//out.println("window.close();");
			out.println("history.go(-1);");
			out.println("</script>");
			
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
			
		}finally {
			if(out != null) 
			   out.close();
		}
		return url;
	}
	
}




















