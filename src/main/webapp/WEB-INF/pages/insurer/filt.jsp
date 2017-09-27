<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<c:import url="/WEB-INF/pages/layout/flotscript.jsp"></c:import>
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>
</head>
<body>




	<div class="container-fluid"
		style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div>
			<div class="col-xs-12 col-sm-3"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #5C8DEC;"><!--左半藍 -->
 				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
				<div class="col-xs-6 col-sm-12"
					style="display: table-cell; vertical-align: top; padding-top: 15vh;padding-left: 4vw;">
					<h1 style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">保險公司</h1>
					<h4>
						<a href="/insurer/list"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各公司列表</a>
					</h4>
					<h4>
						<a href="/insurer/filt"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各別項目查詢</a>
					</h4>
					<img alt="" src="/resources/pic/各公司資訊/images/img_1.png"
							style="width: 80% ;padding-top: 8vh">
				</div>
				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
			</div>
			<div class="col-xs-12 col-sm-9"
				style="height: 100vh; background-color: white; overflow: auto; position: relative;">
				<!--右半白 -->
				<div style="height: 100vh; position: relative; padding-top: 16vh;">
					<!--選擇框 -->

					<div class="col-xs-1 col-sm-1" style="height: 100%;"></div>
					<button id="btn" style="background-color: white; color: #5C8DEC; border: 1px #5C8DEC solid; width: 10em">篩選保險公司</button><br>
					<br>
					
					<div class="col-xs-10 col-sm-7 col-md-4"
						style="padding-left: 0px; padding-right: 0px;">
						<div id="insurerfilter"
							style="background-color: #5C8DEC; display: none;">
							<p class="col-xs-12 col-sm-12">
								<input type="checkbox" id="checkall" checked="checked">全選
							</p>
							<!-- 							<p id="insurerfilter2"> -->
							<c:forEach items="${models}" var="model">
								<p class="col-xs-6 col-sm-6">
									<input id="chk${model.id}" type="checkbox" checked="checked"
										name="${model.shortName}">${model.shortName}
								</p>
							</c:forEach>
							<!-- 							</p> -->
							<p class="col-xs-6 col-sm-6">
								<button id="strcompare">開始比較</button>
							</p>
							<button style="visibility: hidden;">比較</button>
						</div>
<!-- 					XX率button區 -->
					<button id="bisratioo" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;padding-top: 1vh">資本適足率▽</button><br>
					<div class="col-xs-12">
						<div id="flot-bisratioo" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="returnonAssetss" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;" >資產報酬率▽</button><br>
					<div class="col-xs-12">
						<div id="flot-returnonAssetss" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="persistencyRatioo" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">保單繼續率▽</button><br>
						<div class="col-xs-12">
						<div id="flot-placeholder" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="litigationRatioo" style="background-color: #FAF7F7;  color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">訴訟率▽</button><br>
					<div class="col-xs-12">
						<div id="flot-litigationRatioo" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="appealRatioo" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">申訴率▽</button><br>
					<div class="col-xs-12">
						<div id="flot-appealRatioo" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					</div>
					<div class="col-xs-1 col-sm-4 col-md-7"></div>
				</div>
			</div>
		</div>
	</div>
<%-- 	window.document.write(${models}); --%>

	<script type="text/javascript">
		
		$(function() {
// 			$('#btn').one('click', function() {
// 				$('#flot-placeholder').toggle(0);
// 			})   先留一下
			$('#btn').click(function() {//全部收起再給出checkbox給人家選
				$('div[id^="flot"]').hide();
				$('#insurerfilter').toggle(500);
			})
// 			$('#persistencyRatioo').click(function() {//收起該表格
// 				$('#flot-placeholder').toggle(500);
// 			})
			$('#checkall').change(function() {//全選
				var b = $(this).prop('checked')
				$(':checkbox').prop('checked', b)
				//切換所有checkbox的勾選狀態        	
			});
			$(':checkbox').click(function() {//全選連動勾銷
				var c = $(this).prop('checked')
				if (c==false){
					$('#checkall').prop('checked', c)
				}
// 				window.alert(c);
				//切換checkall的全選狀態        	
			});
// 			---------------------------------
			$("#bisratioo").click(function() {//資本適足率
				Dobisratioo();
				$('#flot-bisratioo').toggle(500,function(){
					$.plot($("#flot-bisratioo"), dataSet, optionbisRatio);
				});
			});
			$("#returnonAssetss").click(function() {//資產報酬率
				DoreturnonAssetss();
				$('#flot-returnonAssetss').toggle(500,function(){
					$.plot($("#flot-returnonAssetss"), dataSet, optionreturnonAssets);
				});
			});
			$("#persistencyRatioo").click(function() {//保單繼續率
				DopersistencyRatioo();
				$('#flot-placeholder').toggle(500,function(){
					$.plot($("#flot-placeholder"), dataSet, optionpersistencyRatio);
				});
			});
			$("#litigationRatioo").click(function() {//訴訟率
				DolitigationRatioo();
				$('#flot-litigationRatioo').toggle(500,function(){
					$.plot($("#flot-litigationRatioo"), dataSet, optionlitigationRatio);
				});
			});
			$("#appealRatioo").click(function() {//申訴率
				DoappealRatioo();
				$('#flot-appealRatioo').toggle(500,function(){
					$.plot($("#flot-appealRatioo"), dataSet, optionappealRatio);
				});
			});
			$("#strcompare").click(function() {//開始比較按鈕(預設第一出現表格)
				DopersistencyRatioo();
				$('#insurerfilter').toggle(500);
				$('#flot-placeholder').toggle(500,function(){
					$.plot($("#flot-placeholder"), dataSet, optionpersistencyRatio);
				});
			});
// 			-------------------------------------------
// 			window.onresize = function(event) {
// 				$.plot($("#flot-placeholder"), dataSet, optionpersistencyRatio);

// 		    }
		});
	</script>
</body>
</html>