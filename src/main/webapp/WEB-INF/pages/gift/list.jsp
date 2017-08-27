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

	<div id="wrap" style="margin-top:30px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
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
		<div
			style="height: 100vh; display: table; table-layout: fixed; border: 1px solid; width: 1400px; margin-left: 20px">

			<div style="display: table-cell; height: 500px; width: 1300px;">
				<table style="border: 2px solid; margin-left: 200px;">
					<h3 style="margin-left: 200px;">熱門兌換:</h3>
					<tr>
						<c:forEach items="${models}" var="model">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${model.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${model.brand}</p>
									</div>
									<div style="height: 200px; width: 300px;">
										<p style="text-align: center;">圖片:${model.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${model.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">累積兌換次數:${model.exchangeCount}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">最大購買數量:${model.exchangePersonMax}</p>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a src="#">查看更多...</a></span>
				<table style="border: 2px solid; margin-left: 200px;">
					<h3 style="margin-left: 200px;">家電兌換:</h3>
					<tr>
						<c:forEach items="${models}" var="model">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${model.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${model.brand}</p>
									</div>
									<div style="height: 200px; width: 300px;">
										<p style="text-align: center;">圖片:${model.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${model.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">累積兌換次數:${model.exchangeCount}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">最大購買數量:${model.exchangePersonMax}</p>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a src="#">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">禮卷兌換:</h3>
					<tr>
						<c:forEach items="${models}" var="model">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${model.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${model.brand}</p>
									</div>
									<div style="height: 200px; width: 300px;">
										<p style="text-align: center;">圖片:${model.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${model.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">累積兌換次數:${model.exchangeCount}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">最大購買數量:${model.exchangePersonMax}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>

				</table>
				<span style="margin-left: 1130px"><a src="#">查看更多...</a></span>
			</div>
		</div>
	</div>

</body>
</html>






