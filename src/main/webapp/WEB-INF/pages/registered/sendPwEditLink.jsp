<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Tripe i重送臨時密碼</title>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<!-- 載入後寄信-->
		<form class="form-horizontal" id="dataForm">
		<input type="hidden" id="id" name="id" value="${resendUserEntity.id}" /> 
		<div style="text-align: center;padding-top:9vh">
			<img class="forgetPassword" name="forgetPassword"
				src="/resources/pic/registered/forgetPassword.png" width="35%">
			
			<div class="form-group">
				<h3>已發送變更密碼連結至您的信箱</h3>
			</div>
					
			
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div>
						<a href=""  class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="resendButton">重送臨時密碼</a>
					</div>

				</div>
				<div class="col-md-3"></div>
			</div>
					
		</div>
		</form>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	</div>

</body>
</html>
<script type="text/javascript">
$(function(){
	window.onload=sendLetter;
	function sendLetter(){
		$("#resendButton").click();
	}
	
	$('#resendButton').hide();
	
	$("#resendButton").bind("click", function() {
		
		var $btn = $(this);
			$btn.button("loading");
			
		$.put("<c:url value='/registered/sendPwEditEMail'/>", "dataForm",
				function(data) {
					
					if (data.messages.length==0){
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
})
</script>