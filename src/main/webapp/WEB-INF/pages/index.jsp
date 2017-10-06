<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<!-- <link rel="stylesheet" href="/resources/fullpage/jquery.fullPage.css"> -->
<!-- <script src="/resources/fullpage/jquery.fullPage.min.js"></script> -->
<title>Triple i</title>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}

p{
	font-size:90%;
	opacity: 0.8;
}

</style>

</head>

<body>

<div class="container-fluid" style="padding-right: 0px; padding-left: 0px;">
<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<div>
		<div class="col-xs-12 col-sm-4" style="height: 100vh; display: table; table-layout: fixed; padding-right: 0px; padding-left: 0px;">
		<!-- 左邊白 -->
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<h1 style="font-size: 5vh; margin-top: 30vh;">最專業的保險理財</h1>
				<h1 style="font-size: 5vh; margin-top: 10px; margin-bottom: 25px;">讓你的財富開始起飛</h1>
					<div style="opacity: 0.8; margin-bottom: 7vh;">TRIPLE-I為您準備了簡易的商品比較及公開透明的資訊，讓您輕鬆篩選商品，並擁有專業的保險團隊提供相關知識，即時為您解決理賠問題或保險疑問</div>
					<div class="col-sm-12">
						<a href="/product/list">
							<button id="productBtn" name="productBtn" class="btn btn-md btn-primary" style="background-color: white; color: #5C8DEC; border-radius: 30px;">開始篩選儲蓄險</button>
						</a>
					</div>
					</div>
			<div class="col-sm-1"></div>
			</div>
			<div class="col-xs-12 col-sm-8  text-center " style="height: 100vh; background-color: #5C8DEC; padding-right: 0px; padding-left: 0px;">
				<!--右邊藍 -->
				<div class="col-sm-12"
					style="height: 100%; display: flex; justify-content: center; flex-direction: column;padding-left: 0px;padding-right: 0px"
					align='center' >
					<img alt="" src="/resources/pic/首頁/背景雲.svg" style="position: absolute;top:5vh;">
					<div class="col-sm-12"  id="bm" style="height: 100%"></div>
				</div>
			</div>
			<!-- 第二頁 -->
			<div class="col-xs-12 col-sm-6" style="height: 100vh;;background-color: white">
				<div class="col-xs-0 col-sm-2"></div>
				<div class="col-xs-12 col-sm-10">
					<div class="col-xs-12 col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 3vh">
						<span style="color: #000079; font-weight: bold;">HOT<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px">本月熱搜榜</a></span>
					</div>

					<div class="col-xs-12 col-sm-12" style="height: 80vh; display: flex; flex-direction: column" align='center'>
						<div class="col-xs-12 col-sm-12" style="width:auto; height: 18vh; margin-bottom: 10vh">
							<img class="col-xs-3 col-sm-3" src="${hotproduct[0].insurer.imgsrc}" style="height:100%;padding:0;"/>
							<table class="col-xs-9 col-sm-9" style="height:100%;text-align:left;padding:0;">
								<tr style="padding:0;">
									<td style="font-size:190%;padding-top:1vh;">${hotproduct[0].insurer.name}</td>
								</tr>
								<tr style="font-size:130%;">
									<td class="align-middle" style="margin-top:-1vh;">${hotproduct[0].code} - ${hotproduct[0].localName}</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-12 col-sm-12" style="width:auto; height: 18vh; margin-bottom: 10vh">
							<img class="col-xs-3 col-sm-3" src="${hotproduct[1].insurer.imgsrc}" style="height:100%;padding:0;"/>
							<table class="col-xs-9 col-sm-9" style="height:100%;text-align:left;padding:0;">
								<tr style="padding:0;">
									<td style="font-size:190%;padding-top:1vh;">${hotproduct[1].insurer.name}</td>
								</tr>
								<tr style="font-size:130%;">
									<td class="align-middle" style="margin-top:-1vh;">${hotproduct[1].code} - ${hotproduct[1].localName}</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-12 col-sm-12" style="width:auto; height: 18vh; margin-bottom: 10vh">
							<img class="col-xs-3 col-sm-3" src="${hotproduct[2].insurer.imgsrc}" style="height:100%;padding:0;"/>
							<table class="col-xs-9 col-sm-9" style="height:100%;text-align:left;padding:0;">
								<tr style="padding:0;">
									<td style="font-size:190%;padding-top:1vh;">${hotproduct[2].insurer.name}</td>
								</tr>
								<tr style="font-size:130%;">
									<td class="align-middle" style="margin-top:-1vh;">${hotproduct[2].code} - ${hotproduct[2].localName}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!--右半圖 -->
			<div class="col-xs-12 col-sm-6" style="height: 100vh;;background-color: white">
				<div class="col-xs-12 col-sm-10" style="height: 100%; display: flex; justify-content: flex-end; flex-direction: column" align='center'>
					<img alt="" src="/resources/pic/首頁/首頁插圖.png" style="width: 100%;">
				</div>
				<div class="col-xs-0 col-sm-2"></div>
			</div>

			<!--第三頁 -->
			<div class="col-xs-12 col-sm-6" style="height: 100vh;">
				<div class="col-xs-0 col-sm-2"></div>
				<div class="col-xs-12 col-sm-10">
					<div class="col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 3vh;">
						<div class="col-sm-12" style="display: inline;">
							<a style="color: #000079; font-weight: bold;">HOT</a>
							<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">熱門文章</a>
							<a href="/article/list" style="color: #5C8DEC;">查看更多</a>
						</div>
					</div>

						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh;color:black">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${hotissue[0].id}">
								<img style="width:100%;" src="${hotissue[0].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${hotissue[0].title}</h4></a>
								<p>${hotissue[0].introduction}</p>
							</div>
						</div>
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${hotissue[1].id}">
								<img style="width:100%;" src="${hotissue[1].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${hotissue[1].title}</h4></a>
								<p>${hotissue[1].introduction}</p>
							</div>
						</div>
				</div>
			</div>
			<!--右半邊 -->


			<div class="col-xs-12 col-sm-6" style="height: 100vh; padding-right: 0px; padding-left: 0px">
				<div class="col-xs-12 col-sm-10">
					<div class="col-sm-0 col-sm-12" style="height: 17vh; padding-bottom: 3vh"></div>
					<div class="col-sm-12" style="height: 80vh; display: flex; flex-direction: column;">
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${hotissue[2].id}">
								<img style="width:100%;" src="${hotissue[2].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${hotissue[2].title}</h4></a>
								<p>${hotissue[2].introduction}</p>
							</div>
						</div>
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${hotissue[3].id}">
								<img style="width:100%;" src="${hotissue[3].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${hotissue[3].title}</h4></a>
								<p>${hotissue[3].introduction}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-0 col-sm-2"></div>
			</div>
			<!-- 第四頁 -->
			<div class="col-xs-12 col-sm-6" style="height: 100vh;">
				<div class="col-xs-0 col-sm-2"></div>
				<div class="col-xs-12 col-sm-10">
					<div class="col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 3vh;">
						<div class="col-sm-12" style="display: inline;">
							<a style="color: #000079; font-weight: bold;">HOT</a>
							<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">熱門新聞</a>
							<a href="/article/list" style="color: #5C8DEC;">查看更多</a>
						</div>
					</div>

						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh;color:black">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${news[0].id}">
								<img style="width:100%;" src="${hotissue[0].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${news[0].title}</h4></a>
								<p>${news[0].introduction}</p>
							</div>
						</div>
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${news[1].id}">
								<img style="width:100%;" src="${hotissue[1].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${news[1].title}</h4></a>
								<p>${news[1].introduction}</p>
							</div>
						</div>
				</div>
			</div>
			<!--右半邊 -->


			<div class="col-xs-12 col-sm-6" style="height: 100vh; padding-right: 0px; padding-left: 0px">
				<div class="col-xs-12 col-sm-10">
					<div class="col-sm-0 col-sm-12" style="height: 17vh; padding-bottom: 3vh"></div>
					<div class="col-sm-12" style="height: 80vh; display: flex; flex-direction: column;" align='center'>
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${news[2].id}">
								<img style="width:100%;" src="${hotissue[2].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${news[2].title}</h4></a>
								<p>${news[2].introduction}</p>
							</div>
						</div>
						<div class="col-sm-12" style="padding-left: 0px">
							<div class="col-sm-12" style="height: 25vh">
								<div style="margin-top:7%;">
								<a href="/article/readArticle/read/${news[3].id}">
								<img style="width:100%;" src="${hotissue[3].bannerImage}" /></div>
							</div>
							<div class="col-sm-12" style="height: 12vh">
								<h4>${news[3].title}</h4></a>
								<p>${news[3].introduction}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-0 col-sm-2"></div>
			</div>
<!-- 			<div class="col-xs-12 col-sm-12" style="height: 100vh;"> -->

<!-- 					三個框框(熱門新聞) -->
<!-- 					<div class="col-sm-12" style="height: 90vh"> -->
<!-- 					<div class="col-xs-2 col-sm-1"></div> -->
<!-- 					<div class="col-xs-8 col-sm-10" style="padding-left: 0px"> -->
<!-- 					<div class="col-sm-12" style="height: 17vh; display: flex; justify-content: flex-end; flex-direction: column; padding-bottom: 3vh;padding-left: 3vw"> -->
<!-- 						<div class="col-sm-12" style="display: inline;"> -->
<!-- 							<a style="color: #000079; font-weight: bold;">HOT</a> -->
<!-- 							<a style="font-size: 2vw; color: #3C3C3C; font-weight: bold; padding-left: 2px;">熱門新聞</a> -->
<!-- 							<a href="/article/news" style="color: #5C8DEC;">查看更多</a> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-xs-12 col-sm-4" style="height: 80vh;"> -->
<!-- 						<div class="col-sm-12" style="height: 60vh;padding-right: 0px; padding-left: 0px"> -->
<!-- 						<div class="col-sm-12" style="height: 50vh;"> -->
<!-- 							<div style="margin-top:7%;"> -->
<%-- 							<a href="/article/readArticle/read/${news[0].id}"> --%>
<%-- 							<img style="width:100%;" src="${news[0].bannerImage}" /></div> --%>
<!-- 						</div> -->
<!-- 						<div class="col-sm-12" style="height: 20vh;"> -->
<%-- 							<h4>${news[0].title}</h4></a> --%>
<%-- 							<p>${news[0].introduction}</p> --%>
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						</div> -->
						
<!-- 					<div class="col-xs-12 col-sm-4" -->
<!-- 						style="height: 80vh;"> -->
<!-- 						<div class="col-sm-12" style="height: 60vh;padding-right: 0px; padding-left: 0px"> -->
<!-- 						<div class="col-sm-12" style="height: 50vh;"> -->
<!-- 							<div style="margin-top:7%;"> -->
<%-- 							<a href="/article/readArticle/read/${news[1].id}"> --%>
<%-- 							<img style="width:100%;" src="${news[1].bannerImage}" /></div> --%>
<!-- 						</div> -->
<!-- 						<div class="col-sm-12" style="height: 20vh;"> -->
<%-- 							<h4>${news[1].title}</h4></a> --%>
<%-- 							<p>${news[1].introduction}</p> --%>
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						</div> -->
						
<!-- 					<div class="col-xs-12 col-sm-4" -->
<!-- 						style="height: 80vh;"> -->
<!-- 						<div class="col-sm-12" style="height: 60vh;padding-right: 0px; padding-left: 0px"> -->
<!-- 						<div class="col-sm-12" style="height: 50vh;"> -->
<!-- 							<div style="margin-top:7%;"> -->
<%-- 							<a href="/article/readArticle/read/${news[2].id}"> --%>
<%-- 							<img style="width:100%;" src="${news[2].bannerImage}" /></div> --%>
<!-- 						</div> -->
<!-- 						<div class="col-sm-12" style="height: 20vh;"> -->
<%-- 							<h4>${news[3].title}</h4></a> --%>
<%-- 							<p>${news[3].introduction}</p> --%>
<!-- 						</div> -->
<!-- 						</div> -->
<!-- 						</div> -->
					
<!-- 					</div> -->
<!-- 					<div class="col-xs-2 col-sm-1"></div> -->
<!-- 					</div> -->
					
						
<!-- 				</div> -->
			</div>
		</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	<!-- 		 <a src="/triplei-portal/pic/question.png" href="/question/askQuestion"></a> -->
	<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>

<script type="text/javascript">

// $(function(){
// 	$('#dowebok').fullpage();
// });


var animation = bodymovin.loadAnimation({
	  container: document.getElementById('bm'),
	  renderer: 'svg',
	  loop: true,
	  autoplay: true,
	  path: '/resources/pic/首頁/首頁動畫/main.json'
	})
	
	
</script>
</body>
</html>