package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;
import kr.or.ddit.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class FreeBoardReplyController {
	
	@Autowired
	private ReplyService service;
	
	
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("bno") int bno, @PathVariable("page") int page) throws Exception{
		
		ResponseEntity<Map<String, Object>> result = null;
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page);
		
		try {
			Map<String, Object> dataMap = service.getReplyList(bno, cri);
			result =  new ResponseEntity<Map<String, Object>>(dataMap,HttpStatus.OK);
		} catch (SQLException e) {
			result = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> regist(@RequestBody ReplyVO reply) throws Exception{
		
		ResponseEntity<String> result = null;
		
		try {
			service.registReply(reply);
			
			SearchCriteria cri = new SearchCriteria();
			
			Map<String, Object> dataMap = service.getReplyList(reply.getBno(), cri);
			PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();
			
			result = new ResponseEntity<String>(realEndPage+"",HttpStatus.OK);
			
		} catch (SQLException e) {
			e.printStackTrace();
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyVO reply) throws Exception{
		ResponseEntity<String> result = null;
		reply.setRno(rno);
		
		try {
			service.modifyReply(reply);
			result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/{bno}/{rno}/{page}", method= RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno, @PathVariable("bno") int bno, 
										 @PathVariable("page") int page) throws Exception{
	
		ResponseEntity<String> result = null;
		
		try {
			service.removeReply(rno);
			SearchCriteria cri = new SearchCriteria();
			Map<String, Object> dataMap = service.getReplyList(bno, cri);
			PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
			
			int realEndPage = pageMaker.getRealEndPage();
			if(page>realEndPage) {page=realEndPage;}
			
			result = new ResponseEntity<String>(""+page, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	
	}
}



















