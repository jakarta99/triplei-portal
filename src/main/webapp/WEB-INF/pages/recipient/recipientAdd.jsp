<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>

<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>

<title>Triple i</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
.dailogcheck
* {
	font-family: 微軟正黑體;
}
</style>
</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br /> <br /> <br />
			<h3>輸入資料</h3>

			<div class="panel panel-default">
				<form class="form-horizontal" id="dataForm">
					<div class="panel-body">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>基本資料</strong>
								</h4>
							</div>
							<input type="hidden" class="form-control" id="pid" name="pid"
								placeholder="pid" value="${modelp.id}" /> <span
								class="help-block"></span>
						</div>
					</div>

					<div class="panel-body">
						<div class="form-group required">
							<label for="localName" class="col-md-2 control-label">商品名稱</label>
							<div class="col-md-10">
								<input type="text" readonly="readonly" class="form-control"
									id="localName" name="localName" placeholder="localName"
									value="${modelp.localName}" /> <span class="help-block"></span>
							</div>
						</div>

						<div class="form-group required">
							<label for="code" class="col-md-2 control-label">商品代碼</label>
							<div class="col-md-10">
								<input type="text" readonly="readonly" class="form-control"
									id="code" name="code" placeholder="code" value="${modelp.code}" />
								<span class="help-block"></span>
							</div>
						</div>

						<div class="form-group required">
							<label for="year" class="col-md-2 control-label">商品年期</label>
							<div class="col-md-10">
								<input type="text" readonly="readonly" class="form-control"
									id="year" name="year" placeholder="year" value="${modelp.year}" />
								<span class="help-block"></span>
							</div>
						</div>

						<div class="form-group required">
							<label for="name" class="col-md-2 control-label">姓名</label>
							<div class="col-md-10">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="Name" value="${entity.name}" /> <span
									class="help-block"></span>
							</div>
						</div>
						
						<div class="form-group required">
							<label for="gender" class="col-md-2 control-label">姓別</label>
							<div class="col-md-10">
								<input type="radio" id="gender" name="male" value="M" /> <span>男</span>
								<input type="radio" id="gender" name="female" value="F" /> <span>女</span>
							</div>
						</div>

						<div class="form-group required">
							<label for="tel" class="col-md-2 control-label">電話</label>
							<div class="col-md-10">
								<input type="text" class="form-control" id="tel" name="tel"
									value="${entity.tel}" /> <span class="help-block"></span>
							</div>
						</div>

						<div class="form-group required">
							<label for="date_1_1" class="col-md-2 control-label">時間1_1</label>
							<div class="col-md-10">
								<input type="date" class="form-control" id="date_1_1" name="date_1_1"/> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_1_2" class="col-md-2 control-label">時間1_2</label>
							<div class="col-md-10">
								<select class="form-control" id="date_1_2"
									name="date_1_2">
									<option value="上午 ">上午</option>
									<option value="下午 ">下午 </option>
									<option value="晚上">晚上</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_1_3" class="col-md-2 control-label">時間1_3</label>
							<div class="col-md-10">
								<select class="form-control" id="date_1_3"
									name="date_1_3">
									<option value="08:00~12:00">08:00~12:00</option>
									<option value="10:00~12:00">10:00~12:00</option>
									<option value="12:00~14:00">12:00~14:00</option>
									<option value="14:00~16:00">14:00~16:00</option>
									<option value="16:00~18:00">16:00~18:00</option>
									<option value="18:00~20:00">18:00~20:00</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_2_1" class="col-md-2 control-label">時間2_1</label>
							<div class="col-md-10">
								<input type="date" class="form-control" id="date_2_1" name="date_2_1"/> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_2_2" class="col-md-2 control-label">時間2_2</label>
							<div class="col-md-10">
								<select class="form-control" id="date_2_2"
									name="date_2_2">
									<option value="上午 ">上午</option>
									<option value="下午 ">下午 </option>
									<option value="晚上">晚上</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_2_3" class="col-md-2 control-label">時間2_3</label>
							<div class="col-md-10">
								<select class="form-control" id="date_2_3"
									name="date_2_3">
									<option value="08:00~12:00">08:00~12:00</option>
									<option value="10:00~12:00">10:00~12:00</option>
									<option value="12:00~14:00">12:00~14:00</option>
									<option value="14:00~16:00">14:00~16:00</option>
									<option value="16:00~18:00">16:00~18:00</option>
									<option value="18:00~20:00">18:00~20:00</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_3_1" class="col-md-2 control-label">時間3_1</label>
							<div class="col-md-10">
								<input type="date" class="form-control" id="date_3_1" name="date_3_1"/> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_3_2" class="col-md-2 control-label">時間3_2</label>
							<div class="col-md-10">
								<select class="form-control" id="date_3_2"
									name="date_3_2">
									<option value="上午 ">上午</option>
									<option value="下午 ">下午 </option>
									<option value="晚上">晚上</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="date_3_3" class="col-md-2 control-label">時間3_3</label>
							<div class="col-md-10">
								<select class="form-control" id="date_3_3"
									name="date_3_3">
									<option value="08:00~12:00">08:00~12:00</option>
									<option value="10:00~12:00">10:00~12:00</option>
									<option value="12:00~14:00">12:00~14:00</option>
									<option value="14:00~16:00">14:00~16:00</option>
									<option value="16:00~18:00">16:00~18:00</option>
									<option value="18:00~20:00">18:00~20:00</option>
								</select> <span class="help-block"></span>
							</div>
						</div>
						<div class="form-group required">
							<label for="address" class="col-md-2 control-label">地址</label>
							<div class="col-md-10">
								<input type="text" class="form-control" id="address" name="address"/> <span class="help-block"></span>
							</div>
						</div>
					</div>
				</form>
			</div>
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
				<a href="<c:url value='/product/list'/>"
					class="btn btn-lg btn-primary btn-warning btn-block"
					data-loading-text="Loading">返回</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//<!-- Save -->	
				$("#saveButton").on("click", function() {
				var $btn = $(this);
				$btn.button("loading");

				var formData = new FormData();
				formData.append('pid', $("#pid").val());
				formData.append('name', $("#name").val());
				formData.append('gender', $("#gender").val());
				formData.append('date_1_1', $("#date_1_1").val());
				formData.append('date_1_2', $("#date_1_2").val());
				formData.append('date_1_3', $("#date_1_3").val());
				formData.append('date_2_1', $("#date_2_1").val());
				formData.append('date_2_2', $("#date_2_2").val());
				formData.append('date_2_3', $("#date_2_3").val());
				formData.append('date_3_1', $("#date_3_1").val());
				formData.append('date_3_2', $("#date_3_2").val());
				formData.append('date_3_3', $("#date_3_3").val());
				formData.append('tel', $("#tel").val());
				formData.append('address', $("#address").val());
				formData.append('shops', $("#shops").val());
				formData.append('insured_amount', $("#insured_amount").val());
				formData.append('premiumPerYear', $("#premiumPerYear").val());
				formData.append('bonusPoint', $("#bonusPoint").val());

				$.ajax({
					url : "<c:url value='/admin/recipient1'/>",
					method : "POST",
					data : formData,
					processData : false,
					contentType : false,
					success : function(data) {
						alert("SUCCESS");
					}
				})
				$btn.button("reset");
			});
// 			$("#saveButton").bind(
// 					"click",
// 					function() {
// 						var $btn = $(this);
// 						$btn.button("loading");
// 						var pid = $("#pid").val();
// 						$.post("<c:url value='/admin/recipient?pid=" + pid
// 								+ "'/>", "dataForm", function(data) {
// 							if (data.messages.length == 0) {
// 								alert("SUCCESS");
// 								$btn.button("reset");
// 							}
// 						}, function(data, textStatus, jqXHR) {
// 							$btn.button("reset");
// 						});

// 						$btn.button("reset");
// 					});
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
