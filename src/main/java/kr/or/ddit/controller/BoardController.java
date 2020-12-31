package kr.or.ddit.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.BoardService;
import kr.or.ddit.service.NoticeService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/main")
	public String main() {
		String url = "board/main.open";
		return url;
	}
	
	@RequestMapping("/list") 
	public ModelAndView list(SearchCriteria cri ,ModelAndView mnv) throws Exception{
		
		String url = "/board/list.open";
		
		Map<String,Object> dataMap = boardService.getBoardList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		String url = "board/regist.open";
		return url;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public void regist(BoardVO board, HttpServletRequest request, HttpServletResponse response)throws Exception {
		board.setTitle((String)request.getAttribute("XSStitle"));

		boardService.write(board);
		
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");
		out.close();
			
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(int bno,@RequestParam(defaultValue="") String from, Model model) throws Exception {
		String url = "/board/detail.open";
		
		BoardVO board = null;
		//motify 취소 했을때 조회수 2 증가하는 문제 해결
		if(from.equals("modify")) {
			board = boardService.getBoardModify(bno);
		}else {
			board = boardService.getBoard(bno);
		}
		
		model.addAttribute("board", board);
			
		return url;
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(int bno, Model model) throws Exception {
		String url = "/board/modify.open";
	
		BoardVO board = boardService.getBoardModify(bno);
		
		model.addAttribute("board", board);
			
		return url;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public void modify(BoardVO board, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 파라메터 저장
		board.setTitle((String)request.getAttribute("XSStitle"));
		boardService.modify(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(""
				+ "<script>"
				+ "window.opener.location.reload();"
				+ "location.href='detail?bno="+board.getBno()+"&from=modify';"
				+ "</script>"
				+ "");
		out.close();
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public void remove(int bno, HttpServletResponse response) throws Exception{
		
		boardService.remove(bno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(""
				+ "<script>"
				+ "window.opener.location.reload();window.close()"
				+ "</script>"
				+ "");
		out.close();
		
	}
	
}



















