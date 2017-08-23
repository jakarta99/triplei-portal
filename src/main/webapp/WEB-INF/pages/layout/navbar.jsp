<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<li><a href="/login">登入</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>