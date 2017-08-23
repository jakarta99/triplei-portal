<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
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

<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" id="mainNavbar">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">Triple-I</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/insurer/list">各公司資訊</a></li>
					<li><a href="/product/list">商品專區</a></li>
					<li><a href="/gift/list">績點專區</a></li>
					<li><a href="/article/list">文章專欄</a></li>
					<li><a href="#">登入</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container-fluid">

		<div>
			<div class="col-lg-4"
				style="height: 100vh; display: table; table-layout: fixed;">
				<div style="display: table-cell; vertical-align: middle;">
					<h1>最專業的保險理財</h1>
					<h1>讓你的財富開始起飛</h1>
					<div>TRIPLE-I為您準備了簡易的商品比較及公開透明的資訊，讓您輕鬆篩選商品，並擁有專業的保險團隊提供相關知識，即時為您解決理賠問題或保險疑問</div>
					<div>
						<br />
					</div>
					<div class="col-md-12 text-center">
						<button id="productBtn" name="productBtn"
							class="btn btn-lg btn-primary">開始篩選儲蓄險</button>
					</div>
				</div>
			</div>
			<div class="col-lg-8 bg-info"
				style="height: 100vh; display: table; table-layout: fixed;">

				<div style="display: table-cell; vertical-align: middle;">

					After traversing the United States from Oregon in just 94 minutes,
					the Great American Eclipse of 2017 leaves the United States at
					South Carolina. After leaving the eastern coast, the Moon's shadow
					passes over most of the Atlantic before ending at sunset near
					Africa. But no other land mass, island or continent, is again
					touched by totality. The path of the total solar eclipse lies
					exclusively within the United States. According to eclipse
					meteorologist Jay Anderson on his website eclipsophile.com, the
					odds of clear skies in South Carolina are fair. If you want to see
					totality in South Carolina, be prepared to quickly relocate based
					on cloud conditions. If the weather forecast the day before is not
					favorable, strongly consider driving to an adjacent state within
					the path with a better forecast. A pro-active eclipse chaser can
					increase her odds of success by studying the short-term weather
					forecast before eclipse day and being prepared to relocate if
					necessary. That being said, it is very possible that you can
					successfully see totality in South Carolina. South Carolina will be
					a significant destination for the eclipse because it will be the
					nearest spot within the path of totality for at least 100 million
					Americans in the Atlantic Seaboard and Florida.
				</div>


			</div>

		</div>
		<div>
			<div class="col-lg-6">
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				保險公司 <br/>
				
			
			</div>
			<div class="col-lg-6">
				文章 <br/>
				文章 <br/>
				文章 <br/>
				文章 <br/>
				文章 <br/>
				文章 <br/>
				文章 <br/>
				
			</div>
		</div>
	</div>
</body>
</html>