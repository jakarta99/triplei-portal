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
<style>
.irrfield {
	width: 70%;
	height: 120%;
}
</style>
</head>
<body>
	<div class="container-fluid"
		style="width: 100%; height: 100%; position: absolute; padding: 0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding: 0; width: 100%; height: 100%; color: white;">
			<div class="col-sm-4"
				style="background-color: #5C8DEC; height: 100%; overflow-y: auto;">
				<div class="col-sm-12"
					style="text-align: center; margin-top: 7%; display: table;">
					<br /> <br />
					<h1>IRR計算機</h1>
					<br />
				</div>
				<div class="col-sm-12">
					<img src="/resources/pic/IRR計算機/images/img_1.png" width="10%"
						style="margin-top: 15%; margin-left: 25%"> <img
						src="/resources/pic/IRR計算機/images/img_2.png" width="10%"
						style="margin-top: 15%; margin-left: 25%"> <img
						src="/resources/pic/IRR計算機/images/img_0.png" width="100%"
						style="margin-top: 0%">
				</div>
			</div>
			<div class="col-lg-8 bg-info"
				style="background-color: white; height: 100%; padding-top: 7%; color: black; overflow-y: scroll">

				<div class="col-lg-6">
					<div class="col-md-12" style="margin: 3% auto 10% auto">
						<div class="col-md-5">
							<label for="premium">年繳保費(元)</label>
						</div>
						<div class="col-md-7">
							<input class="form-control check_change" id="premium"
								type="number" min="0" style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-12" style="margin: 3% auto 10% auto">
						<div class="col-md-5">
							<label for="times">領回年期</label>
						</div>
						<div class="col-md-7">
							<input class="form-control check_change" id="times" name="times"
								type="number" min="0" style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-12" style="margin: 3% auto 10% auto">
						<div class="col-md-5">
							<label for="period">繳費年期(年)</label>
						</div>
						<div class="col-md-7">
							<input class="form-control check_change" id="period"
								name="period" type="number" min="0"
								style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-12" style="margin: 3% auto 10% auto">
						<div class="col-md-5">
							<label for="expired">期滿領回(元)</label>
						</div>
						<div class="col-md-7">
							<input class="form-control check_change" id="expired"
								name="expired" type="number" min="0"
								style="border: 1px solid #5C8DEC" />
						</div>
					</div>
				</div>
				<div id="showResults" class="col-lg-6">
					<div class="col-md-12" style="margin: 2% auto 3% auto">
						<div class="col-md-12">
							<span style="font-size: 150%">總繳金額</span>
						</div>
						<div class="col-md-12" style="margin: 0 auto 2% auto">
							<h3 id="showTotalPaid" style="color: #5C8DEC">總繳金額</h3>
						</div>
					</div>
					<div class="col-md-12" style="margin: 2% auto 3% auto">
						<div class="col-md-12">
							<span style="font-size: 150%">淨報酬</span>
						</div>
						<div class="col-md-12" style="margin: 0 auto 2% auto">
							<h3 id="showRemuneration" style="color: #5C8DEC">淨報酬</h3>
						</div>
					</div>
					<div class="col-md-12" style="margin: 2% auto 3% auto">
						<div class="col-md-12">
							<span style="font-size: 150%">內部報酬率IRR</span>
						</div>
						<div class="col-md-12" id="spanIRR"
							style="margin: 0 auto 2% auto;">
							<h3 id="showIRR" style="color: #5C8DEC;">IRR</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(".check_change").on(
				"change",
				function() {
					var premium = $("#premium").val();
					var times = $("#times").val();
					var period = $("#period").val();
					var expired = $("#expired").val();

					var url = "/product/calculationofirr/" + premium + "/"
							+ times + "/" + period + "/" + expired;


					if (premium != "" && times != "" && period != ""
							&& expired != "") {
						$.getJSON(url, function(value) {
							var IRR = JSON.stringify(value.responseJSON);
							$("#showIRR").empty();
							$("#showIRR").append(IRR + "%");

						})
					}
					
					var url = "/product/calculationofremuneration/" + premium
							+ "/" + times + "/" + period + "/" + expired;
					if (premium != "" && times != "" && period != ""
							&& expired != "") {
						$.getJSON(url, function(value) {
							var Remuneration = JSON
									.stringify(value.responseJSON);
							$("#showRemuneration").empty();
							$("#showRemuneration").append(Remuneration);

						})
					}
					
					var url = "/product/calculationoftotal/" + premium
							+ "/" + times + "/" + period;
					if (premium != "" && times != "" && period != "") {
						$.getJSON(url, function(value) {
							var total = JSON
									.stringify(value.responseJSON);
							$("#showTotalPaid").empty();
							$("#showTotalPaid").append(total);

						})
					}
				})
	</script>
</body>
</html>
