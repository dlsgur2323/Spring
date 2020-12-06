package kr.or.ddit.test;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/context/root-context.xml")
public class TestSqlSession {

	@Autowired
	private SqlSession session;
	
	@Test
	public void testSession() {
		Assert.assertNotEquals(null, session);
	}
	
	@Test
	public void testSelectMember() throws SQLException {
		
		String id = "mimi";
		
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",id);
		
		Assert.assertEquals(id, member.getId());
	}
	
}
