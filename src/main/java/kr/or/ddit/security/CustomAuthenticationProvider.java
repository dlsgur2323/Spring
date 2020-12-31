package kr.or.ddit.security;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dto.MemberVO;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login_id = (String) auth.getPrincipal();
		String login_pwd = (String) auth.getCredentials();
		
		MemberVO member = null;
		
		try {
			member = memberDAO.selectMemberById(login_id);
		} catch (SQLException e) {
			throw new AuthenticationServiceException("Internal server error!");
		}
		if(member != null && login_pwd.equals(member.getPwd())) {
			User authUser = new User(member);
			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authUser.getAuthorities());
			result.setDetails(authUser);
			
			return result;
		} else {
			throw new BadCredentialsException("Bad ID or Password");
		}
		
	}
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
}





