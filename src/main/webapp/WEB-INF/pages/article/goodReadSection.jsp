<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">

<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i 小資必讀</title>
<style type="text/css">

h2{
	color:black;
}

content img{
	width:100%;
	margin-bottom: 2%;
	margin-top: 1%;
}

.col-sm-6{
	padding:0;
}

span{
	color:#003377;
	font-size: normal;
}

</style>
</head>
<body>
<div class="container-fluid" style="width: 100%; height: 100%; position: absolute; padding: 0">
	<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<div id="content" style="padding: 0; width: 100%; height: 100%; color: white;padding-top:8vh">
	<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
		<div class="col-lg-9" style="height: 100%; padding: 0; overflow-y: scroll; color: black" id="style-1">
			<div class="col-md-12" id="list">
				<div class="col-sm-12" id="goodRead" style="padding-top: 4%;">
				<h3>小資族必讀</h3>
					<c:forEach var="article" items="${articles}">
						<div class="col-sm-6" style="padding-bottom: 2%;">
							<a href="/article/readArticle/read/${article.id}">
								<span style="font-size: 150%">${article.title}</span>
									<div style="width: 95%;">
										<img style="width: 100%;margin-bottom: 2%;margin-top: 1%;" src="data:image/jpg;base64,${article.showImage}"/>
									</div>
							</a>
							<span>${article.introduction}</span>
						</div>
					</c:forEach>
				</div>	
			</div>
		</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>

	</div>
</div>
</body>
</html>