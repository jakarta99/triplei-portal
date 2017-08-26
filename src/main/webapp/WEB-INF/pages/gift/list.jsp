<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	<div id="wrap">

		<div class="container">
			<section class="well">
				<h1>積點專區</h1>
			</section>


			<br /> <br /> <br />

			<ul>
				<li><a href='/insurer/list'>各公司資訊</a></li>
				<li><a href='/product/list'>商品專區</a></li>
				<li><a href='/gift/list'>積點專區</a></li>
				<li><a href='/article/list'>文章專欄</a></li>
				<li><a href='#'>登入</a></li>
			</ul>
			<br /> <br /> <br /> <a href='/qa'>線上客服</a>
		</div>
		<div class="col-lg-8 bg-info"
			style="height: 100vh; display: table; table-layout: fixed;">

			<div style="display: table-cell; vertical-align: middle;">
				<table style="border:2px solid;back ">
					<tr>
						<td>積點商品名稱</td>
						<td>品牌</td>
						<td>商品兌換點數</td>
						<td>累積兌換次數</td>
						<td>最大購買數量</td>
					</tr>
					<c:forEach items="${models}" var="model">
						<tr>
							<td>${model.name}</td>
							<td>${model.brand}</td>
							<td>${model.bonus}</td>
							<td>${model.exchangeCount}</td>
							<td>${model.exchangePersonMax}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>






