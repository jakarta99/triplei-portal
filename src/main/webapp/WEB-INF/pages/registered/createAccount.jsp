<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<h3>免費註冊</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>註冊</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="name" class="col-md-2 control-label">會員姓名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="name" value="" /> <span class="help-block">
											<div class="text-danger"></div>
										</span>
									</div>
								</div>

								<div class="form-group required">
									<label for="email" class="col-md-2 control-label">電子信箱</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="email"
											name="email" placeholder="Email" value="" /> <span
											class="help-block">
											<div class="text-danger"></div>
										</span>
									</div>
								</div>

								<div class="form-group required">
									<label for="password" class="col-md-2 control-label">設定密碼</label>
									<div class="col-md-8">
										<input type="password" class="form-control" id="password"
											name="password" placeholder="password" value="" /> 
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								
								<div class="form-group required">
									<label for="checkPassword" class="col-md-2 control-label">確認密碼</label>
									<div class="col-md-8">
										<input type="password" class="form-control" id="checkPassword"
											name="checkPassword" placeholder="checkPassword" value="" /> 
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								

								<div class="form-group required">
									<label for="gender" class="col-md-2 control-label">性別</label>
									<div class="col-md-8">
										<select id="gender" name=gender class="form-control">
											<option value="female">女性</option>
											<option value="male">男性</option>
										</select>
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
							data-loading-text="Loading" id="saveButton">註冊</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/login'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script tyep="text/javascript">
		$(function() {
			//<!-- Save -->	
			$("#saveButton").on("click",
					function() {
						var $btn = $(this);
						$btn.button("loading");
						
						$.post("<c:url value='/registered'/>", "dataForm",
								function(data) {
									if (data.messages.length == 0) {
										//$("#dataForm").trigger("reset");
										
										location.href = '/registered/checkLetter?email=' + data.data.email
										
										$btn.button("reset");
									}
								}, function(data, textStatus, jqXHR) {
									$btn.button("reset");

								});
						$btn.button("reset");
					});
		})
	</script>

</body>
</html>