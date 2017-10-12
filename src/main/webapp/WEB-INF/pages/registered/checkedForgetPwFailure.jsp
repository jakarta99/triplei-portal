<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i驗證失敗重新申請忘記密碼</title>

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
		
		<br>
		<div style="text-align:center;padding-top:9vh">
		<h1>驗證失敗 請重新申請忘記密碼!!</h1>
		<img class="checkedForgetPwFailure" name="checkedForgetPwFailure"
			src="/resources/pic/registered/registeredFailure.png" width="40%">
		</div>
		
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	</div>
</body>
</html>