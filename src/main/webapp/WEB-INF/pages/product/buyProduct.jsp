<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<link rel="stylesheet" href="/resources/jquery/jquery-ui.1.11.2.css">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<script type="text/javascript"
	src='<c:url value="/resources/jquery/localization/jquery.ui.datepicker-zh-TW1.js" />'></script>
<style>
select {
	border: 1px lightgrey solid;
}
</style>
</head>
<body>

	<div id="wrap">
		<div class="container-fluid"
			style="width: 100%; height: 100%; position: absolute; padding: 0">
			<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
			<div style="padding: 0; width: 100%; height: 100%;">
				<div class="col-sm-12" style="margin-top: 4%;">
					<span style="font-size: 150%">基本資料及聯絡資訊</span>
					<form>
						<div class="col-sm-6" style="border-right: 1px lightgrey solid">
							<div style="width: 85%">
								<div class="col-sm-12">
									<span>姓名</span> <br /> <input class="col-sm-8" type="text">
									<label class="col-sm-2"> <input type="radio"
										class="btn btn-secondary gender" value="male"> <span>先生</span>
									</label> <label class="col-sm-2"> <input type="radio"
										class="btn btn-secondary gender" value="female"> <span>小姐</span>
									</label>
								</div>
								<div class="col-sm-12">
									<span>手機號碼</span> <br /> <br /> <input type="text"
										class="col-sm-2" placeholder="+886" style="margin-right: 1%">
									<input type="text" class="col-sm-9" placeholder="0988-888-888">
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段1</span> <input class="col-sm-12 date"
										id="date1" type="text" placeholder="日期"> <br /> <br />
									<select id="session1" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="morning">上午</option>
										<option value="afternoon">下午</option>
										<option value="evening">晚上</option>
									</select> <select id="shift1" class="col-sm-9">
									</select> <br />
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段2</span> <input class="col-sm-12 date"
										type="text" placeholder="日期"> <br /> <br /> <select
										id="session2" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="morning">上午</option>
										<option value="afternoon">下午</option>
										<option value="evening">晚上</option>
									</select> <select id="shift2" class="col-sm-9">
									</select> <br />
								</div>
								<div class="col-sm-12">
									<br /> <span>有空時段3</span> <input class="col-sm-12 date"
										type="text" placeholder="日期"> <br /> <br /> <select
										id="session3" class="col-sm-3">
										<option value="" selected="selected">請選擇</option>
										<option value="morning">上午</option>
										<option value="afternoon">下午</option>
										<option value="evening">晚上</option>
									</select> <select id="shift3" class="col-sm-9">
									</select> <br /> <br /> <br />
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<form id="dataForm">
								<input type="hidden" id="id" name="id" value="" />
								<div class="col-sm-12">
									<div class="col-sm-12" style="padding: 0">
										<div class="form-group required col-sm-6"
											style="margin: 0; padding: 0">
											<label for="city" class="col-sm-4 control-label">縣市</label>
											<div class="col-sm-8">
												<select id="city" class="form-control">
													<option value="" selected>請選擇</option>
													<option value="台北市">台北市</option>
													<option value="桃園">桃園</option>
													<option value="台中">台中</option>
													<option value="台南">台南</option>
													<option value="高雄">高雄</option>
												</select>
											</div>
										</div>

										<div class="form-group required col-sm-6">
											<label for="region" class="col-sm-4 control-label">區域</label>
											<div class="col-sm-8">
												<select id="region" class="form-control">
													<option value="" selected>請選擇</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group required">
										<label for="street" class="col-sm-2 control-label">街道</label>
										<div class="col-sm-10">
											<select id="street" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="address" class="col-sm-2 control-label">超商地址</label>
										<div class="col-sm-10">
											<select id="address" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>


								</div>
								<!-- class="col-md-8" -->
								<div id="map"></div>

<!-- 								<div class="col-sm-12" align="center" style="margin-top:2%"> -->
<!-- 									<img width="80%" id="mapimg" -->
<!-- 										src="https://maps.googleapis.com/maps/api/staticmap?center=%E5%8F%B0%E5%8C%97101&zoom=17&size=600x300&maptype=roadmap&markers=color:red%7C%E5%8F%B0%E5%8C%97101&key=AIzaSyAYD9w-VVl7VwL9mC1SPzvQmMw6Sxk3Ul0" /> -->
<!-- 								</div> -->
								<iframe id="googleMapping" width="600" height="450" style="border:0"
  src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAuwYed8DxRGhmTy44R5HGKich9J-dGs9s
    &q=台北101">
</iframe>
							</form>
						</div>
					</form>
				</div>
				<div class="col-sm-12">
					<span style="font-size: 150%">確認保單資料</span> <span
						style="color: grey; margin-left: 1%;">與業務員見面後也可更改</span>
					<div class="col-sm-12" style="border: 1px solid lightgrey">
						<div class="col-sm-12" style="margin-bottom: 2%; margin-top: 1%;">
							<img src=""> <span style="font-size: 115%">保險公司</span> <br />
							<span style="font-size: 115%">保險商品編號+名稱</span>
						</div>

						<div class="col-sm-12" style="margin-bottom: 4%;">
							<div class="col-sm-3" align="center">
								<span>幣別</span> <br /> <span>新台幣</span>
							</div>
							<div class="col-sm-3" align="center"
								style="border-left: 1px lightgrey solid">
								<span>保額</span> <br /> <span>XX,XXX</span>
							</div>
							<div class="col-sm-3" align="center"
								style="border-left: 1px lightgrey solid">
								<span>每期保費</span> <br /> <span>XX,XXX</span>
							</div>
							<div class="col-sm-3" align="center"
								style="border-left: 1px lightgrey solid">
								<span>可獲得點數</span> <br /> <span>X,XXX</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
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
		$("#date1").datepicker('setDate', new Date());

		$("#session1").on("change", function() {
			var sessionVal = $("#session1 option:selected").val();
			var shift = $("#shift1");
			if (sessionVal == "morning") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "afternoon") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value=‘4'>16:00~17:00</option>");
				shift.append("<option value=‘5'>17:00~18:00</option>");
			} else if (sessionVal == "evening") {
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
			if (sessionVal == "morning") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "afternoon") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value=‘4'>16:00~17:00</option>");
				shift.append("<option value=‘5'>17:00~18:00</option>");
			} else if (sessionVal == "evening") {
				shift.empty();
				shift.append("<option value=‘6'>18:00~19:00</option>");
				shift.append("<option value=‘7'>19:00~20:00</option>");
				shift.append("<option value=‘8'>20:00~21:00</option>");
				shift.append("<option value=‘9'>21:00~22:00</option>");
			}
		})
		$("#session3").on("change", function() {
			var sessionVal = $("#session3 option:selected").val();
			var shift = $("#shift3");
			if (sessionVal == "morning") {
				shift.empty();
				shift.append("<option value='8'>08:00~09:00</option>");
				shift.append("<option value='9'>09:00~10:00</option>");
				shift.append("<option value='10'>10:00~11:00</option>");
				shift.append("<option value='11'>11:00~12:00</option>");
			} else if (sessionVal == "afternoon") {
				shift.empty();
				shift.append("<option value='12'>12:00~13:00</option>");
				shift.append("<option value='1'>13:00~14:00</option>");
				shift.append("<option value='2'>14:00~15:00</option>");
				shift.append("<option value='3'>15:00~16:00</option>");
				shift.append("<option value=‘4'>16:00~17:00</option>");
				shift.append("<option value=‘5'>17:00~18:00</option>");
			} else if (sessionVal == "evening") {
				shift.empty();
				shift.append("<option value=‘6'>18:00~19:00</option>");
				shift.append("<option value=‘7'>19:00~20:00</option>");
				shift.append("<option value=‘8'>20:00~21:00</option>");
				shift.append("<option value=‘9'>21:00~22:00</option>");
			}
		})
		$('#city')
				.on(
						"change",
						function() {
							var datas = {};
							var city = $('#city').val();
							datas.city = city;

							$
									.ajax({
										type : "GET",
										url : "<c:url value='/admin/convenienceStore/searchRegion'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#region").empty();
											$("#region")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											$("#street").empty();
											$("#street")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											$("#address").empty();
											$("#address")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#region")
														.append(
																$(
																		"<option></option>")
																		.attr(
																				"value",
																				data[i])
																		.text(
																				data[i]));
											}
										},
										error : function(data) {
											alert("error");
										}

									});
						});

		$('#region')
				.on(
						"change",
						function() {
							var datas = {};
							var city = $('#city').val();
							var region = $('#region').val();
							datas.city = city;
							datas.region = region;

							$
									.ajax({
										type : "GET",
										url : "<c:url value='/admin/convenienceStore/searchStreet'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#street").empty();
											$("#street")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											$("#address").empty();
											$("#address")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#street")
														.append(
																$(
																		"<option></option>")
																		.attr(
																				"value",
																				data[i])
																		.text(
																				data[i]));
											}
										},
										error : function(data) {
											alert("error");
										}

									});
						});

		$('#street')
				.on(
						"change",
						function() {
							var datas = {};
							var city = $('#city').val();
							var region = $('#region').val();
							var street = $('#street').val();
							datas.city = city;
							datas.region = region;
							datas.street = street;

							$
									.ajax({
										type : "GET",
										url : "<c:url value='/admin/convenienceStore/searchAddress'/>",
										dataType : "json",
										data : datas,
										success : function(data) {
											$("#address").empty();
											$("#address")
													.append(
															$(
																	"<option selected></option>")
																	.attr(
																			"value",
																			"")
																	.text("請選擇"));
											for (var i = 0; i < data.length; i++) {
												$("#address")
														.append(
																$(
																		"<option></option>")
																		.attr(
																				"value",
																				data[i].address)
																		.text(
																				data[i].address));
											}
										},
										error : function(data) {
											alert("error");
										}

									});
						});

		$('#address')
				.on(
						"change",
						function() {

							var address = $('#city').val() + $('#region').val()
									+ $('#address').val();
// 							var mapimg = 'https://maps.googleapis.com/maps/api/staticmap?center='
// 									+ address
// 									+ '&zoom=17&size=600x400&maptype=roadmap&markers=color:red%7C'
// 									+ address
// 									+ '&key=AIzaSyAYD9w-VVl7VwL9mC1SPzvQmMw6Sxk3Ul0'
// 							$('#mapimg').attr("src", mapimg);
							var fixedAdd = "https://www.google.com/maps/embed/v1/place?key=AIzaSyAuwYed8DxRGhmTy44R5HGKich9J-dGs9s&q="
    						var locate = fixedAdd+address;
							$("#googleMapping").attr("src",locate);
						});

		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 8,
				center : {
					lat : -34.397,
					lng : 150.644
				}
			});
			var geocoder = new google.maps.Geocoder();

			document.getElementById('address').addEventListener('change',
					function() {
						geocodeAddress(geocoder, map);
					});
		}

		function geocodeAddress(geocoder, resultsMap) {
			var address = $('#city').val() + $('#region').val()
					+ $('#address').val();
			console.log(address);
			geocoder
					.geocode(
							{
								'address' : address
							},
							function(results, status) {
								if (status === 'OK') {
									resultsMap
											.setCenter(results[0].geometry.location);
									var marker = new google.maps.Marker({
										map : resultsMap,
										position : results[0].geometry.location
									});
								} else {
									alert('Geocode was not successful for the following reason: '
											+ status);
								}
							});
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYD9w-VVl7VwL9mC1SPzvQmMw6Sxk3Ul0&callback=initMap">
		
	</script>
</body>
</html>

