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
			<h3 style="text-align: center"></h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>許願池</strong>
								</h4>
							</div>
							<div class="panel-body">
								<div class="form-group required">
									<label for="wishType" class="col-md-2 control-label">商品類別</label>
									<div class="col-md-10">
										<select class="form-control" id="wishType" name="wishType">
											<option value="VOUCHERS">禮劵</option>
											<option value="FURNITURES">家居，廚具</option>
											<option value="ELETRONICS">3C家電</option>
											<option value="OUTDOOR">戶外運動</option>
											<option value="WOMAN">女仕用品</option>
											<option value="MAN">男仕用品</option>
											<option value="OTHERS">其他</option>
										</select> 
									</div>
								</div>
								<div class="form-group required">
									<label for="name" class="col-md-2 control-label">商品名稱</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="Name" value="${entity.name}" /> 
									</div>
								</div>
								<div class="form-group required">
									<label for="brand" class="col-md-2 control-label">品牌</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="brand" name="brand"
											placeholder="brand" value="${entity.brand}" />
									</div>
								</div>
<!-- 								<div class="form-group required"> -->
<!-- 									<label for="bonus" class="col-md-2 control-label">許願池剩餘使用次數</label> -->
<!-- 									<div class="col-md-10"> -->
<%-- 										<span>${entity.weekUseCount}</span> <span class="help-block"><div --%>
<!-- 												class="text-danger"></div></span> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group required"> -->
<!-- 									<label for="bannerImage" class="col-md-2 control-label">商品圖片</label> -->
<!-- 									<div class="col-md-10"> -->
<!-- 										<input type="file" class="form-control" id="bannerImage" -->
<!-- 											name="bannerImage"> <span class="help-block"> -->
<!-- 											<div class="text-danger"></div> -->
<!-- 										</span> -->
<!-- 									</div> -->
<!-- 								</div> -->
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">許願</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/wish/list'/>"
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
		//<!-- Save -->	
		$("#saveButton").bind("click", function() {
			var $btn = $(this);
			$btn.button("loading");

			$.post("<c:url value='/admin/wish'/>", "dataForm", function(data) {
				if (data.messages.length == 0) {
					$("#dataForm").trigger("reset");
					//swal("SUCCESS", "許願成功！", "success");
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

