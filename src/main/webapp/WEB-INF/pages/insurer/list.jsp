<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>
</head>
<body>
	
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div>
			<div class="col-lg-4"
				style="height: 100vh; display: table; table-layout: fixed;">
				<div style="display: table-cell; vertical-align: middle;">
					<h1>保險公司</h1>
					<h3><a href="/insurer/list">保險公司</a></h3>
					<h3><a href=>各別項目查詢</a></h3>
				</div>
			</div>
			<div class="col-lg-8 bg-info"
				style="height: 100vh; display: table; table-layout: fixed;">

				<div style="display: table-cell; vertical-align: middle;">

					<c:forEach items="${models}" var="model">
					<a onclick = "showDetails(${model.id})">${model.id}, ${model.shortName}, ${model.name}</a><br/>
					</c:forEach>
				</div>
				<div class="descriptionzone" style="float:right;margin:40% 40% auto auto;background-color:white;border:1px solid black;">
					<c:forEach items="${models}" var="model">
					${model.id}
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('.descriptionzone').hide();
		function showDetails(id){
			alert(id);
			$('.descriptionzone').show(1500);
		}
	</script>
</body>
</html>
