<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces="true" %>

<script>

//이벤트 핸들러로 만들면 재활용이 불가능함
window.onload=function(){
	//사진이미지 불러오기
	var imageURL = "getPicture.do?picture=${member.picture}";
	
	$('div#pictureView').css({'background-image':'url('+imageURL+')',
		  					  'background-position':'center',
		 					  'background-size':'cover',
							  'background-repeat':'no-repeat'
							});
	
// 	$('input#inputFile').on('change',function(event){
// 		alert("file select");
// 		preViewPicture(this,$('div#pictureView'));
// 		//이미지 변경 확인
// 		$()
// 	});  //function call로 변경  ==> imageChange_go()

	$('#modifyBtn').on('click',function(e){
		var form = $('form[role="form"]');
		form.submit();
	});
	$('#cancelBtn').on('click',function(e){
		//alert("cancel btn click");
		history.go(-1);
	});


}

function imageChange_go(){
	
	var inputImage = $('input#inputFile')[0];
	
	preViewPicture(inputImage,$('div#pictureView'));
	//이미지 변경 확인
	$('input[name="uploadPicture"]').val(inputImage.files[0].name);
}

</script>