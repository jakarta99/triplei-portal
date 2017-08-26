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
			<h3>積點商品管理</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
					<input type="hidden" id="id" name="id" value="${entity.id}"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>保險公司修改</strong></h4>
							</div>
							<div class="panel-body">
							
<!-- 								<div class="form-group required"> -->
<!-- 			    					<label for="code" class="col-md-2 control-label">公司代碼</label> -->
<!-- 			    					<div class="col-md-10"> -->
<%-- 			    						<span class="control-label">${entity.code}</span> --%>
<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
<!-- 			    					</div> -->
<!-- 								</div>	 -->
								
								<div class="form-group required">
									<label for="name" class="col-md-2 control-label">積點商品名稱</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="Name" value="${entity.name}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="brand" class="col-md-2 control-label">品牌</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="brand"
											name="brand" placeholder="Brand" value="${entity.brand}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="bonus" class="col-md-2 control-label">商品兌換點數</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="bonus"
											name="bonus" placeholder="bonus" value="${entity.bonus}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="exchangeCount" class="col-md-2 control-label">累積兌換次數</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="exchangeCount"
											name="exchangeCount" placeholder="exchangeCount" value="${entity.exchangeCount}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="exchangePersonMax" class="col-md-2 control-label">最大購買數量</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="exchangePersonMax"
											name="exchangePersonMax" placeholder="exchangePersonMax" value="${entity.exchangePersonMax}" /> <span
											class="help-block"><div class="text-danger"></div></span>
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
						<a href="<c:url value='/admin/gift/list'/>" class="btn btn-lg btn-primary btn-warning btn-block" data-loading-text="Loading">返回</a>
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
	
			$.put("<c:url value='/admin/gift'/>", "dataForm",
					function(data) {
						if (data.messages.length==0) {
							//swal("SUCCESS", "保險公司資料更新成功", "success");
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

