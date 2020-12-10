package kr.or.ddit.command;

import java.util.Arrays;

import kr.or.ddit.dto.MemberVO;

public class MemberRegistCommand {
	String id;
	String pwd;
	String email;
	String picture;
	String authority;
	String name;
	String[] phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "MemberRegistCommand [id=" + id + ", pwd=" + pwd + ", email=" + email + ", picture=" + picture
				+ ", authority=" + authority + ", name=" + name + ", phone=" + Arrays.toString(phone) + "]";
	}
	
	public MemberVO toMemberVO(){
		
		String phone = "";
		for (String data : this.phone) {
			phone += data;
		}		
		
		//MemberVO setting
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setPicture(picture);
		member.setAuthority(authority);
		member.setName(name);
		
		return member;
	}
	
}












