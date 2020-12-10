<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data">
	<input id="inputFile" name="pictureFile" type="file" class="form-control" style="display:none;"
		onchange="imageChange_go();">
	<input id="oldFile" type="hidden" name="oldPicture" value="" />
	<input type="hidden" name="checkUpload" value="0" />
</form>

<script>

function imageChange_go(){
	$('input[name="checkUpload"]').val(0);
	preViewPicture($('input#inputFile')[0],$("div#pictureView"));
}

// //1. 이벤트 핸들러
// window.onload = function(){
// 	//jQuery가 open-footer.jsp에 있어서 화면이 다 로딩된 이후에 실행되도록
// 	//변경된 파일을 받아와야 하기때문에 change이벤트가 받아온 정보를 (event)에 담아서 넘겨줌
// 	$('input#inputFile').on('change', function(event){
		
// 		//업로드 확인변수 초기화
//		$('input[name="checkUpload"]').val(0);
		
// 		var fileFormat=
// 			this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
		
// 		//이미지 확장자 jpg 확인
// 		if(!(fileFormat=="JPG" || fileFormat=="JPEG")) {
// 			alert("이미지는 jpg/jpeg 형식만 가능합니다.");
// 			$(this).val("");
// 			return;
// 		}
		
// 		//이미지 파일 용량 체크
// 		if(this.files[0].size>1024*1024*1){
// 			alert("사진 용량은 1MB 이하만 가능합니다.");
// 			$(this).val("");
// 			return;
// 		}
		
// 		//이미지 파일명 표시
// 		$('input[name="tempPicture"]').val(this.files[0].name);
		
// 		//이미지 읽기와 썸네일 표시
// 		//파일을 읽으면서 결과를 result에 담음
// 		//이미지태그에 주면 사이즈가 뭉개지고 다운로드 되기 때문에 잘 사용하지 않음
// 		//background로 하면 다운로드도 안되고 이미지 전체의 비율을 유지해서 원본 이미지를 보여줌 
// 		if(this.files && this.files[0]) {
// 			var reader = new FileReader();
// 			reader.onload = function (e) {  //읽는 이벤트  //e(타겟):파일
// 				$('div#pictureView')
// 					.css({'background-image':'url('+e.target.result+')',
// 						  'background-position':'center',
// 						  'background-size':'cover',
// 						  'background-repeat':'no-repeat'
// 					});
// 			}
// 			reader.readAsDataURL(this.files[0]);
// 		}
// 	});
// }  //common.js로



//jQuery 라이브러리가 다 읽어진 다음에 실행됨
function upload_go(){
	//alert("upload btn click");
	
	if($('input[name="pictureFile"]').val()==""){
		alert("사진을 선택하세요.");
		$('input[name=pictureFile]').click();
		return;
	};
	
	
	//form 태그 양식을 객체화	
	var form = new FormData($('form[role="imageForm"]')[0]);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/member/picture.do",
		data:form,
		type:'post',
		processData:false,
		contentType:false,
		success:function(data){
			//업로드 확인변수 세팅
			$('input[name="checkUpload"]').val(1);
			
			//저장된 파일명 저장.
			$('input#oldFile').val(data); // 변경시 삭제될 파일명
			$('form[role="form"]  input[name="picture"]').val(data);
			
			alert("사진이 업로드 되었습니다.");
		},
		error:function(error){
			alert("현재 사진 업로드가 불가합니다.\n 관리자에게 연락바랍니다.");
		}
	});
	
}




//2.function
//<input id="inputFile" 태그에 onchange="selectFile()"> 추가
//단점 : 이벤트 function인데 해당 이벤트(input태그)를 못가져오기 때문에 잘사용하지 않음
// function selectFile(){  
// 	alert("file selected");
// }

</script>