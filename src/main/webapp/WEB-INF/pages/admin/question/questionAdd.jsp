<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>

<body>
	
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div>
			<br/><br/><br/>
			<h3>問題管理</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>問題新增</strong></h4>
							</div>
							<div class="panel-body">
							
								<div class="form-group required">
			    					<label for="questionType" class="col-md-2 control-label">問題分類1</label>
			    					<div class="col-md-10">
			    					<select id="questionType" name="questionType" class="form-control" >
			    					<option value="" disabled selected>Select your question</option>
			    					<option  value="ARTICLE">文章類問題</option>
			    					<option  value="POLICY">保單類問題</option>
			    					<option  value="CLAIMS">理賠問題</option>
			    					<option  value="WEBUSE">網站使用及問題回報</option>
			    					<option  value="SALES">業務人員問題</option>
			    					</select>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
															
								<div class="form-group required">								  					
			    					<label for="questionType2" class="col-md-2 control-label">問題分類2</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="questionType2" name="questionType2" placeholder="問題分類2" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
			  					<div class="form-group required">
			    					<label for="email" class="col-md-2 control-label">Email</label>
			    					<div class="col-md-10">
			      						<input type="email" class="form-control" id="askerEmail" name="askerEmail" placeholder="Enter Email" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="content" class="col-md-2 control-label">問題內容</label>
			    					<div class="col-md-10">
			      						<textarea id="content" name="content" class="form-control" rows="8" ></textarea>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
								
<!-- 								<div class="form-group required"> -->
<!-- 			    					<label for="postTime" class="col-md-2 control-label">提問時間</label> -->
<!-- 			    					<div class="col-md-10"> -->
<!-- 			      						<input type="text" class="form-control" id="postTime" name="postTime" placeholder="postTime" value=""/> -->
<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
<!-- 			    					</div> -->
<!-- 								</div>	 -->
								
			
								
							</div>
						</div>	
		 			</form>
				</div>				
			</div>

			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/question/list'/>" class="btn btn-lg btn-primary btn-warning btn-block" data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>
	
	
	
</body>
</html>
<script type="text/javascript">
$(function() {
	//<!-- Save -->	
	$("#saveButton").bind("click",
		function() {
		var $btn = $(this);
			$btn.button("loading");
			
			$.post("<c:url value='/admin/question'/>", "dataForm",
				function(data) {
					if (data.messages.length == 0) {
						$("#dataForm").trigger("reset");
						//swal("SUCCESS", "資料新增成功！", "success");
						alert("SUCCESS");
						$btn.button("reset");
					}
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
			
				$btn.button("reset");
		});	
	});
</script>
