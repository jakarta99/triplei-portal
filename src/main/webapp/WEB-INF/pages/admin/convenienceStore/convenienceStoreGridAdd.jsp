<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
			<br /> <br /> <br />
			<h3>新增便利商店</h3>
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>新增便利商店</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="manufacturer" class="col-md-2 control-label">廠商</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="manufacturer"
											name="manufacturer" placeholder="manufacturer" value="">
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="storeName" class="col-md-2 control-label">店名</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="storeName"
											name="storeName" placeholder="storeName" value="" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="city" class="col-md-2 control-label">縣市</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="city" name="city"
											placeholder="city" value="" /> <span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="region" class="col-md-2 control-label">區域</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="region"
											name="region" placeholder="region" value="" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="street" class="col-md-2 control-label">路名</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="street"
											name="street" placeholder="street" value="" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="address" class="col-md-2 control-label">地址</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="address"
											name="address" placeholder="address" value="" /> <span
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
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">新增 </a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/convenienceStore/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		//<!-- 新增問題  -->	
		$("#saveButton").bind(
				"click",
				function() {
					var $btn = $(this);
					$btn.button("loading");
					$.post("<c:url value='/admin/convenienceStore'/>", "dataForm",
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

