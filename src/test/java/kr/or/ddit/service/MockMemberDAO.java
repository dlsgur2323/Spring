package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dto.MemberVO;

public class MockMemberDAO implements MemberDAO{
	
	@Override
	public List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException{
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		MemberVO member1=new MemberVO("mimi","mimi","mimi","000-0000-1234","mimi@mimi.com");
		MemberVO member2=new MemberVO("mimi1","mimi1","mimi1","000-111-1234","mimi1@mimi.com");
		
		memberList.add(member1);
		memberList.add(member2);
	
		return memberList;
	}

	@Override
	public int selectMemberListCount(SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		MemberVO member=null; 
		if(!id.equals("mimi"))return member;
		
		member=new MemberVO();
		member.setId(id);
		member.setPwd("1234");
		member.setEnabled(1);
		member.setPhone("010-1234-5678");
		member.setEmail(id+"@"+id+".com");
		member.setPicture("noImage.jpg");
		
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabledMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabledMember(String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
