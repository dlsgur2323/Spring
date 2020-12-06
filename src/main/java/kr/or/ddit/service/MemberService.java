package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;

public interface MemberService {  //기능별로 되어 있음

	//로그인
	void login(String id, String pwd, HttpSession session) throws SQLException, NotFoundIDException, InvalidPasswordException;
	
	//회원리스트조회
	List<MemberVO> getMemberList(SearchCriteria cri) throws SQLException;
	
	Map<String, Object> getSearchMemberList(SearchCriteria cri) throws SQLException;
	
	//회원정보조회
	MemberVO getMember(String id) throws SQLException;
	
	//회원등록
	void regist(MemberVO member)  throws SQLException;
	
	//회원수정
	void modify(MemberVO member)  throws SQLException;
	
	//회원삭제
	void remove(String id)  throws SQLException;
	
	//회원정지
	void disabled(String id)  throws SQLException;
	
	//회원활성
	void enabled(String id)  throws SQLException;
	

}
