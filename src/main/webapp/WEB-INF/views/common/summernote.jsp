<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>

//window.onload=function(){
function SmartEditor_summernote(target){
	//$('#content').summernote({
	target.summernote({	
		//json형태로 필요한 옵션을 준다 ==> 더 많은 옵션을 보려면 summernote로 ㅎㅎ
		placeholer:'여기에 내용을 적으세요.',
		height:250,
		disableResizeEditor: true,
		callbacks:{
			//files:여러개선택가능
			onImageUpload : function(files, editor, welEditable) {
				//alert("image upload");
				for(var i = files.length -1; i > -1; i--) {
					if(files[i].size > 1024*1024*5) {  //이미지만 오기 때문에
						alert("이미지는 5MB 미만입니다.");
						return;
					}
				}
				//file sending
				for(var i = files.length -1; i >= 0; i--) {
					sendFile(files[i], this);
				}
			},
			onMediaDelete : function(target) {
				//alert("image delete");
				var answer = confirm("정말 이미지를 삭제하시겠습니다.");
				if(answer) {
					deleteFile(target[0].src);
				}
			}
		}
	});
}

//이미지 업로드
function sendFile(file, el) {
	var form_data = new FormData();
	form_data.append("file", file);
	$.ajax({
		data : form_data,
		type : "POST",
		url : '<%=request.getContextPath()%>/uploadImg.do',
		contentType : false,
		processData : false,
		success : function(img_url) {
			$(el).summernote('editor.insertImage', img_url);
		}
	});
}
//이미지 삭제
function deleteFile(src) {
	var splitSrc = src.split("=");
	var fileName = splitSrc[splitSrc.length-1]; //인덱스에서 무조건 마지막 것 가져오기 위해서
	
	//alert(fileName);
	var fileData = {
			fileName : fileName
	}
	$.ajax({
		url : "<%=request.getContextPath()%>/deleteImg.do",
		data : JSON.stringify(fileData),
		type : "post",
		success : function(res) {
			console.log(res);
		}
	});
}

</script>