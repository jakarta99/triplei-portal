<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i重設密碼</title>

</head>
<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div style="padding-top:9vh">
			
			<h3>重設密碼</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="${entity.id}" />
						<input type="hidden" id="editState" name="editState" value="forgetpw"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>會員密碼設定</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="password" class="col-md-2 control-label">輸入新密碼</label>
									<div class="col-md-8">
										<input type="password" class="form-control" id="password"
											name="password" placeholder="password" value="" /> 
											<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="checkPassword" class="col-md-2 control-label">再次輸入密碼</label>
									<div class="col-md-8">
										<input type="password" class="form-control" id="checkPassword"
											name="checkPassword" placeholder="checkPassword" value="" />
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
			</div>


		</div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	</div>	
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#saveButton").bind("click", function() {
		
		var $btn = $(this);
			$btn.button("loading");
			
		$.put("<c:url value='/registered'/>", "dataForm",
				function(data) {
					console.log(data.messages.length);
					if (data.messages.length==0){
						swal("SUCCESS","更新成功");
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
})
</script>