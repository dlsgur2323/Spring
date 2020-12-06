package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.Map;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;

public interface ReplyService {
	
	//리스트 보기
	Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException;
	
	//등록
	void registReply(ReplyVO reply) throws SQLException;
	
	//수정
	void modifyReply(ReplyVO reply) throws SQLException;
	
	//삭제
	void removeReply(int rno) throws SQLException;
	
}
