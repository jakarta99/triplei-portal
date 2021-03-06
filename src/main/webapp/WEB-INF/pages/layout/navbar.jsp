<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="icon" type="image/x-icon" href="/resources/pic/icon.png">
<link href="https://fonts.googleapis.com/earlyaccess/cwtexhei.css" rel="stylesheet">
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
	margin-bottom: 0;
}

ul {
	font-size: 95%;
}
.dropdown-menu{
text-align:right;
}

body{
font-family: 微軟正黑體;
}
</style>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top" id="mainNavbar"
	style="border-radius: 0;padding-bottom:2vh">
	<div class="container">
		<div class="navbar-header">

			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

				<a class="navbar-brand" href="/" style="padding: 0; ">
				<img class="img-responsive" src="/resources/pic/logo-white-big.png" style="margin: 0;margin-top:1vh; max-height: 75%;">
				</a>
			
			<!-- 			<a class="navbar-brand" href="/"  style="color:white">Triple-I</a> -->

		</div>
		<div id="navbar" class="navbar-collapse collapse navbar-right"
			style="font-size: 90%; padding: 0; position: relative;text-align:right;float:right;padding-right:15px;overflow-x:hidden">
			<ul class="nav navbar-nav navbar-right">
				<!-- 後台 -->
				<li class="dropdown"><sec:authorize
						access="hasAnyRole('NORMAL','ORDER','PARTTIME','SALES','SERVICE','ARTICLE','ADMIN')">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"
							style="color: white">會員管理<span class="caret"></span></a>
					</sec:authorize>
					<ul class="dropdown-menu">
						<sec:authorize access="hasAnyRole('ADMIN','SERVICE')">
							<li><a href="/admin/user/list">會員管理</a></li>
						</sec:authorize>
						
						<sec:authorize
							access="hasAnyRole('NORMAL','ORDER','PARTTIME','SALES','SERVICE','ARTICLE','ADMIN')">
							<li><a href="/user/reset/info">基本資料設定</a></li>
							<li><a href="/user/reset/pw">重設密碼</a></li>
						</sec:authorize>
						
						<sec:authorize access="hasAnyRole('ORDER','ADMIN')">
							<li><a href="/recipient/list">保險訂單查詢</a></li>
						</sec:authorize>
						
						<sec:authorize access="hasAnyRole('SALES','SERVICE')">
							<li><a href="/recipient/sale/list">接收保險訂單</a></li>
						</sec:authorize>	
					</ul></li>

				<sec:authorize access="hasAnyRole('ADMIN', 'SERVICE')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">客服管理<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/admin/question/list">問題一覽</a></li>
							<!-- 							<li><a href="/question/list">問題回覆</a></li>							 -->

						</ul>
					</li>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ADMIN', 'SERVICE', 'PARTTIME')">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">保險商品管理<span
							class="caret"></span></a>			
						<ul class="dropdown-menu">
							<sec:authorize access="hasAnyRole('ADMIN')">
								<li><a href="/admin/product/list">保險商品管理</a></li>
								<li><a href="/admin/insurer/list">保險公司管理</a></li>
								<li><a href="/admin/convenienceStore/list">超商管理</a></li>	
							</sec:authorize>
							<sec:authorize access="hasAnyRole('ADMIN', 'SERVICE', 'PARTTIME')">					
								<li><a href="/admin/recipient/list">保險訂單管理</a></li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>
					
				<sec:authorize access="hasAnyRole('ADMIN', 'SERVICE' ,'PARTTIME')">	
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">禮品訂單管理<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
						
							<sec:authorize access="hasAnyRole('ADMIN')">	
							<li><a href="/admin/gift/list">積點商品上架</a></li>
							<li><a href="/admin/wish/list">許願池查詢</a></li>
							</sec:authorize>
							
							<sec:authorize access="hasAnyRole('ADMIN', 'SERVICE' ,'PARTTIME')">
							<li><a href="/admin/gift/giftOrder/list">積點商品訂單管理</a></li>
							</sec:authorize>
							
						</ul>
					</li>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ADMIN', 'ARTICLE')">		
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">文章管理<span
							class="caret"></span></a>
						<ul class="dropdown-menu">

							<li><a href="/admin/article/getArticles">文章列表</a></li>
							<li><a href="/admin/article/insertArticle">新增文章</a></li>
						</ul>
					</li>
				</sec:authorize>	


					<!-- 前台 -->
<!-- 					<li><a href="/insurer/list" style="color: white">各公司資訊</a></li> -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">儲蓄險專區<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/product/list">儲蓄險比較</a></li>
							<li><a href="/product/irr">IRR計算機</a></li>
						</ul></li>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" style="color: white">積點專區<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/gift/list" >積點商品列表</a></li>
					<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
							<li><a href="/gift/giftOrder/list" >積點商品訂單</a></li>
					</sec:authorize>
						</ul></li>

					<li><a href="/article/list" style="color: white">文章專欄</a></li>
					<li><a href="/question/askQuestion" style="color: white">聯絡客服</a></li>

					<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
						<li><a href="/login" style="color: white">登入</a></li>
					</sec:authorize>

					<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
						<li><a href="#" onclick="$('#logout').submit();"
							style="color: white"><span
								class="glyphicon glyphicon-log-out" style="color: white"></span>登出</a></li>
						<form class="hide" id="logout" action="<c:url value="/logout" />"
							method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</sec:authorize>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

