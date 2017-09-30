<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}

.insurername {
	color: #868686;
	font-size: 1.5em;
	padding-top: 10px;
}
.ui-widget-header {
	 border: 0px solid #e78f08; 
	background-color: white;
}
 
</style>
</head>
<body>
	<div class="container-fluid"
		style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding-top:8vh">
			<div class="col-xs-12 col-sm-3"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #5C8DEC;"><!--左半藍 -->
 				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
				<div class="col-xs-6 col-sm-12"
					style="display: table-cell; vertical-align: top; padding-top: 15vh;padding-left: 4vw;">
					<h1 style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;padding-bottom: 5vh">保險公司</h1>
					<h4 style="margin-bottom: 5vh">
						<a href="/insurer/list"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各公司列表</a>
					</h4>
					<h4  style="margin-bottom: 5vh">
						<a href="/insurer/filt"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各別項目查詢</a>
					</h4>
					<div id="bm" style="width: 170%;position: absolute;left: -100px"></div>
				</div>
				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
			</div>
			<div class="col-sm-9"
				style="height: 100vh; display: table; table-layout: fixed; background-color: white;padding-right: 0px; padding-left: 0px;"><!--右半白 -->
				<div
					style="height: 100vh; overflow: auto; position: relative; padding-top: 15vh;" id="style-1">
					<div class="col-xs-1 col-sm-1"></div>
					<div class="col-xs-10 col-sm-10">
						<c:forEach items="${models}" var="model">
							<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3"><!-- 每一個logo -->
								<div class="text-center">
										<img class="insurerzone" src="${model.imgsrc}" alt="${model.shortName}"
										width="125" height="125" name="${model.id}">
								</div>
								<br>
								<div class="text-center">
									<a style="color: #868686;">${model.shortName}</a>
								</div>
								<br>
								<br>
							</div>
						</c:forEach>
					</div>
					<div class="col-xs-1 col-sm-1"></div>


					
						<c:forEach items="${models}" var="model">
						<div class="descriptionzone" id="${model.id}">
						<img src="${model.imgsrc}" alt="${model.shortName}" width="50" height="50" name="${model.id}" style="margin-left: 10%;margin-bottom: 5%;">
							<a style="font-size: 25px; padding-left: 5%;font-weight:bold;">${model.shortName}</a><br>
							<a style="padding-left: 13%">資本適足率　：<fmt:formatNumber type="percent" minFractionDigits="2" value="${model.bisRatio}" /></a><br>
							<a style="padding-left: 13%">資產報酬率　：<fmt:formatNumber type="percent" minFractionDigits="2" value="${model.returnonAssets}" /></a><br>
							<a style="padding-left: 13%">保單繼續率　：<fmt:formatNumber type="percent" minFractionDigits="2" value="${model.persistencyRatio}" /></a><br>
							<a style="padding-left: 13%">訴訟率　　　：<fmt:formatNumber type="percent" minFractionDigits="2" value="${model.litigationRatio}" /></a><br>
							<a style="padding-left: 13%">申訴率　　　：<fmt:formatNumber type="percent" minFractionDigits="5" value="${model.appealRatio}" /></a><br>
							<a style="padding-left: 13%">保險安定基金：<c:if test="${model.insuranceGuarantyFund=true}">已加入</c:if><c:if test="${model.insuranceGuarantyFund=false}">未加入</c:if></a><br>
							<a style="padding-left: 13%">${model.shortName}官網：<a href="${model.description}" target="_blank">${model.description}</a></a><br>
							<a style="padding-left: 13%">${model.description2}</a><br>
							<a style="padding-left: 13%">${model.description3}</a><br>
							<a style="padding-left: 13%">${model.description4}</a><br>
							</div>
						</c:forEach>
					
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.descriptionzone').hide();
			$('.insurerzone').click(function() {
				var aaa = $(this).attr("name");
// 				$(".intro.demo")
				$('.descriptionzone#'+aaa).dialog({
					resizable : true,
					height : "auto",
					width : 500,
					modal : true,
					show : {
						effect : "blind",
						duration : 500
					},
					hide : {
						effect : "blind",
						duration : 500
					}
				});
			});
		});
		
		var animation = bodymovin.loadAnimation({
			  container: document.getElementById('bm'),
			  renderer: 'svg',
			  loop: true,
			  autoplay: true,
			  path: '/resources/pic/各公司資訊/company.json'
			})
			
	</script>
</body>
</html>
