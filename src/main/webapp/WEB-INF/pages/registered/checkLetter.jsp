<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

		<input type="hidden" id="id" name="id" value="${resendInfo.id}" /> <br>
		<div style="text-align: center;">
			<h1>請至您的信箱點擊確認信連結</h1>
			<img class="checkLetter" name="checkLetter"
				src="/resources/pic/registered/checkLetter.png" width="40%">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div>
						<a href="" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="resendButton">重送驗證信</a>
					</div>

				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#resendButton").bind("click", function() {
	
		var $btn = $(this);
			$btn.button("loading");
			
		$.get("<c:url value='/registered/checkLetter'/>", "dataForm",
				function(data) {
					console.log(data.messages.length);
					if (data.messages.length==0){
						alert("SUCCESS");
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
	
})
</script>