package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:kr/or/ddit/context/root-context.xml")
public class TestMemberServiceImpl {
	
	@Autowired
	private MemberServiceImpl service;
	
	@Before
	public void init() {
		service.setMemberDAO(new MockMemberDAO());
	}
	
	@Test
	public void testGetList() throws SQLException {
		
		SearchCriteria cri = new SearchCriteria();
		
		List<MemberVO> memberList = (List<MemberVO>)service.getSearchMemberList(cri).get("memberList");
		
		Assert.assertEquals(2, memberList.size());
	}
	
	@Test
	public void testLogin() throws SQLException {
		String testID = "kkk";
		String testPWD = "kkk";
		
		HttpSession session = new MockHttpSession();
		
		try {
			service.login(testID, testPWD, session);
			
			Assert.assertEquals(1, 0);
		} catch (NotFoundIDException e) {
			Assert.assertEquals(1, 1);
		} catch (InvalidPasswordException e) {
			Assert.assertEquals(1, 0);
		}
		
		testID = "mimi";
		try {
			service.login(testID, testPWD, session);
			
			Assert.assertEquals(1, 0);
		} catch (InvalidPasswordException e) {
			Assert.assertEquals(1, 1);
		} catch (NotFoundIDException e) {
			Assert.assertEquals(1, 2);
		}
		
		testPWD = "1234";
		try {
			service.login(testID, testPWD, session);
			
			//session에 loginUser가 들어가 있는지 확인
			Assert.assertNotNull(session.getAttribute("loginUser"));
		} catch (InvalidPasswordException e) {
			Assert.assertEquals(1, 3);
		} catch (NotFoundIDException e) {
			Assert.assertEquals(1, 4);
		}
	}

}
