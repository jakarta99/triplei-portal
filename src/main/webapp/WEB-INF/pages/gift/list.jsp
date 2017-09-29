<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<title>Triple i</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
.ui-widget-header {
	 border: 0px solid #e78f08; 
	background-color: white;
}
</style>
</head>

<body>

	<div id="wrap" >
	<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;color:white;">
<!-- 左半藍 -->
			<div class="col-sm-3" style="background-color:#5C8DEC;height:100vh;">
			
				<div class="col-sm-1"></div>
				<div class="col-sm-10" style="height: 100vh;padding-left: 0px;padding-right: 0px;">
				<div class="col-sm-12" style="padding-top: 15vh;padding-bottom: 0vh">
				<span style="font-size: 2.5em;font-family: 微軟正黑體;">T-Point</span>
				<span style="font-size: 1.5em;font-family: 微軟正黑體;">積點專區</span>
				</div>
				<div class="col-sm-12">
				<img alt="" src="/resources/pic/積點專區/點數(大).png" width="50" height="50">
				<span id="userPoint" style="font-size: 3.5em;vertical-align: middle;">${userPoint}</span>
				</div>
				<div class="col-sm-12">
				<div class="col-sm-12" style="height: 15vh;border: 1px white solid;border-radius: 20px;margin-top: 3vh;padding-left: 0px;padding-right: 0px;">
				<div class="col-sm-6" style="border-right: 1px white solid;height: 80%;margin-top:5%;display: flex; justify-content: center; flex-direction: column" align="center">
				<p style="margin-top: 10%;margin-bottom: 10%;">審核中點數</p>
				<span id="audittingPoint" >${audittingPoint}</span>
				</div>
				<div class="col-sm-6" style="height: 80%;margin-top:5%;display: flex; justify-content: center; flex-direction: column" align="center">
				<p style="margin-top: 10%;margin-bottom: 10%">已兌換點數</p>
				<span id="exchangedPoint" >${exchangedPoint}</span>
				</div>
				</div></div>
				<div class="col-sm-12"><img id="wishpool" alt="" src="/resources/pic/積點專區/許願池.png" width="200" height="200"></div>
				</div>
				<div class="col-sm-1"></div>
<!-- 			<div style="margin:7% auto 0 auto; display:table;width:70%;"> -->
<!-- 				<br/><br/> -->
<!-- 				<h1>商品專區</h1> -->
<!-- 				<br/>				 -->
<!-- 				<div id="container-fluid" style="height:100%;position:relative;"> -->
<!-- 				<div > -->
<!-- 				<img src="/resources/pic/product/商品專區動畫/images/img_1.png" width="100%;" style="width:90%;position:absolute;margin-top:4%;"> -->
<!-- 				</div> -->
<!-- 				<div > -->
<!-- 				<img src="/resources/pic/product/商品專區動畫/images/img_0.png" width="100%" style="width:30%;position:absolute;margin:23% auto auto 50%;"> -->
<!-- 				</div> -->
<!-- 				</div> -->
<!-- 				</div> -->
			</div>
<!-- 右半白 -->
			<div class="col-sm-9" style="height:100%;padding:3%;color:black;overflow-y:scroll">
		<div
			style="height: 100vh; display: table; table-layout: fixed; width: 1400px;">

			<div style="display: table-cell; height: 500px; width: 1300px;">
				<table style="border: 2px solid; margin-left: 20px;">
					<h3 style="margin-left: 20px;">熱門兌換:</h3>
					<tr>
						<c:forEach items="${modelh}" var="modelh">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<img style="height: 200px; width: 200px;" src='<c:url value="${modelh.image1}"/>'>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelh.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelh.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
									<p style="text-align: center;">商品兌換點數:${modelh.bonus}</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a href="${pageContext.request.contextPath}/gift/true">查看更多...</a></span>
				<table style="border: 2px solid; margin-left: 20px;">
					<h3 style="margin-left: 20px;">家電兌換:</h3>
					<tr>
						<c:forEach items="${modele}" var="modele">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:${modele.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modele.name}</p>
										<p style="text-align: center;">商品兌換點數:${modele.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modele.bonus}</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/ELETRONICS">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">禮卷兌換:</h3>
					<tr>
						<c:forEach items="${modelv}" var="modelv">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:<br/><img id="image" style="height: 200px; width: 200px;" src='<c:url value="${modelv.image1}"/>'></p>
									</div>
									<div>
									<div  class = "col-md-8" style="height: 20px;">
										<span>積點商品名稱:    </span><span id="giftName" style="text-align: center;">${modelv.name}</span></br>
										<span>商品兌換點數:    </span><span id="points" style="text-align: center;">${modelv.bonus}</span>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p id = "placeOrder" class = "btn btn-sm btn-primary" style="text-align: center;">立即兌換</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/VOUCHERS">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">家居、廚具兌換:</h3>
					<tr>
						<c:forEach items="${modelf}" var="modelf">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:${modelf.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelf.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelf.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modelf.bonus}</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/FURNITURES">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">戶外運動商品兌換:</h3>
					<tr>
						<c:forEach items="${modelod}" var="modelod">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:${modelod.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelod.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelod.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modelod.bonus}</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/OUTDOOR">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">女仕用品兌換:</h3>
					<tr>
						<c:forEach items="${modelw}" var="modelw">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:${modelw.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelw.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelw.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modelw.bonus}</p>
									</div>
									</div> 
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/WOMAN">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">男仕用品兌換:</h3>
					<tr>
						<c:forEach items="${modelm}" var="modelm">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">				
										<p style="text-align: center;">圖片:${modelm.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelm.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelm.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modelm.bonus}</p>
									</div>
									</div>
								</div></td>
						</c:forEach>
					</tr>
				</table>
				<span style="margin-left: 1130px"><a
					href="${pageContext.request.contextPath}/gift/MAN">查看更多...</a></span>
				<table
					style="border: 2px solid; margin-left: 20px; cellpadding: 10px">
					<h3 style="margin-left: 20px;">其他商品兌換:</h3>
					<tr>
						<c:forEach items="${modelot}" var="modelot">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:${modelot.image1}</p>
									</div>
									<div>
									<div class = "col-md-8" style="height: 20px; ">
										<p style="text-align: center;">積點商品名稱:${modelot.name}</p>
										<p style="text-align: center;">商品兌換點數:${modelot.bonus}</p>
									</div>
									<div class = "col-md-4" style="height: 20px; ">
										<p style="text-align: center;">商品兌換點數:${modelot.bonus}</p>
									</div>
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
	</div>
	</div>
</div>

<div id="dialog" title="商品明細">
      <div>
        <div>
            <img id="orderImage" style="height: 200px; width: 200px;" >
        </div>
        <div>
            <span>積點商品名稱:    </span>
            <span id="orderName" style="text-align: center;"></span>
        </div>
        <div>
        	<img alt="" src="/resources/pic/積點專區/點數(小).png" width="30" height="30">
            <span>商品兌換點數:    </span>
            <span id="orderPoint" style="text-align: center;"></span>
        </div>
        <div>
        <label for="quantity1" class="">請輸入數量:</label>	
			<input type="number" class="" id="quantity1"
				name="quantity1" min="1" value="1" placeholder="quantity"/> 
        </div>
        <div>
        <label for="recipient" class="">收件人姓名:</label>	
			<input type="text" class="" id="recipient"
				name="recipient" placeholder=""/> 
        </div>
        <div>
        <label for="recipientAddress" class="">收件人地址:</label>	
			<input type="text" class="" id="recipientAddress"
				name="recipientAddress" placeholder=""/> 
        </div>
        <div>
        <label for="recipientPhone" class="">收件人電話:</label>	
			<input type="text" class="" id="recipientPhone"
				name="recipientPhone" placeholder=""/> 
        </div>
        <div>
        <label for="recipientTime" class="">希望收件時間:</label>	
				<select class="" id="recipientTime"
				name="recipientTime">
				<option value="上午" selected>上午</option>
				<option value="下午">下午</option>
				</select>
        </div>
        <div>
            <a href="#" class="btn btn-lg btn-primary btn-block"
               data-loading-text="Loading" id="saveButton">確認購買</a>
        </div>
    </div>
</div>
<!-- 許願池dialog -->
<div id="dialog-wish">
<div class="col-sm-1"></div>
<div class="col-sm-10">
<div class="col-sm-12" style="padding-left: 0px;padding-right: 0px">
<div class="col-sm-8" style="padding-left: 0px">
<h3>許願池</h3>
<p> 積點專區的商品無法滿足你的慾望嗎?別擔心,你可以在這裡告訴我們您想要的商品,TRIPLE-I會盡全力滿足您的願望!快許下願望吧</p>
</div>
<div class="col-sm-4" style="padding-right: 0px"><img id="wishpool" alt="" src="/resources/pic/積點專區/許願池.png" width="150" height="150"></div>
</div>
<div class="col-sm-12" style="padding-left: 0px;padding-bottom: 1vh">希望增加的兌換商品：</div>
<div class="col-sm-3" style="border: 1px #5C8DEC solid;height: 20vh;display: flex; justify-content: center; flex-direction: column" align="center">
<!-- <button style="background-color: white;color: #5C8DEC; border: 1px #5C8DEC solid;">上傳圖片</button> -->
<label for="image1" style="background-color: white;color: #5C8DEC;font-family: 微軟正黑體;">圖片(必要):</label> <input type="file" id="image1" name="image1" style="border: 0px #5C8DEC solid">

</div>
<form enctype="multipart/form-data" method="post" id="dataForm">
<div class="col-sm-9" >
<div class="col-sm-12" style="height: 9vh;border: 1px #5C8DEC solid;margin-bottom: 2vh;display: flex; justify-content: center; flex-direction: column">
<div style="">
<label for="brand" style="color: #5C8DEC;font-family: 微軟正黑體;">品牌:</label> <input type="text" placeholder="請輸入品牌名稱" id="brand" name="brand" style="border: 0px #5C8DEC solid">
</div></div>
<div class="col-sm-12" style="height: 9vh;border: 1px #5C8DEC solid;margin-bottom: 2vh;display: flex; justify-content: center; flex-direction: column">
<div style="">
<label for="name" style="color: #5C8DEC;font-family: 微軟正黑體;">商品名稱:</label> <input type="text" placeholder="請輸入商品名稱" id="name" name="name" style="border: 0px #5C8DEC solid">
</div></div>
</div>
</form>
<div class="col-sm-12" style="height:10vh" align="right">
<a class="btn btn-primary" data-loading-text="Loading" id="wishButton" style="margin-top: 4vh;background-color: white;border: 1px #5C8DEC solid;color: #5C8DEC;border-radius: 20px;font-size: 15px;">許願</a>
</div>
</div>
<div class="col-sm-1"></div>
</div>

<div id="dialog-success">
<img alt="" src="/resources/pic/積點專區/許願成功.png" class="col-sm-10" style="width: 500"><br/>
<div class="col-sm-10">
<p style="background-color: white;color: black;font-family: 微軟正黑體;font-size: 30px" align="center">許願成功</p>
</div>
</div>

<div id="dialog-failed">
<img alt="" src="/resources/pic/積點專區/只能許一次.png" class="col-sm-10" style="width: 500"><br/>
<div class="col-sm-10">
<p style="background-color: white;color: black;font-family: 微軟正黑體;font-size: 30px" align="center">一周只能許一次喔</p>
</div>
</div>


<script type="text/javascript">
$( function() {
	var image;
	var giftName;
	var points;
	var quantity1;
// 	var userPoint = $.('#userPoint');
// 	var exchangedPoint = $.('#exchangedPoint');
	
  $('p#placeOrder').on( 'click', function(){
// 	   image =  $(this).parents().parents().find("#image").attr("src");
	   image =  $(this).parent().parent().siblings().find("#image").attr("src");
	   giftName =  $(this).parent().siblings().find("#giftName").text();
	   points =  $(this).parent().siblings().find("#points").text();
	   console.log(image);
	   console.log(giftName);
	   console.log(points);
	   $("#orderImage").attr("src" , image);
	   $("#orderName").text(giftName);
	   $("#orderPoint").text(points);
	  $( "#dialog" ).dialog( "open" );  
  });
  
  $("#dialog").hide();
  $( "#dialog" ).dialog({
      autoOpen: false,
      height : "auto",
	  width : 700,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "blind",
        duration: 1000
      }
    });
  
  $("#saveButton").on("click", function() {
// 		var $btn = $(this);
// 		$btn.button("loading");
		var datas = {};
		quantity1 = $("#quantity1").val();
		console.log(quantity1);
		datas.giftName = giftName;
		datas.quantity1 = quantity1;
		datas.recipient = $("#recipient").val();
		datas.recipientAddress = $("#recipientAddress").val();
		datas.recipientPhone = $("#recipientPhone").val();
		datas.recipientTime = $("#recipientTime").val();
		
		$.ajax({
			url : "<c:url value='/gift/giftOrder/addOrder'/>",
			method : "POST",
			data : datas,
			dataType : "json",
			success : function(data) {
				if(data.訂購成功){
				alert("購買成功");
				location.replace("/gift/list");
// 				userPoint.text(data.userPoint);
// 				exchangedPoint.text(data.exchangedPoint);
				}else if(data.剩餘點數不足){
					alert("剩餘點數不足");
				}else if(data.數字輸入錯誤){
					alert("數字輸入錯誤");
				}else{
					alert("請輸入正確資料");
				}
			},
			error : function(){
				alert("NetWorkError");
			}
		})
// 		$btn.button("reset");
	});
//   以下許願池
		$('#dialog-wish').hide();
		$('#dialog-success').hide();
			var dialog1 = $('#dialog-wish').dialog({
				autoOpen: false,
				resizable : true,
				height : "auto",
				width : 700,
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
			var dialog2 = $('#dialog-success').dialog({
				autoOpen: false,
				resizable : true,
				height : 300,
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
			var dialog3 = $('#dialog-failed').dialog({
				autoOpen: false,
				resizable : true,
				height : 300,
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
		$('#wishpool').on("click",function() {
			dialog1.dialog("open");
			//<!-- Save -->
			$("#wishButton").bind("click",function() {
								var $btn = $(this);
								$btn.button("loading");
								
								var formData = new FormData();
								formData.append('name', $("#name").val());
								formData.append('brand', $("#brand").val());
								$.each($("input[type='file']")[0].files, function(i, file) {
									formData.append('file', file);
								});
								
								$.ajax({
									url : "<c:url value='/admin/wish'/>",
									method : "POST",
									data : formData,
									enctype : "multipart/form-data",
									processData : false,
									contentType : false,
									success : function(data) {
										if (data.data == null) {
											dialog3.dialog("open");
											$btn.button("reset");
											dialog1.dialog("close");
										}else if (data.messages.length == 0) {
															$("#dataForm").trigger("reset");
															dialog2.dialog("open");
															$btn.button("reset");
															dialog1.dialog("close");
														}
									}
								})
								$btn.button("reset");
							});
			});
	
});
</script>
</body>
</html>






