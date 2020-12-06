<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("${id}님의 사용을 중지했습니다.")
	//상대경로	
	location.href="detail.do?id=${id}";
</script>
