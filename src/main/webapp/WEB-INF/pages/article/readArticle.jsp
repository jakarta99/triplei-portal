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
	<div class="container-fluid" style="margin-bottom: 4%">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	</div>

	<div>
		<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
	</div>
	<!-- Testing inserting images -->
	<div class="col-lg-8 bg-info"
		style="height: 100vh; display: table; table-layout: fixed; float: right; padding: 0;">

		<div style="display: table-cell;">
		<div style="margin:5% auto auto 8%; width:85%;border:1px black solid">
			<img src="/resources/logo/google-logo.png" alt="#" style="width:100%">
			<h2>${articleEntity.title}</h2>
			<span>${articleEntity.content}</span>
			</div>
		</div>
	</div>
	<!-- Testing ends here -->

</body>
</html>



