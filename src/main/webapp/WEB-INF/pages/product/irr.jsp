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

.col-md-8{
	margin-top:0%;
	padding-left:5%;
}

</style>
</head>
<body>
	<div class="container-fluid" style="width: 100%; height: 100%; position: absolute; padding: 0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding: 0; width: 100%; height: 100%; color: white;padding-top:9vh" class="col-sm-12">
			<div class="col-sm-4" style="background-color: #5C8DEC; height: 100%; overflow-y: auto;">
				<div class="col-sm-10" style="margin-top: 7%; display: table;">
					<h2 style="padding: 0;margin-bottom: 3vh;margin-top: 3vh">儲蓄險真正的報酬率</h2>
					<h2 style="padding: 0;margin-bottom: 3vh">IRR計算機</h2>
				</div>
				<div class="col-sm-10">
					<p style="opacity: 0.7;">保單上面的數字那麼多，哪個才是真正的報酬率呢?別被搞混了!IRR才是真正的報酬率!</p>
					<br />
					<p style="opacity: 0.7;">IRR又稱為「年化報酬率」，假設一年期定存的利率為1.23%，那麼它的IRR即為1.23%。</p>
					<div id="bm" style="width: 100%;position: relative;"></div>
				</div>
			</div>
			<div class="col-sm-4" style="background-color: white; height: 60vh; padding-top: 5%; color: black;">
				
				<div class="col-sm-12" style="padding-top: 0%; height: 90vh;">
				
					<div class="col-md-12" style="height:80px;">
						<div class="col-md-8">
							<label for="period" class="explan" id="periodlabel">繳費年期(年)</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="period" name="period" type="number" min="0" value="1" style="width:300px;border: 1px solid #5C8DEC" />
							<div style="opacity: 0.8;">
								<p class="explancontent" id="periodlabelcontent">保費要繳幾年?(躉繳請輸入1唷)</p>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="height:80px;margin: 3% auto">
						<div class="col-md-8">
							<label for="premium" class="explan" id="premiumlabel">年繳保費(元)</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="premium" type="number" min="0" value="10000" style="width:300px;border: 1px solid #5C8DEC" />
							<div style="opacity: 0.8;">
								<p class="explancontent" id="premiumlabelcontent">每年繳多少錢呢?</p>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="height:80px;margin: 3% auto">
						<div class="col-md-8">
							<label for="times" class="explan" id="timeslabel">第幾年末領回</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="times" name="times" type="number" min="0" value="1" style="width:300px;border: 1px solid #5C8DEC" />
							<div style="opacity: 0.8;">
								<p class="explancontent" id="timeslabelcontent">期望在第幾年後領回?</p>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="height:80px;margin: 3% auto">
						<div class="col-md-8">
							<label for="expired" class="explan" id="expiredlabel">領回金額</label>
						</div>
						<div class="col-md-8">
							<input class="form-control check_change" id="expired" name="expired" type="number" min="0" value="10123" style="width:300px;border: 1px solid #5C8DEC" />
							<div style="opacity: 0.8;">
								<p class="explancontent" id="expiredlabelcontent">填入的領回時間當年度的解約金額</p>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
			<div class="col-sm-4" style="background-color: white; height: 90vh; padding-top: 5%; color: black;">
			
				<div id="showResults" class="col-sm-12" style="padding-top: 5%; height: 90vh;">
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
						<div class="col-md-12" id="spanIRR" style="margin: 0 auto 2% auto;">
							<h3 id="showIRR">1.23%</h3>
						</div>
					</div>
				</div>
			
			</div>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>

	<script>
	
	$('p.explancontent').hide();
	$('label.explan').bind('mouseover', function() {
	    $('#'+$(this).attr('id')+'content').show();
	});
	
	$('label.explan').bind('mouseout', function() {
	    $('#'+$(this).attr('id')+'content').hide();
	}); 
	
		$(".check_change").on(
				"change",
				function() {
					var premium = $("#premium").val();
					var times = $("#times").val();
					var period = $("#period").val();
					var expired = $("#expired").val();
					var url = "/product/calculationofirr/" + premium + "/"
							+ times + "/" + period + "/" + expired;

					if (times.indexOf(".") == -1 && period.indexOf(".") == -1 && times.indexOf("-") == -1 
							&& period.indexOf("-") == -1 && premium.indexOf("-") == -1 && expired.indexOf("-") == -1) {

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
						if(period>times){
							swal("貼心提醒","繳費年期如果小於領回年期幾乎都會虧本唷~");
						}
						}else if(period.indexOf('-')!=-1){
							swal("貼心提醒","沒有負的年期唷~");
						}else if(times.indexOf('-')!=-1){
							swal("貼心提醒","沒有負的年期唷~");
						}else if(premium.indexOf('-')!=-1){
							swal("貼心提醒","沒有這種機車保單唷~");
						}else if(expired.indexOf('-')!=-1){
							swal("貼心提醒","沒有這種機車保單唷~");
							
					} else {
						swal("請輸入整數的年期");
					}
				})
				
			var animation = bodymovin.loadAnimation({
			  container: document.getElementById('bm'),
			  renderer: 'svg',
			  loop: true,
			  autoplay: true,
			  path: '/resources/pic/IRR計算機/data.json'
			})
	</script>
</body>
</html>
