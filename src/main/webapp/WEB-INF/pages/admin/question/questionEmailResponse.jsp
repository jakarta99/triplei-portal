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
			<h3>回覆Email</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
					<input type="hidden" id="id" name="id" value="${entity.id}"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>回覆Email</strong></h4>
							</div>
							<div class="panel-body">
							
								<div class="form-group required">								  					
			    					<label for="name" class="col-md-2 control-label">問題分類</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="questionType" name="questionType" placeholder="questionType" value="${entity.questionType}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="sortNo" class="col-md-2 control-label">發問人Email</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="askerEmail" name="askerEmail" placeholder="askerEmail" value="${entity.askerEmail}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
								
<!-- 								<div class="form-group required">								  					 -->
<!-- 			    					<label for="bisRatio" class="col-md-2 control-label">問題內容</label> -->
<!-- 			    					<div class="col-md-10"> -->
<%-- 			      						<textarea type="text" class="form-control" id="content" name="content"  rows="8" value="${entity.content}"/></textarea> --%>
<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
<!-- 			    					</div> -->
<!-- 								</div> -->
								
<!-- 								<div class="form-group required">								  					 -->
<!-- 			    					<label for="bisRatio" class="col-md-2 control-label">問題回覆</label> -->
<!-- 			    					<div class="col-md-10"> -->
<!-- 			      						<input type="text" class="form-control" id="content" name="content" placeholder="content" value=""/> -->
<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
<!-- 			    					</div> -->
<!-- 								</div> -->
								<div class="form-group required">
			    					<label for="content" class="col-md-2 control-label">問題回覆</label>
			    					<div class="col-md-10">
			      						<textarea id="emailreply" name="emailreply" class="form-control" rows="8" value="${entity.content}" ></textarea>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
						
							</div>
						</div>	
		 			</form>
				</div>				
			</div>

			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">回覆</a>
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
	
			$.put("<c:url value='/admin/question/emailReply'/>", "dataForm",
					function(data) {
						if (data.messages.length==0) {
							//swal("SUCCESS", "問題資料更新成功", "success");
							alert("回覆Email已寄出");
							$btn.button("reset");
						}
					}, function(data, textStatus, jqXHR) {
						$btn.button("reset");
					});
			
			$btn.button("reset");
		});	
	});
</script>

