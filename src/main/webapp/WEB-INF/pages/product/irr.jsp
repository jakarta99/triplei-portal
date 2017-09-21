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
h3 {
	color: #5C8DEC;
	opacity: 0.8;
}

.irrfield {
	width: 70%;
	height: 120%;
}

.minus {
	color: #DC143C;
	opacity: 0.8;
}
</style>
</head>
<body>
	<div class="container-fluid" style="width: 100%; height: 100%; position: absolute; padding: 0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding: 0; width: 100%; height: 100%; color: white;">
			<div class="col-sm-4" style="background-color: #5C8DEC; height: 100%; overflow-y: auto;">
				<div class="col-sm-10" style="margin-top: 7%; display: table;">
					<br /> <br />
					<h2 style="padding: 0;">儲蓄險真正的報酬率</h2>
					<br />
					<h2 style="padding: 0; margin-top: 0">IRR計算機</h2>
					<br /> <br />
				</div>
				<div class="col-sm-10">
					<p style="opacity: 0.7;">保單上面的數字那麼多，哪個才是真正的報酬率呢?別被搞混了!IRR才是真正的報酬率!</p>
					<br />
					<p style="opacity: 0.7;">IRR又稱為「年化報酬率」，假設一年期定存的利率為1.23%，那麼它的IRR即為1.23%。</p>
					<img src="/resources/pic/IRR計算機/images/img_1.png" width="10%"	style="margin-top: 15%; margin-left: 25%">
					<img src="/resources/pic/IRR計算機/images/img_2.png" width="10%" style="margin-top: 15%; margin-left: 25%">
					<img src="/resources/pic/IRR計算機/images/img_0.png" width="100%" style="margin-top: 0%">
				</div>
			</div>
			<div class="col-lg-8 bg-info" style="background-color: white; height: 100%; padding-top: 7%; color: black;">
				<div class="col-lg-8" style="padding-top: 10%;">
				
					<div class="col-md-6" style="padding: 0; margin: 3% auto 10% auto">
						<div class="col-md-4" style="padding: 0;">
							<label for="period">繳費年期(年)</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="period" name="period" type="number" min="0" value="1" style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-6" style="padding: 0; margin: 3% auto 10% auto">
						<div class="col-md-4" style="padding: 0;">
							<label for="premium">年繳保費(元)</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="premium" type="number" min="0" value="10000" style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-6" style="padding: 0; margin: 3% auto 10% auto">
						<div class="col-md-4" style="padding: 0;">
							<label for="times">第幾年末領回</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="times" name="times" type="number" min="0" value="1" style="border: 1px solid #5C8DEC" />
						</div>
					</div>

					<div class="col-md-6" style="padding: 0; margin: 3% auto 10% auto">
						<div class="col-md-4" style="padding: 0;">
							<label for="expired">領回金額</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="expired" name="expired" type="number" min="0" value="10123" style="border: 1px solid #5C8DEC" />
						</div>
					</div>
					
				</div>
				
				<div id="showResults" class="col-lg-4">
					<div class="col-md-12" style="padding: 0; margin: 2% auto 3% auto">
						<div class="col-md-12" style="padding: 0;">
							<span style="font-size: 150%">總繳金額</span>
						</div>
						<div class="col-md-12" style="margin: 0 auto 2% auto">
							<h3 id="showTotalPaid">總繳金額</h3>
						</div>
					</div>
					<div class="col-md-12" style="padding: 0; margin: 2% auto 3% auto">
						<div class="col-md-12" style="padding: 0;">
							<span style="font-size: 150%">總淨報酬</span>
						</div>
						<div class="col-md-12" style="margin: 0 auto 2% auto">
							<h3 id="showRemuneration">總淨報酬</h3>
						</div>
					</div>
					<div class="col-md-12" style="padding: 0; margin: 2% auto 3% auto">
						<div class="col-md-12" style="padding: 0;">
							<span style="font-size: 150%">IRR內部報酬率</span>
						</div>
						<div class="col-md-12" id="spanIRR"
							style="margin: 0 auto 2% auto;">
							<h3 id="showIRR">1.23%</h3>
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

					if (times.indexOf(".") == -1 && period.indexOf(".") == -1) {

						if (premium != "" && times != "" && period != ""
								&& expired != "") {
							$.getJSON(url, function(value) {
								var IRR = JSON.stringify(value.responseJSON);
								if (IRR.indexOf("-") != -1) {
									$("#showIRR").addClass("minus");
								}else{
									$("#showIRR").removeClass("minus");
								}
								$("#showIRR").empty();
								$("#showIRR").append(IRR + "%");

							})
						}

						var url = "/product/calculationofremuneration/"
								+ premium + "/" + times + "/" + period + "/"
								+ expired;
						if (premium != "" && times != "" && period != ""
								&& expired != "") {
							$.getJSON(url, function(value) {
								var Remuneration = JSON
										.stringify(value.responseJSON);
								if (Remuneration.indexOf("-") != -1) {
									$("#showRemuneration").addClass("minus");
								}else{
									$("#showRemuneration").removeClass("minus");
								}
								$("#showRemuneration").empty();
								$("#showRemuneration").append(Remuneration);

							})
						}
						
						var url = "/product/calculationoftotal/" + premium
								+ "/" + times + "/" + period;
						if (premium != "" && times != "" && period != "") {
							$.getJSON(url, function(value) {
								var total = JSON.stringify(value.responseJSON);
								$("#showTotalPaid").empty();
								$("#showTotalPaid").append(total);

							})
						}

					} else {
						alert("請輸入整數的年期");
					}
				})
	</script>
</body>
</html>
