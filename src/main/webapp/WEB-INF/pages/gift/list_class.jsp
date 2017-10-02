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
		<div div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>		
<!-- 			<div style="padding:0;width:100%;height:100%;color:white;"> -->
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
			
			</div>
			<div class="col-md-9" style="height:100%;padding:3%; margin-top:8vh;color:black;overflow-y:scroll" id="style-1">
					<div class="row" style="margin-top: 20px;">
						<c:forEach items="${models}" var="model" varStatus="status">
						<div class="col-md-3" style="width:300px ; border: #5C8DEC 1px solid; border-radius: 10px; margin-left: 20px">
							<div style="height: 300px; ">
								<div class="col-md-12" style="height: 220px;">
									<img id="image" style="margin-top: 5px ; height: 200px; width: 200px;" src='<c:url value="${model.image1}"/>'>
								</div>
								<div>
									<div  class="col-md-8" style="height: 20px;">
										<span id="giftName" style="text-align: center;">${model.name}</span></br>
									<img alt="" src="/resources/pic/積點專區/點數(小).png" width="15" height="15">
										<span id="points" style="text-align: center;">${model.bonus}</span>
									</div>
									<div class="col-md-4" style="height: 20px; ">
										<p id="placeOrder" class="btn btn-sm btn-primary" style="text-align: center;">立即兌換</p>
									</div>
								</div>
							</div>
						</div>
						<c:if test="${status.count%3==0}">
					</div><div class="row" style="margin-top: 20px;">
						</c:if>
						</c:forEach>
				</div>
			</div>
		</div>
	</div>
<!-- </div> -->
		
		
		
		
		
		
		
		
		
<div id="dialog" title="商品明細">
      <div>
	      <div class="container-fluid">
	        <div class="col-md-7">
		        <div>
		            <span>積點商品名稱:    </span>
		            <span id="orderName" style="text-align: center;"></span>
		        </div>
		        <div>
		        <label for="quantity1" class="">請輸入數量:</label>	
					<input type="number" class="" id="quantity1"
						name="quantity1" min="1" value="1" placeholder="quantity"/> 
		        </div>
		        <div>
		        	<img alt="" src="/resources/pic/積點專區/點數(小).png" width="30" height="30">
		            <span>商品兌換點數:    </span>
		            <span id="orderPoint" style="text-align: center;"></span>
		        </div>
	        </div>
	        <div class="col-md-4">
	            <img id="orderImage" style="height: 200px; width: 200px;" >
	        </div>
        </div>
        <hr>
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
        <div class="col-md-8"></div>
        <div class="col-md-3">
            <a href="#" class="btn btn-lg btn-primary btn-block"
               data-loading-text="Loading" id="saveButton">確認購買</a>
        </div>
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
											swal({
												  icon: "/resources/pic/積點專區/只能許一次.png",
												  title:'一周只能許一次噢...'
												});
											$btn.button("reset");
											dialog1.dialog("close");
										}else if (data.messages.length == 0) {
															$("#dataForm").trigger("reset");
															swal({
																  icon: "/resources/pic/積點專區/許願成功.png",
																  title:'許願成功!!!'
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
</script>	
</body>
</html>






