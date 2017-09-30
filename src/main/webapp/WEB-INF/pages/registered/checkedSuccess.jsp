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
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<br>
		<div style="text-align:center;padding-top:9vh">
		<h1>恭喜您註冊成功!!</h1>
		<img class="registeredSuccess" name="registeredSuccess"
			src="/resources/pic/registered/registeredSuccess.png" width="40%">
		<h3>於右上角登入後即可開始使用</h3>
		</div>


	</div>
</body>
</html>