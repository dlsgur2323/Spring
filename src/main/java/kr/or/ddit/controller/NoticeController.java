package kr.or.ddit.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list") 
	public ModelAndView list(SearchCriteria cri ,ModelAndView mnv) throws Exception{
		
		String url = "/notice/list";
		
		Map<String,Object> dataMap = noticeService.getNoticeList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		String url = "notice/regist";
		return url;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public void regist(NoticeVO notice, HttpServletResponse response)throws Exception {
		noticeService.write(notice);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");
		out.close();
			
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(int nno,@RequestParam(defaultValue="") String from, Model model) throws Exception {
		String url = "/notice/detail";
		
		NoticeVO notice = null;
		//motify 취소 했을때 조회수 2 증가하는 문제 해결
		if(from.equals("modify")) {
			notice = noticeService.getNoticeModify(nno);
		}else {
			notice = noticeService.getNotice(nno);
		}
		
		model.addAttribute("notice", notice);
			
		return url;
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(int nno, Model model) throws Exception {
		String url = "/notice/modify";
	
		NoticeVO notice = noticeService.getNoticeModify(nno);
		
		model.addAttribute("notice", notice);
			
		return url;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public void modify(NoticeVO notice, HttpServletResponse response)
			throws Exception {
		// 파라메터 저장
		noticeService.modify(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(""
				+ "<script>"
				+ "window.opener.location.reload();"
				+ "location.href='detail?nno="+notice.getNno()+"&from=modify';"
				+ "</script>"
				+ "");
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void remove(int nno, HttpServletResponse response) throws Exception{
		
		noticeService.remove(nno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(""
				+ "<script>"
				+ "widnow.opener.location.reload();window.close()"
				+ "</script>"
				+ "");
		
	}
	
}



















