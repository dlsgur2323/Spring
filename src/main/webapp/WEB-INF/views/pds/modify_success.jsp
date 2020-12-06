<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	alert("자료수정이 성공했습니다.");
	window.location.href="detail.do?pno=${pds.pno}&from=modify";
</script>