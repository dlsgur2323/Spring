<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<form role="form">
	<input type="hidden" name='pno' value="${pds.pno }">
</form>


<script>
window.onload=function(){
	var formObj = $("form[role='form']");

	$("#modifyBtn").on("click", function(){		
		formObj.attr("action", "modifyForm.do");
		formObj.submit();
	});

	$("#removeBtn").on("click", function(){
		formObj.attr("action", "remove.do");
		formObj.attr("method", "post");
		formObj.submit();
	});
}


</script>




