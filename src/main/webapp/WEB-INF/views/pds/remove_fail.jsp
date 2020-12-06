<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="/WEB-INF/views/error/500.jsp"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<%
System.out.println(1/0);
 %>

<script>
	alert("삭제를 실패했습니당.");
	history.go(-1);
</script>