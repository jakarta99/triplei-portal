<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/jquery/jquery-ui.1.11.2.css">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i商品訂單頁面</title>
<script type="text/javascript"
	src='<c:url value="/resources/jquery/localization/jquery.ui.datepicker-zh-TW1.js" />'></script>
	<script type='text/javascript' src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<style>
select {
	border: 1px lightgrey solid;
	background:white;
	text-shadow:0;
	height:2.5em;
}
</style>
</head>
<body>

	<div id="wrap">
		<div class="container-fluid"
			style="width: 100%; height: 100%; position: absolute; padding: 0">
			<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
			<div style="padding: 0; width: 100%; height: 100%;padding-top:8vh">
				<div class="col-sm-12" style="margin-top: 4%;">
					<span style="font-size: 150%;width:85%;margin-left:6%;color:#5C8DEC;">基本資料及聯絡資訊</span>

					<form style="margin-top:2vh">
						<div class="col-sm-6" style="border-right: 1px lightgrey solid">
							<div style="width: 85%;margin-left:8%">
								<div class="col-sm-12">
									<span>姓名</span> <br /> <input id="name" class="col-sm-8" type="text">
								<c:if test="${gender!='Female'}">
								<span style="margin-left:2%">性別：男</span>
								</c:if>
								<c:if test="${gender!='Male'}">
								<span style="margin-left:2%">性別：女</span>
								</c:if>
								
								</div>
								<div class="col-sm-12" style="margin-top:2%">
									<span>手機號碼</span><br/>
									<input id="tel" name="tel" type="text">
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段1</span> <input class="col-sm-12 date"
										id="date1" type="text" placeholder="日期"> <br /> <br />
									<select id="session1" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="上午">上午</option>
										<option value="下午">下午</option>
										<option value="晚上">晚上</option>
									</select> <select id="shift1" class="col-sm-9">
									</select> <br />
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段2</span> <input class="col-sm-12 date"
										id="date2" type="text" placeholder="日期"> <br /> <br /> <select
										id="session2" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="上午">上午</option>
										<option value="下午">下午</option>
										<option value="晚上">晚上</option>
									</select> <select id="shift2" class="col-sm-9">
									</select> <br />
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段3</span> <input class="col-sm-12 date"
										id="date3" type="text" placeholder="日期"> <br /> <br /> <select
										id="session3" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="上午">上午</option>
										<option value="下午">下午</option>
										<option value="晚上">晚上</option>
									</select> <select id="shift3" class="col-sm-9">
									</select> <br /> <br /> <br />
								</div>
							</div>
						</div>
						<div class="col-sm-6" style="padding-right:6%">
							<form id="dataForm">
								<input type="hidden" id="id" name="id" value="" />
								<div class="col-sm-12">
									<div class="col-sm-12" style="padding: 0">
										<div class="form-group required col-sm-6" style="margin: 0; padding: 0">
											<label for="city" class="col-sm-4 control-label" style="font-weight: normal;">縣市</label>
											<div class="col-sm-8">
												<select id="city" class="form-control">
													<option value="" selected>請選擇</option>
													<option value="台北市">台北市</option>
													<option value="新北市">新北市</option>
												</select>
											</div>
										</div>

										<div class="form-group required col-sm-6">
											<label for="region" class="col-sm-4 control-label;" style="font-weight: normal;">區域</label>
											<div class="col-sm-8">
												<select id="region" class="form-control">
													<option value="" selected>請選擇</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group required">
										<label for="street" class="col-sm-2 control-label" style="margin-bottom:2%;font-weight: normal;">街道</label>
										<div class="col-sm-10" style="margin-bottom:2%">
											<select id="street" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="address" class="col-sm-2 control-label" style="font-weight: normal;">超商地址</label>
										<div class="col-sm-6">
											<select id="address" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
										<div class="col-sm-4" style="vertical-align:middle;padding-top:1%">
										<span >
										超商店名:  <span id="storeName" ></span>
										</span>
										</div>
									</div>


								</div>
								<div class="col-sm-12" style="min-width:400px;padding-left:5%;margin-top:2%;width:45vw;height:50vh;">
								<iframe id="googleMapping" width="90%" height="90%" style="border:0"
  src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAuwYed8DxRGhmTy44R5HGKich9J-dGs9s
    &q=台北101">
</iframe>
</div>
							</form>
						</div>
					</form>
				</div>
				<div class="col-sm-12" style="width:88%;margin-left:6%;">
					<span style="font-size: 150%;color:#5C8DEC;">確認保單資料</span>
					<span style="color: grey; margin-left: 1%;">與業務員見面後也可更改</span>
					<div class="col-sm-12" style="border: 1px solid lightgrey;padding-top:2%;padding-bottom:2%;margin-top:1%">
						<div class="col-sm-12" style="margin-bottom: 2%; margin-top: 1%;">
							<img src="${model.insurer.imgsrc}" width="35vw"> 
							<span style="font-size: 115%">${model.insurer.name}</span> <br />
							<span style="font-size: 115%">${model.code}-${model.localName}</span>
						</div>

						<div class="col-sm-12" style="margin-bottom: 4%;">
							<div class="col-sm-3" align="center">
								<span>幣別</span> <br /> <span>${model.curr}</span>
							</div>
							<div class="col-sm-3" align="center">
								<span>保額</span> <br /> <span>$<fmt:formatNumber value="${model.insureAmount}" maxFractionDigits="0" />萬</span>
							</div>
							<div class="col-sm-3" align="center">
								<span>每期保費</span> <br /> <span>$<fmt:formatNumber value="${model.premiumAfterDiscount}" maxFractionDigits="0" /></span>
							</div>
							<div class="col-sm-3" align="center">
								<span>可獲得點數</span> <br /> <span><fmt:formatNumber value="${model.getPoint}" maxFractionDigits="0" /></span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12 float-right" align="right" style="margin-top:2%;margin-bottom:5%;">
				<input type="button" id="confirmPurchase" class="btn btn-secondary" style="width:30%;background-color:#5C8DEC;color:white;border:none;margin-right:6%" value="確認約訪購買">
				</div>
				<div class="col-sm-12" style="height:5%;">
				</div>
			</div>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>

	<script>
	$('#tel').inputmask({
		  mask: '9999-999-999'
	});
	
		$(".date").on("change", function() {
			$(this).attr("placeholder", "");
		})
		$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$(".date").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : 'c:2+c',
			dateFormat : "yy-mm-dd"
		});
		$(".date").datepicker('setDate', new Date());

		$("#session1").on("change", function() {
			var sessionVal = $("#session1 option:selected").val();
			var shift = $("#shift1");
			if (sessionVal == "上午") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "下午") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value=‘4'>16:00~17:00</option>");
				shift.append("<option value=‘5'>17:00~18:00</option>");
			} else if (sessionVal == "晚上") {
				shift.empty();
				shift.append("<option value=‘6'>18:00~19:00</option>");
				shift.append("<option value=‘7'>19:00~20:00</option>");
				shift.append("<option value=‘8'>20:00~21:00</option>");
				shift.append("<option value=‘9'>21:00~22:00</option>");
			}
		})
		$("#session2").on("change", function() {
			var sessionVal = $("#session2 option:selected").val();
			var shift = $("#shift2");
			if (sessionVal == "上午") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "下午") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value='4'>16:00~17:00</option>");
				shift.append("<option value='5'>17:00~18:00</option>");
			} else if (sessionVal == "晚上") {
				shift.empty();
				shift.append("<option value='6'>18:00~19:00</option>");
				shift.append("<option value='7'>19:00~20:00</option>");
				shift.append("<option value='8'>20:00~21:00</option>");
				shift.append("<option value='9'>21:00~22:00</option>");
			}
		})
		$("#session3").on("change", function() {
			var sessionVal = $("#session3 option:selected").val();
			var shift = $("#shift3");
			if (sessionVal == "上午") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "下午") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value='4'>16:00~17:00</option>");
				shift.append("<option value='5'>17:00~18:00</option>");
			} else if (sessionVal == "晚上") {
				shift.empty();
				shift.append("<option value='6'>18:00~19:00</option>");
				shift.append("<option value='7'>19:00~20:00</option>");
				shift.append("<option value='8'>20:00~21:00</option>");
				shift.append("<option value='9'>21:00~22:00</option>");
			}
		})
		$('#city')
				.on(
						"change",
						function() {
							var datas = {};
							var city = $('#city').val();
							datas.city = city;

							$.ajax({
										type : "GET",
										url : "<c:url value='/convenienceStore/searchRegion'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#region").empty();
											$("#region").append($("<option selected></option>").attr("value","").text("請選擇"));
											$("#street").empty();
											$("#street").append($("<option selected></option>").attr("value","").text("請選擇"));
											$("#address").empty();
											$("#address").append($("<option selected></option>").attr("value","").text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#region").append($("<option></option>").attr("value",data[i]).text(data[i]));
											}
										},
										error : function(data) {
											swal("error");
										}

									});
						});

		$('#region').on("change",function() {
							var datas = {};
							var city = $('#city').val();
							var region = $('#region').val();
							datas.city = city;
							datas.region = region;

							$.ajax({
										type : "GET",
										url : "<c:url value='/convenienceStore/searchStreet'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#street").empty();
											$("#street").append($("<option selected></option>").attr("value","").text("請選擇"));
											$("#address").empty();
											$("#address").append($("<option selected></option>").attr("value","").text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#street").append($("<option></option>").attr("value",data[i]).text(data[i]));
											}
										},
										error : function(data) {
											swal("error");
										}

									});
						});

		$('#street')
				.on("change",function() {
							var datas = {};
							var city = $('#city').val();
							var region = $('#region').val();
							var street = $('#street').val();
							datas.city = city;
							datas.region = region;
							datas.street = street;

							$.ajax({
										type : "GET",
										url : "<c:url value='/convenienceStore/searchAddress'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#address").empty();
											$("#address").append($("<option selected></option>").attr("value","").text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#address").append($("<option></option>").attr("value",data[i].address).text(data[i].address));
											}
										},
										error : function(data) {
											swal("error");
										}

									});
						});

		$('#address').on("change",function() {
							var address = $('#city').val() + $('#region').val()+ $('#address').val();
							var fixedAdd = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBzwMOjkFO0a9VjCZvfSatBpDesK2qFYuU&q="
    						var locate = fixedAdd+address;
							$("#googleMapping").attr("src",locate);
							var datas = {};
							datas.address = $('#address').val();
							$.ajax({
								type : "GET",
								url : "<c:url value='/convenienceStore/searchStoreName'/>",
								dataType : "json",
								data : datas,
								success : function(data) {
// 									for (var i = 0; i < data.length; i++) {
										$("#storeName").text(data.storeName);
// 									}
								},
								error : function(data) {
									swal("error");
								}

							});
		
							});


		
		$("#confirmPurchase").on("click",function(){
			var name = $("#name").val();
			var age = "<c:out value="${age}"/>";
			var gender = "<c:out value="${gender}"/>";
			var tel = $("#tel").val();

			var date11 = $("#date1").val();
			var date12 = $("#session1 option:selected").val();
			var date13 = $("#shift1 option:selected").text();

			var date21 = $("#date2").val();
			var date22 = $("#session2 option:selected").val();
			var date23 = $("#shift2 option:selected").text();
			
			var date31 = $("#date3").val();
			var date32 = $("#session3 option:selected").val();
			var date33 = $("#shift3 option:selected").text();
			
			var pid = <c:out value="${model.id}"/>;
			var insureAmounts = <c:out value="${model.insureAmount}"/>;
			var discountedPremium = <c:out value="${model.premiumAfterDiscount}"/>;
			var scorePoints = <c:out value="${model.getPoint}"/>;
			var userName = "<c:out value="${model.createdBy}"/>";
			
			var city = $("#city option:selected").val();
			var region = $("#region option:selected").val();
			var road = $("#street option:selected").val();
			var add =$("#address option:selected").text();
			var address = city+region+road+add;
			
			var formData = new FormData();
			formData.append("name",name);
			formData.append("age",age);
			formData.append("gender",gender);
			formData.append("tel",tel);
			formData.append("date_1_1",date11);
			formData.append("date_1_2",date12);
			formData.append("date_1_3",date13);
			formData.append("date_2_1",date21);
			formData.append("date_2_2",date22);
			formData.append("date_2_3",date23);
			formData.append("date_3_1",date31);
			formData.append("date_3_2",date32);
			formData.append("date_3_3",date33);
			formData.append("pid",pid);
			formData.append("insureAmount",insureAmounts);
			formData.append("premiumAfterDiscount",discountedPremium);
			formData.append("getPoint",scorePoints);
			formData.append("address",add);
			
			if(name=="" && tel=="" && date11=="" && date12=="" && date13==""){
				alert("請輸入姓名，電話和最少一組聯絡時間，以便我們儘速跟您聯繫");
			}else if(name!="" && tel!="" && date11!="" && date12!="" && date13!=null && name!=null && tel!=null && date11!=null && date12!=null && date13!=null){
				$.ajax({
					url:"<c:url value='/recipient'/>",
					method:"POST",
					data:formData,
					processData:false,
					contentType:false,
					success:function(){	
					}
				})
				swal('已下單成功');
				setTimeout("location.href = '/recipient/list'",1500);
			}else{
				swal("請輸入姓名，電話和最少一組聯絡時間，以便我們儘速跟您聯繫");
			}
			
		})
		
		window.onresize = function(event) {
			
			
		}
	</script>
</body>
</html>

