<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	alert("정지된 계정입니다.\n 사용제한으로 불가합니다.");
	var answer = confirm("이전 화면으로 돌아갑니다.");
	if(answer) {window.close();}
	history.go(-1);
</script>