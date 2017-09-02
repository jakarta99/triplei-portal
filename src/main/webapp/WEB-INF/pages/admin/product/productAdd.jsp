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
			<h3>保險商品管理</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>保險商品新增</strong>
								</h4>
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
					<a href="<c:url value='/admin/product/list'/>"
						class="btn btn-lg btn-primary btn-warning btn-block"
						data-loading-text="Loading">返回</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		//<!-- Save -->	

		$("#saveButton").bind(
				"click",
				function() {
					var $btn = $(this);
					$btn.button("loading");

					$.post("<c:url value='/admin/product'/>", "dataForm",
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
<script type="text/javascript">
	function CBSelectedValueToTrue(cb) {
		if (cb.checked) {
			cb.value = "true";
		}
	}
</script>
