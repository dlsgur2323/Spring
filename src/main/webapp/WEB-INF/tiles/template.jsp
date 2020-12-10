<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		
		<!-- 내보내고자 하는 title에 title태그가 없으면 default값이 나감 -->
		<title>제목</title>
		
		<!-- Font Awesome Icons -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/dist/css/adminlte.min.css">
		<!-- Google Font: Source Sans Pro -->
		<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
		  
	</head>
	<body class="hold-transition sidebar-mini">
		<div id="header"><tiles:insertAttribute name="header"/></div>
		<div id="left"><tiles:insertAttribute name="left"/></div>
		<div id="main"><tiles:insertAttribute name="body"/></div>
		<div id="footer"><tiles:insertAttribute name="footer"/></div>
	</body>
</html>



















