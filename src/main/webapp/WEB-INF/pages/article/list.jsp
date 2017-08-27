<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/slick/slick.min.js"/>"></script>
<link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />

<title>Triple i</title>

<style>
.articleSections{
width:99%;
} 
.articleSections a{

}
.articleSections img{
width:85%;
margin-left:8%;
}
.articleSections p{
margin-left:8%;
width:85%;
text-align:justify;
}
.articleSections h2{
margin:3% auto 2% 4%;
text-decoration:underline;
}
.articlesRight{
float:right;
width:50%;
}
.articlesLeft{
float:left;
width:50%;
}

</style>
</head>

<body>
<div class="container-fluid" style="margin-bottom:4%">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	</div>

		<div>
		<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
		</div>

			
	<!-- Articles Carousel -->
	<div class="col-lg-8 bg-info"
				style="height: 100vh; display: table; table-layout: fixed;float:right;padding:0;">

				<div style="display: table-cell; vertical-align: middle;">
	<div id="ArticleCarousel" style="width:100%;margin:none;">
		<div>
			<a href="${carousel1Url}"><img src="${carousel1ImagePath}" alt="${carousel1Title}"></a>
		</div>
		<div>
			<a href="${carousel2Url}"><img src="${carousel2ImagePath}" alt="${carousel2Title}"></a>
		</div>
		<div>
			<a href="${carousel3Url}"><img src="${carousel3ImagePath}" alt="${carousel3Title}"></a>
		</div>
	</div>
	<!-- Carousel ends here -->
	
	<!-- Testing inserting images -->
	<div>
		<div class="articleSections" title="EditorChoice">
		<h2>編輯精選</h2>
		<div class="articlesLeft">
			<a href="/article/editorChoice">
				 <img src="/resources/logo/google-logo.png" alt="編輯精選"
				class="rounded float-left">
			</a> 
						<p>${editorChoice}</p>
			</div>
			<div class="articlesRight">
			<a href="/article/editorChoice">
				 <img src="/resources/logo/google-logo.png" alt="編輯精選"
				class="rounded float-left" >
			</a> 
						<p>${editorChoice}</p>
			</div>
		</div>
		
		<div class="articleSections" title="News">
		<h2>新聞專區</h2> 
		<div class="articlesLeft">
			<a href="/article/news" >
				<img src="/resources/logo/google-logo.png" alt="新聞專區"
				class="rounded float-right" >
			</a> 
						<p>${news}</p>
			</div>
			<div class="articlesRight">
			<a href="/article/news" >
				<img src="/resources/logo/google-logo.png" alt="新聞專區"
				class="rounded float-right">
			</a> 
						<p>${news}</p>
			</div>
		</div>
		
		<div class="articleSections" title="GoodRead">
		<h2>小資組必讀</h2>
		<div class="articlesLeft">
			<a href="/article/goodRead">
				 <img src="/resources/logo/google-logo.png" alt="小資組必讀"
				class="rounded float-left" >
			</a> 
						<p>${goodRead}</p>
			</div>
			<div class="articlesRight">
			<a href="/article/goodRead">
				 <img src="/resources/logo/google-logo.png" alt="小資組必讀"
				class="rounded float-left" >
			</a> 
						<p>${goodRead}</p>
			</div>
		</div>
		
		<div class="articleSections" title="InvestmentTips">
		<h2>理財觀念</h2>
		<div class="articlesLeft">
			<a href="/article/investmentTips">
				 <img src="/resources/logo/google-logo.png" alt="理財觀念"
				class="rounded float-right">
			</a> 
						<p>${investmentTips}</p>
			</div>
			<div class="articlesRight">
			<a href="/article/investmentTips">
				 <img src="/resources/logo/google-logo.png" alt="理財觀念"
				class="rounded float-right">
			</a> 
						<p>${investmentTips}</p>
			</div>
		</div>
	</div>
	<!-- Testing ends here -->

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
				dots:false,
				lazyload:"ondemand",
				adaptiveHeight:true,
			});
		});
	</script>
</body>
</html>



