<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}

p {
	font-size: 90%;
	opacity: 0.8;
}
justtag{
}
</style>

</head>

<body>
<!-- 抓時間用 -->
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<!--以下首頁頁面 -->
<div class="container-fluid" style="padding-right: 0px; padding-left: 0px;">
<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<div>
	<!--第一頁開始 -->
	<div class="col-xs-12 col-sm-4" style="height: 80vh; display: table; table-layout: fixed; padding-right: 0px; padding-left: 0px;" id="first">
		<!-- 左邊白 -->
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<h1 style="font-size: 5vh; margin-top: 30vh;" id="firsth1">最專業的保險理財</h1>
				<h1 style="font-size: 5vh; margin-top: 10px; margin-bottom: 25px;">讓你的財富開始起飛</h1>
				<div style="opacity: 0.8; margin-bottom: 7vh;">TRIPLE-I為您準備了簡易的商品比較及公開透明的資訊，讓您輕鬆篩選商品，並擁有專業的保險團隊提供相關知識，即時為您解決理賠問題或保險疑問</div>
				<div class="col-sm-12">
					<a href="/product/list">
						<button id="productBtn" name="productBtn" class="btn btn-md btn-primary"
							style="background-color: white; color: #5C8DEC; border-radius: 30px;">開始篩選儲蓄險</button></a>
				</div>
			</div>
			<div class="col-sm-1"></div>
	</div>
	<div class="col-xs-12 col-sm-8  text-center " style="height: 100vh; background-color: #5C8DEC; padding-right: 0px; padding-left: 0px;" id="no1pageimg">
		<!--右邊藍 -->
		<div class="col-sm-12" style="height: 100%; display: flex; justify-content: center; flex-direction: column; padding-left: 0px;
				padding-right: 0px" align='center'>
			<img alt="" src="/resources/pic/首頁/背景雲.svg" style="position: absolute; top: 5vh;">
				<div class="col-sm-12" id="bm" style="height: 100%"></div>
		</div>
	</div>
	<!-- 第二頁開始 -->
	<div class="col-xs-12 col-sm-6" style="height: 100vh; background-color: white">
		<div class="col-xs-0 col-sm-2"></div>
		<div class="col-xs-12 col-sm-10">
			<div class="col-xs-12 col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 3vh">
				<span style="color: #000079; font-weight: bold;">HOT
				<a style="text-decoration: none; font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">本月熱搜榜</a></span>
			</div>
		<div class="col-xs-12 col-sm-12" style="height: 80vh; display: flex; flex-direction: column;" align='center'  id="asdfghjkll">
			<c:forEach var="hotproduct" items="${hotproduct}">
				<div class="col-xs-12 col-sm-12" style="width: auto; height: 18vh; margin-bottom: 8vh;">
					<a href="/product/${hotproduct.id}/Female/${year-30}-01-01/10000/10">
					<img class="col-xs-3 col-sm-3" src="${hotproduct.insurer.imgsrc}" style="width: 6vw; padding: 0;" />
						<div class="col-xs-9 col-sm-9" style="height: 100%; text-align: left; padding: 0;">
							<div style="padding: 0;">
								<div style="font-size: 190%; padding-top: 1vh;" id="asdfghjkl" class="justtag">${hotproduct.insurer.name}</div>
							</div>
							<div >
								<div class="align-middle" style="font-size: 130%;margin-top: -1vh;">${hotproduct.code} - ${hotproduct.localName}</div>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		</div>
	</div>
	<!--右半圖 -->
	<div class="col-xs-12 col-sm-6" style="height: 100vh; background-color: white" id="no2pageimg">
		<div class="col-xs-12 col-sm-10" style="height: 100%; display: flex; justify-content: flex-end; flex-direction: column" align='center'>
			<img alt="" src="/resources/pic/首頁/首頁插圖.png" style="width: 100%; margin-bottom: 15%">
		</div>
		<div class="col-xs-0 col-sm-2"></div>
	</div>
	
	<!--第三頁開始-->
	<div class="col-sm-12" style="height: 100vh;">
	<div class="col-xs-0 col-sm-1"></div>
		<div class="col-xs-12 col-sm-10">
			<div class="col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 1%;">
				<div class="col-sm-12" style="display: inline;">
					<a style="color: #000079; font-weight: bold;">HOT</a>
					<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">熱門文章</a>
					<a href="/article/list" style="color: #5C8DEC;">查看更多</a>
				</div>
			</div>
			<c:forEach var="hotissue" items="${hotissue}">
				<div class="col-sm-6" style="color:black">
					<div style="margin-top:7%;">
						<a href="/article/readArticle/read/${hotissue.id}">
						<img style="width:100%;" src="data:image/jpg;base64,${hotissue.showImage}" />
						<h4>${hotissue.title}</h4></a>
						<p>${hotissue.introduction}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	<div class="col-xs-0 col-sm-1"></div>
	</div>
	
	<!-- 第四頁 -->
	<div class="col-sm-12" style="height: 100vh;padding-top:3%;">
	<div class="col-xs-0 col-sm-1"></div>
		<div class="col-xs-12 col-sm-10">
			<div class="col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 1%;">
				<div class="col-sm-12" style="display: inline;">
					<a style="color: #000079; font-weight: bold;">HOT</a>
					<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">熱門新聞</a>
					<a href="/article/list" style="color: #5C8DEC;">查看更多</a>
				</div>
			</div>
			<c:forEach var="news" items="${news}">
				<div class="col-sm-6" style="color:black">
					<div style="margin-top:7%;">
						<a href="/article/readArticle/read/${news.id}">
						<img style="width:100%;" src="data:image/jpg;base64,${news.showImage}" />
						<h4>${news.title}</h4></a>
						<p>${news.introduction}</p>
					</div>
				</div>
			</c:forEach>
		</div>
	<div class="col-xs-0 col-sm-1"></div>
	</div>
	</div>
</div>
<!--內文結束 -->
	<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>

	<script type="text/javascript">
		// $(function(){
		// 	$('#dowebok').fullpage();
		// });

		$(document).ready(function() {
			var wdth = $(window).width();
			if (wdth < 700) {
				$('#no2pageimg').css("height", "40vh");
				$('#no1pageimg').css("height", "40vh");
				$('#firsth1').css("margin-top", "20vh");
				$('#first').css("height", "65vh");
// 				$('#asdfghjkll').css("font-size", "90%");
// 				$('#asdfghjkll').find('div').css("font-size", "110%");
				$('#asdfghjkll').find('.align-middle').css("font-size", "90%");
				$('#asdfghjkll').find('img').css("width", "14vw");
				$('#asdfghjkll').find('.justtag').css("font-size", "140%");
			}
		});
		var animation = bodymovin.loadAnimation({
			container : document.getElementById('bm'),
			renderer : 'svg',
			loop : true,
			autoplay : true,
			path : '/resources/pic/首頁/首頁動畫/main.json'
		})
	</script>
</body>
</html>