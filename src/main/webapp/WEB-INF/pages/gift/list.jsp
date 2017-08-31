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

	<div id="wrap" style="margin-top: 30px;">
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
						<c:forEach items="${modelh}" var="modelh">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelh.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelh.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelh.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelh.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelh.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a href="${pageContext.request.contextPath}/gift/true">查看更多...</a></span>
				<table style="border: 2px solid; margin-left: 200px;">
					<h3 style="margin-left: 200px;">家電兌換:</h3>
					<tr>
						<c:forEach items="${modele}" var="modele">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modele.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modele.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modele.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modele.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modele.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/ELETRONICS">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">禮卷兌換:</h3>
					<tr>
						<c:forEach items="${modelv}" var="modelv">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelv.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelv.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelv.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelv.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelv.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/VOUCHERS">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">家居、廚具兌換:</h3>
					<tr>
						<c:forEach items="${modelf}" var="modelf">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelf.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelf.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelf.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelf.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelf.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/FURNITURES">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">戶外運動商品兌換:</h3>
					<tr>
						<c:forEach items="${modelod}" var="modelod">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelod.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelod.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelod.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelod.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelod.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/OUTDOOR">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">女仕用品兌換:</h3>
					<tr>
						<c:forEach items="${modelw}" var="modelw">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelw.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelw.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelw.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelw.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelw.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/WOMAN">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">男仕用品兌換:</h3>
					<tr>
						<c:forEach items="${modelm}" var="modelm">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelm.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelm.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelm.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelm.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelm.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/MAN">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">其他商品兌換:</h3>
					<tr>
						<c:forEach items="${modelot}" var="modelot">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${modelot.name}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">品牌:${modelot.brand}</p>
									</div>
									<div style="height: 180px; width: 300px;">
										<p style="text-align: center;">圖片:${modelot.image1}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${modelot.bonus}</p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">類別:${modelot.giftType}</p>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/OTHERS">查看更多...</a></span>
			</div>
		</div>
	</div>

</body>
</html>






