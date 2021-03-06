<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i聯絡客服</title>
</head>

<body>

	<div class="container-fluid" style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div style="padding-top: 9vh">
			
<h3 style="text-align:center;margin-bottom: 5vh;">
									<strong>聯繫客服</strong>
								</h3>
					<form class="form-horizontal col-md-12" id="dataForm" style="width:85%;margin:0 auto; padding:auto">
								

								<div class="form-group required">
									<label for="questionType" class="col-md-2 control-label">問題分類</label>
									<div class="col-md-10">
										<select id="questionType" name="questionType"
											class="form-control" required>
<!-- 											<option value="" disabled selected>Select your -->
<!-- 												question</option> -->
											<option value="ARTICLE" selected >文章類問題</option>
											<option value="POLICY">保單類問題</option>
											<option value="CLAIMS">理賠問題</option>
											<option value="WEBUSE">網站使用及問題回報</option>
											<option value="SALES">業務人員問題</option>
										</select> <span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<!-- 								<div class="form-group required">								  					 -->
								<!-- 			    					<label for="questionType2" class="col-md-2 control-label">問題分類2</label> -->
								<!-- 			    					<div class="col-md-10"> -->
								<!-- 			      						<input type="text" class="form-control" id="questionType2" name="questionType2" placeholder="問題分類2" value=""/> -->
								<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
								<!-- 			    					</div> -->
								<!-- 								</div> -->

								<div class="form-group required">
									<label for="email" class="col-md-2 control-label">Email</label>
									<div class="col-md-10">
										<input type="email" class="form-control" id="askerEmail"
											name="askerEmail" placeholder="Enter Email" autocomplete="on" 
											value="" pattern='/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/' required/>
											 <span class="help-block"><div id="checkemail" class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="content" class="col-md-2 control-label">問題內容</label>
									<div class="col-md-10">
										<textarea id="content" name="content" class="form-control"
											rows="8" maxlength="100" required></textarea>
										<span class="help-block"><div id="checkcontent" class="text-danger"></div></span>
									</div>
								</div>

								<!-- 								<div class="form-group required"> -->
								<!-- 			    					<label for="postTime" class="col-md-2 control-label">提問時間</label> -->
								<!-- 			    					<div class="col-md-10"> -->
								<!-- 			      						<input type="text" class="form-control" id="postTime" name="postTime" placeholder="postTime" value=""/> -->
								<!-- 			      						<span class="help-block"><div class="text-danger"></div></span> -->
								<!-- 			    					</div> -->
								<!-- 								</div>	 -->

					</form>


			<div class="row col-sm-12" style="width: 84%; margin-left: 8%">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">提交問題</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
<%-- 						<a href="<c:url value='/index'/>" --%>
						<a href="http://localhost:8080"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		<div class="col-sm-12" style="height: 10vh"></div>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		//<!-- 新增問題  -->	
		$("#saveButton").bind(
				"click",
				function() {
					var $btn = $(this);
					$btn.button("loading");
				if(checkEmail()&&checklength()){
					swal("感謝您的提問，即將跳轉回首頁...");
					$.post("<c:url value='/question'/>", "dataForm",
							function(data) {
								if (data.messages.length == 0) {
									$("#dataForm").trigger("reset");
									//swal("SUCCESS", "資料新增成功！", "success");
// 									alert("Email已寄出");
									$btn.button("reset");
									location.replace('http://3i-life.com.tw:80');
								}
							}, function(data, textStatus, jqXHR) {
								$btn.button("reset");
							});
				}else{
					swal("請確認資料填寫無誤", "", "warning");
				}

					$btn.button("reset");
				});
	});

	//<!-- Email 驗證-->

	function validateEmail(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}

	function checkEmail() {
		$("#checkemail").text("");
		var email = $("#askerEmail").val();
		if (validateEmail(email)) {
			$("#checkemail").text(email + "可以使用");
			$("#checkemail").css("color", "green");
			return true;
		} else {
			$("#checkemail").text(email + " 請輸入正確的Email");
			$("#checkemail").css("color", "red");
		}
		return false;
	}

	$("#askerEmail").bind("blur", checkEmail);

	//<!-- 文章字數限制驗證 -->
// 	$(function() {
// 		$("#content").keyup(function() {
// 			checklength("content", 50);
// 		});
// 	});
// 	function checklength(content, maxlength) {
// 		if ($("#" + content).text().length > maxlength) {
// 			$("#" + content).text(
// 					$("#" + content).text().substring(0, maxlength));
// 			alert("已超過最大長度!");
// 			return false;
// 		}
// 		return true;
// 	}
	function checklength() {
		if ($("#content").text().length > 50) {
			$("#content").text(
					$("#content").text().substring(0, 50));
			swal("已超過最大長度!", "", "warning");
			return false;
		}
		return true;
	}
	
	$("#content").bind( "keyup" , checklength)
</script>

