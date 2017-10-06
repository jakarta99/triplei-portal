<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<style type="text/css">

h3{
	color:black;
	margin-bottom:2%;
}

content img{
	width:100%;
	margin-top: 1%;
}

</style>
</head>
<body>
<div class="container-fluid" style="width: 100%; height: 100%; position: absolute; padding: 0">
	<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<div id="content" style="padding: 0; width: 100%; height: 100%; color: white;padding-top:8vh">
		<div class="col-lg-3" style="background-color: #5C8DEC; height: 100%; overflow-y: auto;">
			<div class="col-sm-12" style="text-align: center; margin-top: 7%; display: table">
			<br /><br />
			<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>	
			</div>
		</div>
		<div class="col-lg-9" style="height:100%;overflow-y:scroll;color:black">
			<div class="col-sm-12" style="height:40vh;width:100%; margin-top: 3%; display: table">
					<div>
						<h3 style="font-size:180%">${article.title}</h3>
						<p style="font-size:180%">${article.content}</p>
					</div>
			</div>
		</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	</div>
</div>

</body>
</html>