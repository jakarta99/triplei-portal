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
</style>
</head>
<body>
	<div class="container-fluid"
		style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div>
			<div class="col-xs-12 col-sm-4"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #5C8DEC;"><!--左半藍 -->
				<div
					style="display: table-cell; vertical-align: top; padding-top: 20vh; text-align: center;">
					<h1 style="color: rgb(254, 249, 246); font-size: 2 em;">保險公司</h1>
					<h3>
						<a href="/insurer/list"
							style="color: rgb(254, 249, 246); opacity: 0.6; font-size: 1em;">各公司列表</a>
					</h3>
					<h3>
						<a href="/insurer/filt"
							style="color: rgb(254, 249, 246); opacity: 0.6; font-size: 1em;">各別項目查詢</a>
					</h3>
				</div>
			</div>
			<div class="col-sm-8"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #FAF7F7;"><!--右半白 -->
				<div
					style="height: 100vh; overflow: auto; position: relative; padding-top: 20vh;">
					<div class="col-xs-1 col-sm-1"></div>
					<div class="col-xs-10 col-sm-10">
						<c:forEach items="${models}" var="model">
							<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3"><!-- 每一個logo -->
								<div class="text-center">
										<img class="insurerzone" src="${model.imgsrc}" alt="${model.shortName}"
										width="150" height="150" name="${model.id}">
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
						<div class="descriptionzone" title="${model.shortName}" id="${model.id}" n>
							<a href="${model.description}" target="_blank">${model.description}</a>
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
	</script>
</body>
</html>
