<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<body>
  <!-- Content Wrapper. Contains page content -->
  <div style="min-height:812px;">
   <jsp:include page="/WEB-INF/views/content_header.jsp">
   	<jsp:param value="공지사항" name="subject"/>
   	<jsp:param value="list.do" name="url"/>
   	<jsp:param value="등록" name="item"/>
   </jsp:include>

    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-9" style="max-width:960px;">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title p-1">공지등록</h3>
						<div class ="card-tools">
							<button type="button" class="btn btn-primary" id="registBtn">등 록</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" id="cancelBtn" onclick="CloseWindow();" >취 소</button>
						</div>
					</div><!--end card-header  -->
					<div class="card-body pad">
						<form role="form" method="post" action="regist.do" name="registForm">
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>							
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${loginUser.id }">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="textarea" name="content" id="content" rows="20"
									placeholder="1000자 내외로 작성하세요."></textarea>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer" style="display:none">
					
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
<script>
window.onload=function(){
	
	//summernote 함수 호출
	SmartEditor_summernote($('#content'));
	
	$('#registBtn').on('click',function(e){
		//alert("regist btn click");
		
		var form=document.registForm;
		
		if(form.title.value==""){
			alert("제목은 필수입니다.");
			return;
		}
		var content = $('textarea[name="content"]');
		if(content.val()==""){
			alert("내용은 필수입니다.");
			content.focus();
			return;
		}
		form.submit();
	});
}
</script>

<%@ include file="/WEB-INF/views/common/summernote.jsp" %>

</body>






