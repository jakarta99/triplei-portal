<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta property="og:title" content="Triple-I"/>
<meta property="og:image" content="https://i.imgur.com/rEQ5E8X.jpg"/>
<meta property="og:site_name" content="Triple-I"/>
<meta property="og:url" content="3i-life.com.tw"/>
<meta property="og:type" content="website"/>
<meta property="og:description" content="Triple-I 文章專區 最新儲蓄險資訊與消息就在Triple-I"/>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i 閱讀文章</title>
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
	<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
		<div class="col-lg-9" style="height:100%;overflow-y:scroll;color:black">
			<div class="col-sm-12" style="height:40vh;width:100%; margin-top: 3%; display: table">
					<div>
						<h3 style="font-size:180%">${article.title}</h3>
						<p style="font-size:180%">${article.content}</p>
					</div>
			</div>
		</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>

	</div>
</div>

</body>
</html>