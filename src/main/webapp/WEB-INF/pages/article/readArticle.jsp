<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>
<body>
<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	</div>
	<br/><br/><br/>
	<div id="wrap">
		<div class="container">
			<section class="well">
				<h1>文章專欄</h1>
			</section>
			
			
			<br/>
			<br/>
			<br/>
			
			<ul>
				<li><a href='/insurer/list'>各公司資訊</a></li>
				<li><a href='/product/list'>商品專區</a></li>
				<li><a href='/gift/list'>積點專區</a></li>
				<li><a href='/article/list'>文章專欄</a></li>
				<li><a href='/login'>登入</a></li>
			</ul>
			<br/>
			<br/>
			<br/>
			<a href='/qa'>線上客服</a>
		</div>
	</div>
	<!-- Testing inserting images -->
	<div class="container" style="border: blue 1px solid">
		<div title="EditorChoice">
			<a href="/article/editorChoice">
				<h6>${model.title}</h6> <img src="/resources/logo/google-logo.png" alt="#"
				class="rounded float-left" width="20%" height="5%">
			</a> <span>${editorChoice}</span>
		</div>
		
	<!-- Testing ends here -->
	
</body>
</html>



