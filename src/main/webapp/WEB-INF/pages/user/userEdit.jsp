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
<script type='text/javascript' src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<title>Triple i</title>

</head>
<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div>
			
			<h3>會員基本資料設定</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
					<input type="hidden" id="id" name="id" value="${userDetails.id}"/>
					<input type="hidden" id="editState" name="editState" value="info"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>基本資料修改</strong></h4>
							</div>
							<div class="panel-body">
							
								<div class="form-group required">
			    					<label for="accountNumber" class="col-md-2 control-label">會員帳號</label>
			    					<div class="col-md-8">
			    						<p class="form-control-static">${userDetails.accountNumber}</p>
			    						<input type="hidden" class="form-control" id="accountNumber" name="accountNumber" value="${userDetails.accountNumber}"/>
			    					</div>
								</div>	
								
			  					<div class="form-group required">
			    					<label for="name" class="col-md-2 control-label">會員姓名</label>
			    					<div class="col-md-8">
			      						<input type="text" class="form-control" id="name" name="name" placeholder="name" value="${userDetails.name}"/>
			    					</div>
								</div>	
								
								<div class="form-group required">
			    					<label for="email" class="col-md-2 control-label">電子信箱</label>
			    					<div class="col-md-8">
			      						<input type="text" class="form-control" id="email" name="email" placeholder="email" value="${userDetails.email}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
								
								<div class="form-group required">
			    					<label for="birthdate" class="col-md-2 control-label">生日</label>
			    					<div class="col-md-8">
			      						<input type="text" class="form-control" id="birthdate" name="birthdate" placeholder="yyyy-mm-dd" value="${userDetails.birthdate}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
							
								<div class="form-group required">
									<label for="gender" class="col-md-2 control-label">性別</label>
									<div class="col-md-8">
										<select id="gender" name=gender class="form-control">
											<option value="female" ${userDetails.gender == 'female' ? 'selected': ''}>女性</option>
											<option value="male" ${userDetails.gender == 'male' ? 'selected': ''}>男性</option>
										</select>
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								
								<div class="form-group required">
			    					<label for="tel" class="col-md-2 control-label">聯絡電話</label>
			    					<div class="col-md-8">
			      						<input type="text" class="form-control" id="tel" name="tel" placeholder="+886" value="${userDetails.tel}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
								
								
								</div>								
							</div>
						</div>	
		 			</form>
				</div>				
			</div>

			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">已完成/修改基本資料 回首頁</a>
					</div>
				</div>
			</div>


		</div>

</body>
</html>
<script type="text/javascript">
$(function(){
	
	$('#birthdate').inputmask({
		  mask: '9999-99-99'
	});
	
	$('#tel').inputmask({
		  mask: '9999-999-999'
	});
	
	$('#email').on("change",function(){
		var email = $('#email').val();
		var userAccountNumber = "<c:out value="${userDetails.accountNumber}"/>";
		$.ajax({
			type : "GET",
			url : "<c:url value='/user/reset/check/" + userAccountNumber + "/" + email +"'/>",
			success : function(data){
				if(data == true){
					swal("email可以使用~");
				}else{
					$('#email').val(null);
					swal("email已重複,請重新輸入!!");
				}
			},
		})
	})
	
	
	
	
	
	$("#saveButton").bind("click", function() {
		
		var $btn = $(this);
			$btn.button("loading");
		var email = $("#email").val();
		var name = $("#name").val();
		var birthdate = $("#birthdate").val();
		var tel = $("#tel").val();
		
		if(email==""||name==""){
			swal('資料請輸入完整喔~~')
		}else{
			$.put("<c:url value='/user/reset'/>", "dataForm",
					function(data) {
						console.log(data.messages.length);
						if (data.messages.length==0){
							swal("SUCCESS","資料更新成功");
							$btn.button("reset");
//	 						location.href = '/user/reset/info'
						}	
					}, function(data, textStatus, jqXHR) {
						$btn.button("reset");
					});
		}
		$btn.button("reset");			
	});
	
})
</script>