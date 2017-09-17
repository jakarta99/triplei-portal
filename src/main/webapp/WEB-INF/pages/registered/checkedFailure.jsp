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
								<div class="alert alert-warning">驗證失敗 請重新註冊!!</div>
							</h1>
							<h3>
								失敗的可能原因:<br />
								<li>您驗證帳號的時間離註冊的時間已超過三天，驗證連結已失效</li>
								<li>您的會員帳號已被開通，驗證連結已無法使用</li>
							</h3>

						</div>
						</div>
					</form>
				</div>
			</div>
	</div>
</body>
</html>