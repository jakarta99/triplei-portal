<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>

<title>Triple i積點專區</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}

.ui-widget-header {
	border: 0px solid #e78f08;
	background-color: white;
}
#dialog-wish::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	background-color: #F5F5F5;
	border-radius: 10px;
}

#dialog-wish::-webkit-scrollbar
{
	width: 10px;
	background-color: #F5F5F5;
}

#dialog-wish::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #9D9D9D;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid"
			style="width: 100%; height: 100%; position: absolute; padding: 0">
			<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

			<div
				style="padding: 0; width: 100%; height: 100%; color: white; padding-top: 7vh">

				<!-- 左半藍 -->
				<div class="col-xs-12 col-sm-3" id="leftLayout"
					style="background-color: #5C8DEC; height: 100vh;">

					<div class="col-xs-1 col-sm-1"></div>
					<div class="col-xs-10 col-sm-10"
						style="height: 100vh; padding-left: 0px; padding-right: 0px;">
						<div class="col-xs-12 col-sm-12"
							style="padding-top: 15vh; padding-bottom: 0vh">
							<span style="font-size: 2.5em; font-family: 微軟正黑體;">T-Point</span><br />
							<span style="font-size: 1.5em; font-family: 微軟正黑體;">積點專區</span>
						</div>
						<div class="col-xs-12 col-sm-12">
							<img alt="" src="/resources/pic/積點專區/點數(大).png" width="41"
								height="41"> <span id="userPoint"
								style="font-size: 3.5em; vertical-align: middle;">${userPoint}</span>
						</div>
						<div class="col-xs-12 col-sm-12">
							<div class="col-xs-12 col-sm-12"
								style="height: 15vh; border: 1px white solid; border-radius: 20px; margin-top: 3vh; padding-left: 0px; padding-right: 0px;">
								<div class="col-xs-6 col-sm-6"
									style="border-right: 1px white solid; height: 80%; margin-top: 5%; display: flex; justify-content: center; flex-direction: column"
									align="center">
									<p style="margin-top: 10%; margin-bottom: 10%;">審核中點數</p>
									<span id="audittingPoint">${audittingPoint}</span>
								</div>
								<div class="col-xs-6 col-sm-6"
									style="height: 80%; margin-top: 5%; display: flex; justify-content: center; flex-direction: column"
									align="center">
									<p style="margin-top: 10%; margin-bottom: 10%">已兌換點數</p>
									<span id="exchangedPoint">${exchangedPoint}</span>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12" id="bm"
							style="width: 140%; position: relative; top: 20px; left: -50px"></div>
					</div>
					<div class="col-xs-1 col-sm-1"></div>
				</div>

				<!-- 右半白 -->
				<div class="col-sm-9"
					style="height: 100%; color: black; overflow-y: auto; padding-top: 7vh">
					<div class="col-sm-12 col-xs-12" style="margin-top: 3vh; margin-bottom: 3vh;">

						<!-- 					hot items col starts here -->
						<c:if test="${not empty modelh}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">熱門兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelh}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelh}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/true">查看更多...</a>
							</div>
						</c:if>
						<!-- 							hot items col ends here -->
						<!-- 					electronics col starts here -->
						<c:if test="${not empty modele}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">家電兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modele}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modele}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/ELECTRONICS">查看更多...</a>
							</div>
						</c:if>
						<!-- 							electronics col ends here -->
						<!-- 					voucher col starts here -->
						<c:if test="${not empty modelv}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">禮卷兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12 col-xs-12"> -->
							<c:forEach items="${modelv}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelv}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/VOUCHERS">查看更多...</a>
							</div>
						</c:if>
						<!-- 							voucher col ends here -->
						<!-- 					furnitures col starts here -->
						<c:if test="${not empty modelf}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">家居、廚具兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelf}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelf}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/FURNITURES">查看更多...</a>
							</div>
						</c:if>
						<!-- 							furnitures col ends here -->
						<!-- 					outdoor col starts here -->
						<c:if test="${not empty modelod}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">戶外運動商品兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelod}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelod}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/OUTDOOR">查看更多...</a>
							</div>
						</c:if>
						<!-- 							furnitures col ends here -->
						<!-- 					ladies col starts here -->
						<c:if test="${not empty modelw}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">女仕用品兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelw}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelw}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/WOMAN">查看更多...</a>
							</div>
						</c:if>
						<!-- 							ladies col ends here -->
						<!-- 					men col starts here -->
						<c:if test="${not empty modelm}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">男仕用品兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelm}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelm}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/MAN">查看更多...</a>
							</div>
						</c:if>
						<!-- 							men col ends here -->
						<!-- 					others col starts here -->
						<c:if test="${not empty modelot}">
							<div class="col-sm-12 col-xs-12" style="padding-bottom: 2vh">
								<span style="font-size: 190%">其他商品兌換：</span>
							</div>
						</c:if>
<!-- 						<div class="col-sm-12"> -->
							<c:forEach items="${modelot}" var="model">
								<div class="col-sm-3 col-xs-5"
									style="border: #5C8DEC 1px solid; border-radius: 2px; margin-left: 2vw; margin-top:3vh;">
									<div class="col-sm-12 col-xs-12">
										<img id="image" class="img-responsive"
											style="margin-top: 2vh; height: 100%; width: 100%;"
											src="data:image/jpg;base64,${model.showImage}">
									</div>
									<div class="col-sm-12 col-xs-12"
										style="padding-left: 0; margin-top: 2vh; margin-bottom: 3vh; padding-bottom: 1vh;">
										<div class="col-sm-12 col-xs-12" style="padding: 0;">
											<span id="giftName" class="col-sm-8 col-xs-8 giftNames"
												style="font-size: 135%;">${model.name}</span>
											<div class="col-sm-12 col-xs-12">
												<div class="col-sm-8 col-xs-8" style="padding: 0;">
													<img alt="" style="margin-top: -1vh; height: 2.5vh;"
														src="/resources/pic/積點專區/點數(小).png"> <span
														id="points" 
														style="font-size: 125%; height: 1.5vh; color: #5C8DEC; margin-top: 1vh">${model.bonus}</span>
												</div>
												<div class="col-sm-4 col-xs-4" style="padding: 0; float: right">
												<button id="placeOrder" class="btn btn-xs btn-sm btn-primary"
													style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">立即兌換</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
<!-- 						</div> -->
						<c:if test="${not empty modelot}">
							<div class="col-sm-12 col-xs-12" align="right">
								<a href="${pageContext.request.contextPath}/gift/OTHERS">查看更多...</a>
							</div>
						</c:if>
						<!-- 							others col ends here -->
					</div>
				</div>
				<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>
				<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
			</div>
		</div>
	</div>

	<div id="dialog" class="dialog-init" title="商品明細">

		<form>
			<div class="form-group">
				<div class="container-fluid">
					<div class="col-sm-7 ">
						<div class="" style="margin-top: 20px; font-size: 125%">
							<label class="">積點商品名稱: </label> <span id="orderName"
								style="text-align: center;"></span>
						</div>
						<div class="" style="margin-top: 20px; font-size: 125%">
							<label class="">請輸入數量:</label> <input class="" type="number"
								class="" id="quantity1" name="quantity1" min="1" value="1"
								style="width: 20%" />
						</div>
						<div class="" style="margin-top: 20px; font-size: 125%">
							<label class="">商品兌換點數: </label> <img alt=""
								src="/resources/pic/積點專區/點數(小).png" width="20" height="20">
							<span id="orderPoint" style="text-align: center;"></span>
						</div>
					</div>
					<div class="col-sm-4 ">
						<img class="img-responsive" id="orderImage" style="height: 200px; width: 200px;">
					</div>
				</div>
				<hr>
				<div style="margin-top: 10px;" class="">
					<label for="recipient" class="">收件人姓名:</label> <input type="text"
						class="" class="" id="recipient" name="recipient"
						style="width: 20%" />
				</div>
				<div style="margin-top: 10px;" class="">
					<label for="recipientAddress" class="">收件人地址:</label> <input
						class="" type="text" class="" id="recipientAddress"
						name="recipientAddress" style="width: 60%" />
				</div>
				<div style="margin-top: 10px;" class="">
					<label for="recipientPhone" class="">收件人電話:</label> <input class=""
						type="text" class="" maxlength="13" id="recipientPhone"
						name="recipientPhone" style="width: 30%" />
				</div>
				<div style="margin-top: 10px;" class="">
					<label for="recipientTime" class="">希望收件時間:</label> <select
						id="recipientTime" name="recipientTime" class="">
						<option value="上午" selected>上午</option>
						<option value="下午">下午</option>
						<option value="晚上">晚上</option>
					</select>
				</div>
				<div>
					<div class="col-sm-9 "></div>
					<div class="col-sm-3 ">
						<a href="#" class="btn btn-secondary" data-loading-text="Loading"
							id="saveButton"
							style="background-color: white; color: #5C8DEC; border: #5C8DEC 1px solid">確認購買</a>
					</div>
				</div>
			</div>
		</form>

	</div>
	<!-- 許願池dialog -->
	<div id="dialog-wish" class="dialog-init">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px">
				<div class="col-xs-12 col-sm-8" style="padding-left: 0px">
					<h3>許願池</h3>
					<p>
						積點專區的商品無法滿足你的慾望嗎?別擔心,你可以在這裡告訴我們您想要的商品,TRIPLE-I會盡全力滿足您的願望!快許下願望吧</p>
				</div>
				<div class="col-xs-12 col-sm-4" style="padding-right: 0px">
					<img id="wishpool" alt="" src="/resources/pic/積點專區/許願池.png"
						width="150" height="150">
				</div>
			</div>
			<div class="col-sm-12" style="padding-left: 0px; padding-bottom: 1vh">希望增加的兌換商品：</div>
			<form enctype="multipart/form-data" method="post" id="dataForm">
				<div class="col-xs-12 col-sm-3"
					style="border: 1px #5C8DEC solid; height: 20vh; display: flex; justify-content: center; flex-direction: column"
					align="center" id="imgid">
									<label for="image1"
										style="background-color: white; color: #5C8DEC; font-family: 微軟正黑體;">圖片(必要):</label>
									<input type="file" id="image1" name="image1"
										style="border: 0px #5C8DEC solid" value="0">
				</div>
				<div class="col-xs-12 col-sm-9" id="imgid2">
					<div class="col-sm-12"
						style="height: 9vh; border: 1px #5C8DEC solid; margin-bottom: 2vh; display: flex; justify-content: center; flex-direction: column">
						<div style="">
							<label for="brand" style="color: #5C8DEC; font-family: 微軟正黑體;">品牌:</label>
							<input type="text" placeholder="請輸入品牌名稱" id="brand" name="brand"
								style="border: 0px #5C8DEC solid">
						</div>
					</div>
					<div class="col-sm-12"
						style="height: 9vh; border: 1px #5C8DEC solid; margin-bottom: 2vh; display: flex; justify-content: center; flex-direction: column">
						<div style="">
							<label for="name" style="color: #5C8DEC; font-family: 微軟正黑體;">商品名稱:</label>
							<input type="text" placeholder="請輸入商品名稱" id="name" name="name"
								style="border: 0px #5C8DEC solid">
						</div>
					</div>
				</div>
			</form>
			<div class="col-sm-12" style="height: 10vh" align="right">
				<a class="btn btn-primary" data-loading-text="Loading"
					id="wishButton"
					style="margin-top: 4vh; background-color: white; border: 1px #5C8DEC solid; color: #5C8DEC; border-radius: 20px; font-size: 15px;">許願</a>
			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>

	<!-- <div id="dialog-success"> -->
	<!-- <img alt="" src="/resources/pic/積點專區/許願成功.png" class="col-sm-10" style="width: 500"><br/> -->
	<!-- <div class="col-sm-10"> -->
	<!-- <p style="background-color: white;color: black;font-family: 微軟正黑體;font-size: 30px" align="center">許願成功</p> -->
	<!-- </div> -->
	<!-- </div> -->

	<!-- <div id="dialog-failed"> -->
	<!-- <img alt="" src="/resources/pic/積點專區/只能許一次.png" class="col-sm-10" style="width: 500"><br/> -->
	<!-- <div class="col-sm-10"> -->
	<!-- <p style="background-color: white;color: black;font-family: 微軟正黑體;font-size: 30px" align="center">一周只能許一次喔</p> -->
	<!-- </div> -->
	<!-- </div> -->


	<script type="text/javascript">
// 隨手機更改dialog寬
		var changedialogwidth = 700;
		var changedialogheight = 450;
		var changedialogheight2 = 500;
	$(document).ready(function() {

		if($(window).width()<700){
			changedialogwidth =400;
			changedialogheight =400;
			changedialogheight2 =400;
			$('#imgid').css("border","0px #5C8DEC solid");
// 			$('#dialogwish').css("border","0px #5C8DEC solid");
			$('#imgid').css("padding-left","0px");
			$('#imgid2').css("padding-left","0px");
            $('#bm').css("width","100%");
            $('#bm').css("left","0");
		};
	})
	
	
	
		$(function() {
			var image;
			var giftName;
			var points;
			var quantity1;
			// 	var userPoint = $.('#userPoint');
			// 	var exchangedPoint = $.('#exchangedPoint');

			$('button#placeOrder').on('click',function() {
						// 	   image =  $(this).parents().parents().find("#image").attr("src");
// 						image = $(this).parent().parent().parent().siblings()
// 								.find("#image").attr("src");
// 						points = $(this).parent().siblings().find("#points").text();
// 						itemName = $(this).parent().parent().find("#giftName").text();
						image = $(this).parent().parent().parent().parent().siblings()
								.find("#image").attr("src");
						points = $(this).parent().siblings().find("#points").text();
						itemName = $(this).parent().parent().parent().find("#giftName").text();

						console.log(image);
						console.log(itemName);
						console.log(points);
						count = $("#quantity1").val();
						$("#orderImage").attr("src", image);
						$("#orderName").text(itemName);
						$("#orderPoint").text(points);
						$("#totalPoints").text(points*count);
						$("#dialog").dialog("open");
					});

			$("#dialog").hide();
			$("#dialog").dialog({
				autoOpen : false,
				height : "auto",
				height : changedialogheight2,
				width : changedialogwidth,
				show : {
					effect : "blind",
					duration : 800
				},
				hide : {
					effect : "blind",
					duration : 800
				}
			});

			$("#saveButton").on("click", function() {
				// 		var $btn = $(this);
				// 		$btn.button("loading");
				var datas = {};
				quantity1 = $("#quantity1").val();
				console.log(quantity1);
				datas.giftName = itemName;
				datas.quantity1 = quantity1;
				datas.recipient = $("#recipient").val();
				datas.recipientAddress = $("#recipientAddress").val();
				datas.recipientPhone = $("#recipientPhone").val();
				datas.recipientTime = $("#recipientTime").val();

				if(datas.recipient&&datas.recipientAddress&&datas.recipientPhone!=null){
				$.ajax({
					url : "<c:url value='/gift/giftOrder/addOrder'/>",
					method : "POST",
					data : datas,
					dataType : "json",
					success : function(data) {
						if (data.訂購成功) {
							
							swal("購買成功" ,"" ,"success")
							.then((value) => {
// 								$("#userPoint").load(location.href + " #userPoint");
// 								$("#audittingPoint").load(location.href + " #audittingPoint");
// 								$("#exchangedPoint").load(location.href + " #exchangedPoint");
// 								$("#leftLayout").load(location.href + " #leftLayout");
								location.replace("/gift/list");
							});
							
						} else if (data.剩餘點數不足) {
							swal("剩餘點數不足", "", "warning");
						} else if (data.數字輸入錯誤) {
							swal("數字輸入錯誤", "", "warning");
						} else if (data.數量輸入過大) {
							swal("數量輸入過大", "", "warning");
						} else {
							swal("請輸入正確資料", "", "warning");
						}
					},
					error : function() {
						alert("請檢查網路連線", "", "warning");
					}
				})
				
			}else{
				swal("請確認資料填寫無誤" , "", "warning");
			}
				// 		$btn.button("reset");
			});
			
			//   以下許願池
			$('#dialog-wish').hide();
			$('#dialog-success').hide();
			var dialog1 = $('#dialog-wish').dialog({
				autoOpen : false,
				resizable : true,
				height : changedialogheight,
				width : changedialogwidth,
				modal : true,
				show : {
					effect : "blind",
					duration : 500
				},
				hide : {
					effect : "blind",
					duration : 500
				},
			})
			// 			var dialog2 = $('#dialog-success').dialog({
			// 				autoOpen: false,
			// 				resizable : true,
			// 				height : 300,
			// 				width : 400,
			// 				modal : true,
			// 				show : {
			// 					effect : "blind",
			// 					duration : 500
			// 				},
			// 				hide : {
			// 					effect : "blind",
			// 					duration : 500
			// 				},
			// 			})
			// 			var dialog3 = $('#dialog-failed').dialog({
			// 				autoOpen: false,
			// 				resizable : true,
			// 				height : 300,
			// 				width : 400,
			// 				modal : true,
			// 				show : {
			// 					effect : "blind",
			// 					duration : 500
			// 				},
			// 				hide : {
			// 					effect : "blind",
			// 					duration : 500
			// 				},
			// 			})
			$('#bm').on("click", function() {
				dialog1.dialog("open");
				//<!-- Save -->
				$("#wishButton").bind("click", function() {
					if($("#image1").val()==0){
						swal('要記得放圖片喔~');
					};
	
					var $btn = $(this);
					$btn.button("loading");

					var formData = new FormData();
					formData.append('name', $("#name").val());
					formData.append('brand', $("#brand").val());
					$.each($("input[type='file']")[0].files, function(i, file) {
						formData.append('file', file);
					});

					$.ajax({
						url : "<c:url value='/wish'/>",
						method : "POST",
						data : formData,
						enctype : "multipart/form-data",
						processData : false,
						contentType : false,
						success : function(data) {
							if (data.data == null) {
								swal({
									icon : "/resources/pic/積點專區/只能許一次.png",
									title : '一周只能許一次噢...'
								});
								$btn.button("reset");
								dialog1.dialog("close");
							} else if (data.messages.length == 0) {
								$("#dataForm").trigger("reset");
								swal({
									icon : "/resources/pic/積點專區/許願成功.png",
									title : '許願成功!!!'
								});
								$btn.button("reset");
								dialog1.dialog("close");
							}
						}
					})
					$btn.button("reset");
				});
			});

		});

		var animation = bodymovin.loadAnimation({
			container : document.getElementById('bm'),
			renderer : 'svg',
			loop : true,
			autoplay : true,
			path : '/resources/pic/積點專區/動畫/data.json'
		})
		
		$("#quantity1").on("change",function(){
			quantity = $("#quantity1").val();
			points = $("#orderPoint").text();
			$("#totalPoints").text(quantity*points);
		})
	</script>
</body>
</html>

