<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<%@ include file="/WEB-INF/views/include/open_header.jsp" %>

<!-- 내보내고자 하는 jsp의 body가 나가는게 아니고 여기 있는 body가 나감 -->
<!-- 그걸 막는 코드는 아래. 본문의 body는 구분자 역할만 함 -->
<decorator:body />


<%@ include file="/WEB-INF/views/include/open_footer.jsp" %>