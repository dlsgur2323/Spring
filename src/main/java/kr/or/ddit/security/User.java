package kr.or.ddit.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.dto.MemberVO;

public class User implements UserDetails {
	
	private MemberVO member;
	public User() {}
	public User(MemberVO member) {
		this.member = member;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(member.getAuthority()));
		return roles;
	}

	@Override
	public String getPassword() {
		return member.getPwd();
	}

	@Override
	public String getUsername() {
		return member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return member.getEnabled()==1;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return member.getEnabled()==1;
	}
	
	public MemberVO getMember() {
		return this.member;
	}

}
