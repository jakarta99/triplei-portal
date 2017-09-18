<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
</head>
<body>
	<br>
	<br>
	<br>
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<div class="panel panel-primary">

							<div class="panel-body">
							<h1>
								<div class="alert alert-success">恭喜您註冊成功!!</div>
							</h1>
							<h3>於右上角登入後即可開始使用</h3>

						</div>
						</div>
					</form>
				</div>
			</div>
	</div>
</body>
</html>