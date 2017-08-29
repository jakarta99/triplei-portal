<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>

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
				<sec:authorize access="hasRole('ADMIN')">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">系統管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">使用者管理</a></li>
							<li><a href="#">會員管理</a></li>
							
							
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">客服管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/admin/question/list">問題一覽</a></li>							
							<li><a href="/question/list">問題回覆</a></li>							
							
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">保險商品管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">保險商品管理</a></li>
							<li><a href="/admin/insurer/list">保險公司管理</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">禮品訂單管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							
							<li><a href="#">訂單管理</a></li>
							<li><a href="/admin/gift/list">積點商品上架</a></li>
							<li><a href="/admin/wish/list">許願池查詢</a></li>
							<li><a href="#">XXXX</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">文章管理<span class="caret"></span></a>
						<ul class="dropdown-menu">
							
							<li><a href="/article/getArticles">文章列表</a></li>
							<li><a href="/article/insertArticle">新增文章</a></li>
<!-- 							<li><a href="/article/updateDeleteArticle">修改／刪除文章</a></li> -->
<!-- 							<li><a href="/article/articleCarousel">輪播篩選</a></li> -->
						</ul>
					</li>
				</sec:authorize>


				<li><a href="/insurer/list">各公司資訊</a></li>
				<li><a href="/product/list">商品專區</a></li>
				<li><a href="/gift/list">績點專區</a></li>
				<li><a href="/article/list">文章專欄</a></li>
				<li><a href="/login">登入</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>