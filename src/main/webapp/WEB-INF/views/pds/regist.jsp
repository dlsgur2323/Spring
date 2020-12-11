<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/WEB-INF/views/error/500_by_JSP.jsp"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!-- Content Wrapper. Contains page content -->
  <div>
    <jsp:include page="/WEB-INF/views/content_header.jsp">
    	<jsp:param value="자료실" name="subject"/>
		<jsp:param value="등록" name="item"/>
		<jsp:param value="list.do" name="url"/>    	
    </jsp:include>
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h4>글등록</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${loginUser.id }">
							</div>
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="5"
									placeholder="1000자 내외로 작성하세요."></textarea>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										&nbsp;&nbsp;
										<button class="btn btn-xs btn-primary" type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
									</div>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="registBtn">등 록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn" id="cancelBtn" onclick="CloseWindow();">취 소</button>
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
  		SmartEditor_summernote($('#content'));
  		
  		Attach_action();  //attach_js.jsp 에서 호출
  		
  		//등록버튼에 대한 이벤트
  		$('#registBtn').on('click',function(e){
  			//alert("regist btn click");
  			
  			//파일 추가만 눌러 놓고 파일 추가는 안하고 등록 눌렀을 때 유효성
  			var files = $('input[name="uploadFile"]');
  			for(var file of files){
  				console.log(file.name+" : "+file.value);
  				if(file.value==""){
  					alert("파일을 선택하세요.");
  					file.focus();
  					file.click();
  					return;
  				}
  			}
  			//제목 없을 때 등록 안되게
  			if($("input[name='title']").val()==""){ //form.title.value
  				alert("제목은 필수입니다.");
  				$("input[name='title']").focus();
  				return;
  			}
  			document.registForm.submit();
  		});
  	}
  </script>
  
  <%@ include file="/WEB-INF/views/common/summernote.jsp" %>
  <%@ include file="attach_js.jsp" %>