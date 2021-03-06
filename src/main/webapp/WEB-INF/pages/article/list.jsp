<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<link rel="icon" type="image/x-icon" href="/resources/pic/icon.png">
<script type="text/javascript" src="<c:url value="/resources/slick/slick.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />

<title>Triple i 文章首頁</title>

<style type="text/css">

.rotation {
	width: 100%;
	margin-top: 0;
}

#style-1 img {
	width: 100%;
	margin-bottom: 2%;
	margin-top: 1%;
}

.col-sm-6 {
	padding: 0;
}

.summary {
	color: #003377;
	font-size: normal;
}

</style>
</head>

<body>
<div class="container-fluid" style="width: 100%; height: 100%; position: absolute; padding: 0;">
<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<div id="content" style="padding: 0; width: 100%; height: 100%; color: white;padding-top:8vh">
	<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
		<div class="col-lg-9" style="height: 100%; padding: 0; overflow-y: scroll; color: black;" id="style-1">
			<div id="ArticleCarousel" class="col-md-12" style="height: 70vh%; width: 100%; padding: 0;margin-top:-2vh;">
				<div>
					<a href="/article/readArticle/read/${bannerRotation[0].id}">
					<img src="data:image/jpg;base64,${bannerRotation[0].showImage}" title="${bannerRotation[0].title}" class="rotation"></a>
				<img  src="data:image/jpg;base64,${article.showImage}" />
				</div>
				<div>
					<a href="/article/readArticle/read/${bannerRotation[1].id}">
					<img src="data:image/jpg;base64,${bannerRotation[1].showImage}" title="${bannerRotation[1].title}" class="rotation"></a>
				</div>
				<div>
					<a href="/article/readArticle/read/${bannerRotation[2].id}">
					<img src="data:image/jpg;base64,${bannerRotation[2].showImage}" title="${bannerRotation[2].title}" class="rotation"></a>
				</div>
			</div>
				<div class="col-md-12" id="list" style="margin-top: 1%;">
					<div class="col-sm-12" id="editorChoice"><a href="/article/editorChoice"><h3>編輯精選</h3></a>
						<div class="col-sm-6" title="${editorChoice[0].title}">
							<a href="/article/readArticle/read/${editorChoice[0].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${editorChoice[0].showImage}" />
								</div>
							</a><span class="summary">${editorChoice[0].introduction}</span>
						</div>
						<div class="col-sm-6" title="${editorChoice[1].title}">
							<a href="/article/readArticle/read/${editorChoice[1].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${editorChoice[1].showImage}" />
								</div>
							</a> <span class="summary">${editorChoice[1].introduction}</span>
						</div>
					</div>
					<div class="col-sm-12" id="news">
						<a href="/article/news"><h3>新聞專區</h3></a>
						<div class="col-sm-6" title="${news[0].title}">
							<a href="/article/readArticle/read/${news[0].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${news[0].showImage}" />
								</div>
							</a> <span class="summary">${news[0].introduction}</span>
						</div>
						<div class="col-sm-6" title="${news[1].title}">
							<a href="/article/readArticle/read/${news[1].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${news[1].showImage}" />
								</div>
							</a> <span class="summary">${news[1].introduction}</span>
						</div>
					</div>
					<div class="col-sm-12" id="goodRead">
						<a href="/article/goodRead"><h3>小資族必讀</h3></a>
						<div class="col-sm-6" title="${goodRead[0].title}">
							<a href="/article/readArticle/read/${goodRead[0].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${goodRead[0].showImage}" />
								</div>
							</a><span class="summary">${goodRead[0].introduction}</span>
						</div>
						<div class="col-sm-6" title="${goodRead[1].title}">
							<a href="/article/readArticle/read/${goodRead[1].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${goodRead[1].showImage}" />
								</div>
							</a> <span class="summary">${goodRead[1].introduction}</span>
						</div>
					</div>
					<div class="col-sm-12" id="investmentTips">
						<a href="/article/investmentTips"><h3>理財觀念</h3></a>
						<div class="col-sm-6" title="${investmentTips[0].title}">
							<a href="/article/readArticle/read/${investmentTips[0].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${investmentTips[0].showImage}" />
								</div>
							</a> <span class="summary">${investmentTips[0].introduction}</span>
						</div>
						<div class="col-sm-6" title="${investmentTips[1].title}">
							<a href="/article/readArticle/read/${investmentTips[1].id}">
								<div style="width: 95%;">
									<img src="data:image/jpg;base64,${investmentTips[1].showImage}" />
								</div>
							</a><span class="summary">${investmentTips[1].introduction}</span>
						</div>
					</div>
				</div>
			</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>

		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#ArticleCarousel').slick({
				infinite : true,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 2000,
				prevArrow : false,
				nextArrow : false,
				dots : false,
				lazyload : "ondemand",
				adaptiveHeight : true,
			});
		});
	</script>
</body>
</html>