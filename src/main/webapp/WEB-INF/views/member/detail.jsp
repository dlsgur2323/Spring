<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>

  <!-- Content Wrapper. Contains page content -->
  <div >
 
    <!-- Main content -->
    <section class="content register-page" style="height: 586.391px;">       
		<div class="register-box" style="min-width:450px;">         
	    	<form role="form" class="form-horizontal"  method="post">
	        	<div class="register-card-body" >
	            	<div class="row"  style="height:200px;">
						<div class="mailbox-attachments clearfix col-md-12" style="text-align: center;">							
							<div id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>														
						</div>
					</div>
					<br />
	                <div class="form-group row" >
	                  <label for="inputId3" class="col-sm-3 control-label text-right">아이디</label>
	                  <div class="col-sm-9">
	                  	<input name="id" type="text" class="form-control" id="inputId3" value="${member.id }" readonly>
	                  </div>
	                </div>	               
	                <div class="form-group row">
	                  <label for="inputName3" class="col-sm-3 control-label text-right">이  름</label>
	                  <div class="col-sm-9">
	                  	<input name="name" type="text" class="form-control" id="inputName3" value="${member.name }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputEmail3" class="col-sm-3 control-label text-right">이메일</label>
	                  <div class="col-sm-9">
	                  	<input name="email" type="text" class="form-control" id="inputEmail3" value="${member.email }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPhone3" class="col-sm-3 control-label text-right">전화번호</label>
	                  <div class="col-sm-9">  
	                  	<input name="phone" type="text" class="form-control" id="inputPhone3" value="${member.phone }" readonly> 
	                  </div>                  
	                </div>               
	              </div>  
		          <div class="card-footer" >
		          		<div class="row">
			          		
			          		
			          		<div class="col-sm-3 text-center">
			          			<button type="button" id="modifyBtn" class="btn btn-warning ">수 정</button>
			          		</div>
		          		
			          		<div class="col-sm-3 text-center">
			          			<button type="button" id="deleteBtn" class="btn btn-danger" >삭 제</button>
			          		</div>
			          		<div class="col-sm-3 text-center">
			          			<button type="button" id="stopBtn" class="btn btn-info" >정 지</button>
			          		</div>
			          	
			          		<div class="col-sm-3 text-center">
			            		<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
			            	</div>
		          	    </div>  	
		          </div>
	      	  </form>
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@ include file="detail_js.jsp" %>
  
</body>

