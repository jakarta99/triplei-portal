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
			<br />
			<br />
			<br />
			<h3>超商查詢</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>回覆Email</strong>
								</h4>
							</div>
							<div class="panel-body">

							
<div class="col-md-4" style=" border : solid 3px ; color: gray;">
								<div class="form-group required">
									<label for="sortNo" class="col-md-2 control-label">縣市</label>
									<div class="col-md-10">
										<select class="form-control" >
										<option value="台北">台北</option>
										<option value="桃園">桃園</option>
										<option value="台中">台中</option>
										<option value="台南">台南</option>
										<option value="高雄">高雄</option>
										</select>
									</div>
								</div>
								
								<div class="form-group required">
									<label for="sortNo" class="col-md-2 control-label">街道</label>
									<div class="col-md-10">
										<select class="form-control" >
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
										<select class="form-control" >
										<option value="aaa">aaa</option>
										<option value="bbb">bbb</option>
										<option value="ccc">ccc</option>
										<option value="ddd">ddd</option>
										<option value="eee">eee</option>
										</select>
									</div>
								</div>

								
</div>

<div class="col-md-8" style=" border : solid 3px ; color: gray;">


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
		$(function() {
			//<!-- Save -->	
			$("#content").hide();
			$("#questionType").hide();
			$("#saveButton").bind(
					"click",
					function() {
						var $btn = $(this);
						$btn.button("loading");
						$.put("<c:url value='/admin/question/emailReply/"
								+ $("#emailreply").val() + "'/>", "dataForm",
								function(data) {
									if (data.messages.length == 0) {
										//swal("SUCCESS", "問題資料更新成功", "success");
										alert("回覆Email已寄出");
										$btn.button("reset");
									}
								}, function(data, textStatus, jqXHR) {
									$btn.button("reset");
								});

						$btn.button("reset");
						// 			$("#emailreply").value("");
					});
		});
	</script>


</body>
</html>


