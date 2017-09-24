<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>

<body>

	<div class="container-fluid" style="padding: 0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<img alt="#" src="/resources/pic/積點專區/許願池.png"
							style="height: 200px; width: 200px" class="1">

		<div class="dialog-form" style="height: 500px;width: 450px" title="許願ㄅ">
			<h3 style="text-align: center"></h3>
				<div style="height: 200px; width: 450px;">
					<div style="height: 200px; width: 250px; float: left;">
						<h3>許願池</h3>
						<br> <span> 積點專區的商品無法滿足你的慾望嗎?<br>
							別擔心,你可以在這裡告訴我們您想要<br> 的商品,TRIPLE-I會盡全力滿足您的願<br>
							望!快許下願望吧
						</span>
					</div>
					<div style="height: 200px; width: 200px; float: right;">
						<img alt="#" src="/resources/pic/積點專區/許願池.png"
							style="height: 200px; width: 200px">
					</div>
				</div>
				<div class="col-md-10">
					<form method="post" id="dataForm">
						<div class="col-md-12">
							<label for="image1">圖片:</label> <input type="file" value="上傳圖片"
								id="image1" name="image1">
						</div>
						<div class="col-md-4">
							<label for="wishType">類別:</label> <select id="wishType"
								name="wishType">
								<option value="禮劵">禮劵</option>
								<option value="家居，廚具">家居，廚具</option>
								<option value="3C家電">3C家電</option>
								<option value="戶外運動">戶外運動</option>
								<option value="女仕用品">女仕用品</option>
								<option value="男仕用品">男仕用品</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="col-md-5">
							<label for="brand">品牌:</label> <input type="text"
								placeholder="品牌" id="brand" name="brand" class="col-md-11">
						</div>
						<div class="col-md-10">
							<label for="name">商品名稱:</label> <input type="text"
								placeholder="商品名稱" id="name" name="name" class="col-md-12">
						</div>
					</form>
				</div>

			<div class="row">
				<div class="col-md-3">
					<div>
						<a href="#" class="btn btn-primary"
							data-loading-text="Loading" id="saveButton">許願</a>
					</div>
				</div>
			</div>


		</div>
	</div>



</body>
</html>
<script type="text/javascript">
	$(function() {
		$('.dialog-form').hide();
		var dialog1 = $('.dialog-form').dialog({
			autoOpen:false,
			height : "auto",
			width : 400,
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
		$('.1').on("click",function() {
			dialog1.dialog("open");
							
							
							//<!-- Save -->
							$("#saveButton").bind("click",function() {
												var $btn = $(this);
												$btn.button("loading");

												$.post("<c:url value='/admin/wish'/>","dataForm",function(data) {
																	if (data.messages.length == 0) {
																		$("#dataForm").trigger("reset");
																		//swal("SUCCESS", "資料新增成功！", "success");
																		alert("SUCCESS");
																		$btn.button("reset");
																		dialog1.dialog("close");
																	}
																},
																function(data,textStatus,jqXHR) {
																	$btn.button("reset");
																});
												$btn.button("reset");
											});
						});
	});
</script>

