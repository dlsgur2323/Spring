<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>
window.onload=function(){
	SmartEditor_summernote($('#content'));
	
	//화면이 다 로딩된 이후에 자바스크립트나 jsp들은 버블링이벤트를 걸어야 하지만
	//애시당초 시작할 때 부터 있었던 것은 버블링 이벤트를 걸 필요가 없음
	$('div.fileInput a[name="attachedFile"] > button').on('click',function(event){
		//alert("x btn click");
		//event.stopPropagation();
		
		var parent = $(this).parent('a[name="attachedFile"]');
		if(!confirm(parent.attr("attach-fileName")+"파일을 삭제하시겠습니까?")) return false;
		
		var ano= parent.attr("attach-no");
		
		//jQuery
		$(this).parents('li.attach-item').remove();
		
		var input = $('<input>').attr({
			"type" : "hidden",
			"name" : "deleteFile",
			"value" : ano
			});
		
		//prepend(안에서 위로 넣기 때문에 밀림)
		$('form[role="form"]').prepend(input);
		
		return false;  //이벤트 실행 안되게 함
	});
	
	//첨부된 파일의 개수를 세서 한개가 첨부되어 있으면 4개만 추가되게 (최대 5개)
	$('#addFileBtn').on('click',function(event){
		var attachedFile = $('a[name="attachedFile"]').length;
		var inputFile = $('input[name="uploadFile"]').length;
		var attachCount = attachedFile + inputFile;
		
		if(attachCount >= 5) {
			alert("파일 추가는 5개까지만 가능합니다.");
			return;
		}
		
		var input = $('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
		var div = $('<div>').addClass("inputRow");
		div.append(input).append("<button style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");
		div.appendTo('.fileInput');
	});
	
	//submit 부분
	//40MB 파일 용량 제한
	$('.fileInput').on('change','input[type="file"]',function(event){
		if(this.files[0].size>1024*1024*40){
			alert("파일 용량이 40MB를 초과하였습니다.");
			this.value = "";
			$(this).focus();
			return false;
		}
	});
	
	//수정버튼 누르면 해당 modifyForm 가져와서
	//제목 필수
	$('#modifyBtn').on('click',function(e){
		var form = document.modifyForm;
		
		if($("input[name='title']").val()==""){
			alert(input.name+"은 필수입니다.");
			$("input[name='title']").focus();
			return;
		}
		
		//파일 선택 필수
		var files = $('input[name="uploadFile"]');
		for(var file of files){
			console.log(file.name+" : "+file.value);
			if(file.value==""){
				alert("파일을 선택하세요.");
				file.focus();
				return false;
			}
		}
		
		//글자수 체크는 안함 (스마트에디터에서는 의미가 없어서)
		
		form.submit();
	});
	
	//취소버튼은 뒤로가기
	$('#cancelBtn').on('click',function(e){
		history.go(-1);
	});

	

}

</script>