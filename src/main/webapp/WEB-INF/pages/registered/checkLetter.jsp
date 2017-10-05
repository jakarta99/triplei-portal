<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html>
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
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<!-- 先寄信 -->
		<form class="form-horizontal" id="dataForm">
		<input type="hidden" id="id" name="id" value="${resendInfo.id}" /> 
		<input type="hidden" id="email" name="email" value="${resendInfo.email}" /><br>
		<div style="text-align: center;padding-top:9vh" >
			<h1>請至您的信箱點擊確認信連結</h1>
			<img class="checkLetter" name="checkLetter"
				src="/resources/pic/registered/checkLetter.png" width="40%">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div>
						<a href="#"  class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="resendButton">重送驗證信</a>
					</div>

				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	var $count = 0;
	window.onload=sendLetter;
	function sendLetter(){
		$("#resendButton").click();
	}
	
	$("#resendButton").bind("click", function() {
	
		var $btn = $(this);
			$btn.button("loading");
			
		$.put("<c:url value='/registered/sendCheckLetter'/>", "dataForm",
				function(data) {
					
					if (data.messages.length==0){
						$count = $count + 1;  
						if($count>1){
							swal("SUCCESS","寄送成功");
						}
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
	
})
</script>