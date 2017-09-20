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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
			<div class="col-xs-12 col-sm-4"
				style="height: 100vh; display: table; table-layout: fixed; background-color: #5C8DEC;"><!--左半藍 -->
				<div
					style="display: table-cell; vertical-align: top; padding-top: 20vh; text-align: center;">
					<h1 style="color: rgb(254, 249, 246); font-size: 2 em;">保險公司</h1>
					<h3>
						<a href="/insurer/list"
							style="color: rgb(254, 249, 246); opacity: 0.6; font-size: 1em;">各公司列表</a>
					</h3>
					<h3>
						<a href="/insurer/filt"
							style="color: rgb(254, 249, 246); opacity: 0.6; font-size: 1em;">各別項目查詢</a>
					</h3>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8"
				style="height: 100vh;  background-color: #FAF7F7; overflow: auto;position: relative;"><!--右半白 -->
				<div 
					style="height: 100vh; position: relative; padding-top: 24vh;"><!--選擇框 -->
				
				<div class="col-xs-2 col-sm-1" style="height: 100%;"></div>
				<button id="btn" style="background-color: white; color: #5C8DEC; 
				border:1px #5C8DEC solid;width: 10em">篩選保險公司</button><br>
				
				<div class="col-xs-8 col-sm-7 col-md-4" style="padding-left: 0px;padding-right: 0px;">
				<div  id="insurerfilter" style="background-color:#5C8DEC;display: none;">
					<p class="col-xs-12 col-sm-12"><input type="checkbox" id="checkall">全選</p>
					<c:forEach items="${models}" var="model">
					<p class="col-xs-6 col-sm-6">
					<input type="checkbox" name="${model.shortName}">${model.shortName}
					</p>
					</c:forEach>
					<p class="col-xs-6 col-sm-6"><button>開始比較</button></p>
					<button  style=" visibility:hidden;">開始比較</button>
				</div>
				<div id="flot-placeholder" style="width:450px;height:100vh;"></div>
				</div>
				<div class="col-xs-2 col-sm-4 col-md-7"></div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function(){
// 		$('#insurerfilter').toggle(0);
		$('#btn').click(function(){
			$('#insurerfilter').toggle(1000);
		})
		$('#checkall').change(function(){
			var b = $(this).prop('checked')
	        $(':checkbox').prop('checked',b)
	            //切換所有checkbox的勾選狀態        	
		})
// 		if($('#insurerfilter').){
			
// 		}
	});
	</script>
</body>
</html>