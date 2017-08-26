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
			<h3>保險公司管理</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>保險公司新增</strong></h4>
							</div>
							<div class="panel-body">
							
								<div class="form-group required">
			    					<label for="code" class="col-md-2 control-label">公司代碼</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="code" name="code" placeholder="code" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
															
								<div class="form-group required">								  					
			    					<label for="name" class="col-md-2 control-label">公司全名</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="name" name="name" placeholder="Name" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
			  					<div class="form-group required">
			    					<label for="shortName" class="col-md-2 control-label">公司簡稱</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="shortName" name="shortName" placeholder="short name" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="sortNo" class="col-md-2 control-label">公司名稱筆畫</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="sortNo" name="sortNo" placeholder="sortNo(顯示順序依此排序)" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
								
								<div class="form-group required">								  					
			    					<label for="bisRatio" class="col-md-2 control-label">資本適足率</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="bisRatio" name="bisRatio" placeholder="bisRatio(-500%~500%)" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="persistencyRatio" class="col-md-2 control-label">保單繼續率</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="persistencyRatio" name="persistencyRatio" placeholder="persistencyRatio(80％~99%)" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="litigationRatio" class="col-md-2 control-label">訴訟率</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="litigationRatio" name="litigationRatio" placeholder="litigationRatio(0％~0.008%)" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="complaintRatio" class="col-md-2 control-label">投訴率</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="complaintRatio" name="complaintRatio" placeholder="complaintRatio" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="appealRatio" class="col-md-2 control-label">申訴率</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="appealRatio" name="appealRatio" placeholder="appealRatio(0％~0.12%)" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="insuranceGuarantyFund" class="col-md-2 control-label">保險安定基金</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="insuranceGuarantyFund" name="insuranceGuarantyFund" placeholder="insuranceGuarantyFund" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="credit_rating" class="col-md-2 control-label">信用評等</label>
			    					<div class="col-md-10">
			      						<input type="text" class="form-control" id="credit_rating" name="credit_rating" placeholder="credit_rating" value=""/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="description" class="col-md-2 control-label">公司簡介</label>
			    					<div class="col-md-10">
			      						<textarea class="form-control" id="description" name="description" placeholder="description(100字以下)" value=""/></textarea>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">								  					
			    					<label for="logo;" class="col-md-2 control-label">公司Logo</label>
			    					<div class="col-md-10">
			      						<input type="file" class="form-control" id="logo;" name="logo"/>
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
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/insurer/list'/>" class="btn btn-lg btn-primary btn-warning btn-block" data-loading-text="Loading">返回</a>
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
			
			$.post("<c:url value='/admin/insurer'/>", "dataForm",
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

