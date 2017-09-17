<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br /> <br /> <br />
			<h3>修改資料</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="${entity.id}" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>分派業務員</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<div class="col-md-10">
										<input type="hidden" class="form-control" id="pid" name="pid"
											placeholder="pid" value="${entity.product.id}" /> <span
											class="help-block"></span>
									</div>
								</div>

								<div class="panel-body">
									<div class="form-group required">
										<label for="localName" class="col-md-2 control-label">商品名稱</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="localName" name="localName" placeholder="localName"
												value="${entity.product.localName}" /> <span
												class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="code" class="col-md-2 control-label">商品代碼</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="code" name="code" placeholder="code"
												value="${entity.product.code}" /> <span class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="year" class="col-md-2 control-label">商品年期</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="year" name="year" placeholder="year"
												value="${entity.product.year}" /> <span class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="name" class="col-md-2 control-label">姓名</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="name" name="name" value="${entity.name}" /> <span
												class="help-block"></span>
										</div>
									</div>
									
									<div class="form-group required">
										<label for="gender" class="col-md-2 control-label">姓別</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="gender" name="gender" value="${entity.gender}" /> <span
												class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="tel" class="col-md-2 control-label">電話</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="tel" name="tel" value="${entity.tel}" /> <span
												class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="localName" class="col-md-2 control-label">商品名稱</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="localName" name="localName"
												value="${entity.product.localName}" /> <span
												class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="bookedTime_1" class="col-md-2 control-label">預約時段1</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="date" name="bookedTime_1" placeholder="bookedTime_1"
												value="${entity.bookedTime_1}" /> <span class="help-block"></span>
										</div>
									</div>
									<div class="form-group required">
										<label for="bookedTime_2" class="col-md-2 control-label">預約時段2</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="date" name="bookedTime_2" placeholder="bookedTime_2"
												value="${entity.bookedTime_2}" /> <span class="help-block"></span>
										</div>
									</div>
									<div class="form-group required">
										<label for="bookedTime_3" class="col-md-2 control-label">預約時段3</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="date" name="bookedTime_3" placeholder="bookedTime_3"
												value="${entity.bookedTime_3}" /> <span class="help-block"></span>
										</div>
									</div>

									<div class="form-group required">
										<label for="address" class="col-md-2 control-label">地址</label>
										<div class="col-md-10">
											<input type="text" readonly="readonly" class="form-control"
												id="address" name="address" placeholder="地址"
												value="${entity.address}" /> <span class="help-block"></span>
										</div>
									</div>
																	<div class="form-group required">
									<label for="userName" class="col-md-2 control-label">選擇業務員</label>
									<div class="col-md-10">
										<select class="form-control" id="userName" name="userName">
											<option value="測試使用者1號-一般">測試使用者1號-一般</option>
											<option value="測試使用者2號-下單">測試使用者2號-下單</option>
											<option value="測試使用者3號-工讀">測試使用者3號-工讀</option>
											<option value="測試使用者4號-業務員">測試使用者4號-業務員</option>
											<option value="測試使用者5號-客服">測試使用者5號-客服</option>
											<option value="測試使用者6號-文章">測試使用者6號-文章</option>
										</select> <span class="help-block"></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="orderStatus" class="col-md-2 control-label">選擇階段</label>
									<div class="col-md-10">
										<select class="form-control" id="orderStatus" name="orderStatus">
											<option value="第一階段">第一階段</option>
											<option value="第二階段">第二階段</option>
											<option value="第三階段">第三階段</option>
											<option value="第四階段">第四階段</option>
											<option value="第五階段">第五階段</option>
											<option value="第六階段">第六階段</option>
										</select> <span class="help-block"></span>
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
							data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/recipient/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			//<!-- Save -->	
			$("#saveButton").bind(
					"click",
					function() {
						var $btn = $(this);
						$btn.button("loading");
						var name = $("#userName").val();
						var pid = $("#pid").val();
						$.put("<c:url value='/admin/recipient?userName=" + name
								+ "&pid=" + pid + "'/>", "dataForm", function(
								data) {
							if (data.messages.length == 0) {
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
	<script type="text/javascript">
		function CBSelectedValueToTrue(cb) {
			if (cb.checked) {
				cb.value = "true";
			}
		}
	</script>



</body>
</html>
