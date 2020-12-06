<!-- isErrorPage가 true면 런타임이 터져서 온 곳의 익셉션을 인계해준다. false면 인계받지 못한다.-->

<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<div class="content-wrapper" style="min-height: 1136px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>500 Error Page</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Examples</a></li>
			<li class="active">500 error</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class="error-page">
			<h2 class="headline text-red">500 exception</h2>

			<div class="error-content">
				<h3>
					<i class="fa fa-warning text-red"></i> Oops! Something went wrong.
				</h3>

				<p>
					We will work on fixing that right away. Meanwhile, you may <a href="../../index.html">return to dashboard</a> or try using the search form.
				</p>

				<form class="search-form">
					<div class="input-group">
						<input type="text" name="search" class="form-control" placeholder="Search">

						<div class="input-group-btn">
							<button type="submit" name="submit" class="btn btn-danger btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<!-- /.input-group -->
				</form>
			</div>
		</div>
		<!-- /.error-page -->

	</section>
	<!-- /.content -->
</div>