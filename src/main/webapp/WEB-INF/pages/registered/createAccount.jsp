<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<script type='text/javascript' src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<title>Triple i免費註冊</title>
<style>

</style>
</head>

<body>

		<div class="container-fluid" style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding-top:9vh">
			<form class="form-horizontal" id="dataForm">
				<div class="col-sm-6" style="height: 60vh; padding-right: 0px; padding-left: 0px;">
					<!-- 左邊白 -->
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
				
						<div class="panel-heading">
							<h3><span></span>&nbsp; <strong>免費註冊</strong></h3>
						</div>
								
						<div class="form-group required">
							<label for="name" class="col-md-3 control-label">會員姓名</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="姓名" value="" /> <span class="help-block">
									<div class="text-danger"></div>
								</span>
							</div>
						</div>

						<div class="form-group required">
							<label for="email" class="col-md-3 control-label">電子信箱</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="email"
									name="email" placeholder="Email" value="" /> <span
									class="help-block">
									<div class="text-danger"></div>
								</span>
							</div>
						</div>

						<div class="form-group required">
							<label for="password" class="col-md-3 control-label">設定密碼</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="password" value="" /> 
								<span class="help-block"><div class="text-danger"></div></span>
							</div>
						</div>
								
						<div class="form-group required">
							<label for="checkPassword" class="col-md-3 control-label">確認密碼</label>
							<div class="col-md-8">
								<input type="password" class="form-control" id="checkPassword"
									name="checkPassword" placeholder="checkPassword" value="" /> 
								<span class="help-block"><div class="text-danger"></div></span>
							</div>
						</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
			
			<div class=" col-sm-6" style="height: 60vh; padding-right: 0px; padding-left: 0px;">
				<!--右邊白 -->
				<div class="col-sm-1"></div>
				
				<div class="col-sm-10">
					<div class="panel-heading">
						<h3><span></span>&nbsp; <strong></strong></h3>
					</div>
								
					<div class="form-group required">
		    			<label for="birthdate" class="col-md-3 control-label">生日<p style="color: red;">(非必填)</p></label>
		    			<div class="col-md-8">
		      				<input type="text" class="form-control" id="birthdate" name="birthdate" placeholder="yyyy-mm-dd" value=""/>
		      				<span class="help-block"><div class="text-danger"></div></span>
		    			</div>
					</div>
								

					<div class="form-group required">
						<label for="gender" class="col-md-3 control-label">性別</label>
						<div class="col-md-8">
							<select id="gender" name="gender" class="form-control">
								<option value="female">女性</option>
								<option value="male">男性</option>
							</select>
							<span class="help-block"><div class="text-danger"></div></span>
						</div>
					</div>
								
					<div class="form-group required">
						<label for="tel" class="col-md-3 control-label">聯絡電話<p style="color: red;">(非必填)</p></label>
						<div class="col-md-8">
							<input type="tel" class="form-control" id="tel"
								name="tel" placeholder="+886" value="" /> 
							<span class="help-block"><div class="text-danger"></div></span>
						</div>
					</div>
								
					<div>
						<div class="col-md-3"></div>
						<div class="col-md-8">
							<a href="#"data-loading-text="Loading" id="saveButton">
							<img src="/resources/pic/registered/reg_btn.png" border="0" width="100%">
							</a>
						</div>	
						<div class="col-md-1 "></div>	
					</div>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="col-sm-12" style="height: 18vh"></div>
			</form>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>	

	<script tyep="text/javascript">	

		$(function() {		
			
			$('#birthdate').inputmask({
				  mask: '9999-99-99'
			});
			
			$('#tel').inputmask({
				  mask: '9999-999-999'
			});
			
			$('#email').on("change",function(){
				var email = $('#email').val();
				$.ajax({
					type : "GET",
					url : "<c:url value='/registered/check/" + email +"'/>",
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

			
			//<!-- Save -->	
			$("#saveButton").on("click",
					function() {
						var email = $("#email").val();
						var name = $("#name").val();
						if(email==""||name==""){
							swal('資料請輸入完整喔~~')
						}else{
							var $btn = $(this);
							$btn.button("loading");
							
							$.post("<c:url value='/registered'/>", "dataForm",
									function(data) {
										if (data.messages.length == 0) {
											//$("#dataForm").trigger("reset");
											//location.href = '/registered/checkLetter?email=' + data.data.email
											
											location.href = '/registered/checkLetter?id=' + data.data.id
											$btn.button("reset");
										}
									}, function(data, textStatus, jqXHR) {
										$btn.button("reset");

									});
						}
						$btn.button("reset");
					});
		})
	</script>

</body>
</html>