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
		<div style="padding-top:8vh">
			<div class="col-xs-12 col-sm-3"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #5C8DEC;"><!--左半藍 -->
 				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
				<div class="col-xs-6 col-sm-12"
					style="display: table-cell; vertical-align: top; padding-top: 15vh;padding-left: 4vw;">
					<h1 style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;padding-bottom: 5vh">保險公司</h1>
					<h4 style="margin-bottom: 5vh">
						<a href="/insurer/list"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各公司列表</a>
					</h4>
					<h4 style="margin-bottom: 5vh">
						<a href="/insurer/filt"
							style="margin-top: 0;text-align: left;font-weight: normal;opacity: 0.8;color: white;font-family: 微軟正黑體;">各別項目查詢</a>
					</h4>
					<div id="bm" style="width: 170%;position: absolute;left: -100px"></div>
				</div>
				<div class="col-xs-3 col-sm-0"></div><!--切版用div兩邊 -->
			</div>
			<div class="col-xs-12 col-sm-9" id="style-1"
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
					<div class="col-xs-12" style="width: 60vw; display: none;" id="bisratiooo">資本適足率可以看出公司穩定程度<br>
					資本適足：指保險業資本適足率達本法第一百四十三之四第一項規定之百分之二百。<br>
					資本不足：指保險業資本適足率在百分之一百五十以上，未達百分之二百。<br>
					資本顯著不足：指保險業資本適足率在百分之五十以上，未達百分之一百五十。<br>
					資本嚴重不足：指依本法第一百四十三之四第三項規定，保險業資本適足率低於百分之五十或保險業淨值低於零。<br>
					</div>
					<div class="col-xs-12">
						<div id="flot-bisratioo" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="returnonAssetss" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;" >資產報酬率▽</button><br>
					<div class="col-xs-12" style="width: 60vw; display: none;" id="returnonAssetsss" >
					資產報酬率<br>
					是企業資本總額中平均每百元所能獲得的純利潤。它是用以衡量公司運用所有資本所獲經營成效的指標資本報酬率越高<br>
					表明公司資本的利用率越高，反之則資本未能得到充分利用。<br><br>
					保險公司的資產報酬率跟保單未來宣告利率有很大的關係唷<br>
					</div>
					<div class="col-xs-12">
						<div id="flot-returnonAssetss" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="persistencyRatioo" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">保單繼續率▽</button><br>
					<div class="col-xs-12" style="width: 60vw; display: none;" id="persistencyRatiooo">
					保單繼續率<br>
					指要保人持續繳納保費的比率，通常做為評估保險公司招攬業務品質高低的一個指標。<br>
					
					</div>
						<div class="col-xs-12">
						<div id="flot-placeholder" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="litigationRatioo" style="background-color: #FAF7F7;  color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">訴訟率▽</button><br>
					<div  class="col-xs-12" style="width: 60vw; display: none;" id="litigationRatiooo">
					訴訟率<br>
					訴訟率=理賠訴訟件數/申請理賠件數，也就是「保戶對保險理賠內容不滿意，申請訴訟的機率」<br>
					我可以用來評斷該保險公司對「後續理賠服務」，是否專業且積極<br>
					比率較低則可能表示該公司較站在保戶的立場，積極爭取權益。<br>
					
					</div>
					<div class="col-xs-12">
						<div id="flot-litigationRatioo" class="col-xs-12"
							style="width: 60vw; height: 100vh; display: none;"></div>
					</div>
					<button id="appealRatioo" style="background-color: #FAF7F7; color:#5E5E5E;opacity: 0.8; border: 0px #5C8DEC solid; width: 10em;text-align: left;">申訴率▽</button><br>
					<div  class="col-xs-12" style="width: 60vw; display: none;" id="appealRatiooo">
					申訴率<br>
					申訴率=(申訴件數/簽單契約總件數)*10000，也可以用來評斷該保險公司對「後續理賠服務」<br>
					是否專業且積極，比率較低則可能表示該公司較站在保戶的立場，積極爭取權益。<br>
					
					</div>
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
				$('#bisratiooo').toggle(500);
				$('#flot-bisratioo').toggle(500,function(){
					$.plot($("#flot-bisratioo"), dataSet, optionbisRatio);
				});
			});
			$("#returnonAssetss").click(function() {//資產報酬率
				DoreturnonAssetss();
				$('#returnonAssetsss').toggle(500);
				$('#flot-returnonAssetss').toggle(500,function(){
					$.plot($("#flot-returnonAssetss"), dataSet, optionreturnonAssets);
				});
			});
			$("#persistencyRatioo").click(function() {//保單繼續率
				DopersistencyRatioo();
				$('#persistencyRatiooo').toggle(500);
				$('#flot-placeholder').toggle(500,function(){
					$.plot($("#flot-placeholder"), dataSet, optionpersistencyRatio);
				});
			});
			$("#litigationRatioo").click(function() {//訴訟率
				DolitigationRatioo();
				$('#litigationRatiooo').toggle(500);
				$('#flot-litigationRatioo').toggle(500,function(){
					$.plot($("#flot-litigationRatioo"), dataSet, optionlitigationRatio);
				});
			});
			$("#appealRatioo").click(function() {//申訴率
				DoappealRatioo();
				$('#appealRatiooo').toggle(500);
				$('#flot-appealRatioo').toggle(500,function(){
					$.plot($("#flot-appealRatioo"), dataSet, optionappealRatio);
				});
			});
			$("#strcompare").click(function() {//開始比較按鈕(預設第一出現表格)
				Dobisratioo();
				$('#insurerfilter').toggle(500);
				$('#bisratiooo').toggle(500);
				$('#flot-bisratioo').toggle(500,function(){
					$.plot($("#flot-bisratioo"), dataSet, optionbisRatio);
				});
			});
// 			-------------------------------------------
// 			window.onresize = function(event) {
// 				$.plot($("#flot-placeholder"), dataSet, optionpersistencyRatio);

// 		    }
		});
		var animation = bodymovin.loadAnimation({
			  container: document.getElementById('bm'),
			  renderer: 'svg',
			  loop: true,
			  autoplay: true,
			  path: '/resources/pic/各公司資訊/company.json'
			})
	</script>
</body>
</html>