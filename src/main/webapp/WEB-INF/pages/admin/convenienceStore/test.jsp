<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

<style>
<
style>
	/* Always set the map height explicitly to define the size of the div
        * element that contains the map. */ #map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>

</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br /> <br /> <br />
			<h3>超商查詢</h3>


			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>超商查詢</strong>
								</h4>
							</div>
							<div class="panel-body">

								<!-- style="border: solid 3px; color: gray;" -->
								<div class="col-md-4">
									<div class="form-group required">
										<label for="city" class="col-md-4 control-label">縣市</label>
										<div class="col-md-8">
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

									<div class="form-group required">
										<label for="region" class="col-md-4 control-label">區域</label>
										<div class="col-md-8">
											<select id="region" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="street" class="col-md-4 control-label">街道</label>
										<div class="col-md-8">
											<select id="street" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>

									<div class="form-group required">
										<label for="address" class="col-md-4 control-label">超商地址</label>
										<div class="col-md-8">
											<select id="address" class="form-control">
												<option value="" selected>請選擇</option>
											</select>
										</div>
									</div>


								</div>
								<!-- class="col-md-8" -->
								<div id="map"></div>

								<div class="col-md-8">
									<img id="mapimg"
										src="https://maps.googleapis.com/maps/api/staticmap?center=%E5%8F%B0%E5%8C%97101&zoom=17&size=600x400&maptype=roadmap&markers=color:red%7C%E5%8F%B0%E5%8C%97101&key=AIzaSyAYD9w-VVl7VwL9mC1SPzvQmMw6Sxk3Ul0" />
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">回覆</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/question/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript">
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
							var mapimg = 'https://maps.googleapis.com/maps/api/staticmap?center='
									+ address
									+ '&zoom=17&size=600x400&maptype=roadmap&markers=color:red%7C'
									+ address
									+ '&key=AIzaSyAYD9w-VVl7VwL9mC1SPzvQmMw6Sxk3Ul0'
							$('#mapimg').attr("src", mapimg);

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


