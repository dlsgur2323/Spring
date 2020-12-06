<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>

//자료 등록에 첨부파일 추가
function Attach_action(){
	$('#addFileBtn').on('click',function(event){
		//alert("add file btn click");
		
		if($('input[name="uploadFile"]').length >=5) {
			alert("파일추가는 5개까지만 가능합니다.");
			return;
		}
		
		var input = $('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
		var div=$('<div>').addClass("inputRow");
		div.append(input).append("<button style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");
		div.appendTo('.fileInput');
	});
	
	//X 눌렀을 때 첨부파일 없어지는 부분
	$('div.fileInput').on('click','div.inputRow > button', function(event){
		$(this).parent('div.inputRow').remove();
	});
	
	//파일이 변경될 때 용량에 대한 검사
	$('.fileInput').on('change','input[type="file"]',function(event){
		if(this.files[0].size>1024*1024*40){
			alert("파일 용량이 40MB를 초과하였습니다.");
			this.value="";
			$(this).focus();
			return false;
		}
	})
}

</script>