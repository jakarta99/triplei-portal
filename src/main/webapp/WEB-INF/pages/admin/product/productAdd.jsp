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

	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div style="padding-top:9vh">
			<h3>保險商品管理</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" action="/admin/product" method="post" enctype="multipart/form-data">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>保險商品新增</strong>
								</h4>
							</div>
						</div>
						<input type="file" name="files" multiple="multiple">
						<input type="submit" value="文件上傳">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
				</div>
		</div>

	</div>

</body>
</html>

<script type="text/javascript">
	function CBSelectedValueToTrue(cb) {
		if (cb.checked) {
			cb.value = "true";
		}
	}
</script>
