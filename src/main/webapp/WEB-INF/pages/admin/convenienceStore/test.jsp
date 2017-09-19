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
			<h3>超商查詢</h3>


			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>超商查詢</strong>
								</h4>
							</div>
							<div class="panel-body">


								<div class="col-md-4" style="border: solid 3px; color: gray;">
									<div class="form-group required">
										<label for="sortNo" class="col-md-2 control-label">縣市</label>
										<div class="col-md-10">
											<select id="city" class="form-control">
												<option value="台北市" selected>請選擇</option>
												<option value="台北市">台北市</option>
												<option value="桃園">桃園</option>
												<option value="台中">台中</option>
												<option value="台南">台南</option>
												<option value="高雄">高雄</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="sortNo" class="col-md-2 control-label">區域</label>
										<div class="col-md-10">
											<select id="region" class="form-control">
												<option value="aaa">aaa</option>
												<option value="bbb">bbb</option>
												<option value="ccc">ccc</option>
												<option value="ddd">ddd</option>
												<option value="eee">eee</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="sortNo" class="col-md-2 control-label">街道</label>
										<div class="col-md-10">
											<select id="street" class="form-control">
												<option value="aaa">aaa</option>
												<option value="bbb">bbb</option>
												<option value="ccc">ccc</option>
												<option value="ddd">ddd</option>
												<option value="eee">eee</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="sortNo" class="col-md-2 control-label">超商</label>
										<div class="col-md-10">
											<select id="store" class="form-control">
												<option value="aaa">aaa</option>
												<option value="bbb">bbb</option>
												<option value="ccc">ccc</option>
												<option value="ddd">ddd</option>
												<option value="eee">eee</option>
											</select>
										</div>
									</div>


								</div>

								<div class="col-md-8" style="border: solid 3px; color: gray;">


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
							data-loading-text="Loading" id="saveButton">回覆</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/question/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript">
		$('#city').on("change", function() {
			var datas = {};
			var city = $('#city').val();
			console.log(city);
			datas.city = city;

			$.ajax({
				type : "GET",
				url : "<c:url value='/admin/convenienceStore/searchRegion'/>",
				dataType : "json",
				data : datas,
				success : function(data) {
					alert(data);
				},
				error : function(data) {
					alert("error");
				}

			});
		});
	</script>
</body>
</html>


