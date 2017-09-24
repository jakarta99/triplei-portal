<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
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
<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<!-- 輸入帳號 -->
		<form class="form-horizontal" id="dataForm">
		<div style="text-align: center;">
			<img class="forgetPassword" name="forgetPassword"
				src="/resources/pic/registered/forgetPassword.png" width="35%">
			
			<div class="form-group">
				<h3>請輸入註冊時的E-mail</h3>
			</div>
			
			<div class="form-group required">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<input type="text" class="form-control" id="accountNumber" name="accountNumber" placeholder="accountNumber" value="" /> 
					<span class="help-block"> <div class="text-danger"></div></span>
				</div>
				<div class="col-md-4"></div>
			</div>			
				
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div>
						<a href="#"  class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="next">下一步</a>
					</div>

				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
		</form>
	</div>

</body>
</html>
<script type="text/javascript">
$(function(){
	$("#next").bind("click", function() {
		
		var $btn = $(this);
			$btn.button("loading");
			
		$.put("<c:url value='/registered/genNewPassword'/>", "dataForm",
				function(data) {
					
					if (data.messages.length==0){
						//alert("SUCCESS");
						location.href = '/registered/sendNewPassword?id=' + data.data.id
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
})

</script>