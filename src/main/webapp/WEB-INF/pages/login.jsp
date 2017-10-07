<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<style>
@CHARSET "UTF-8";

#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
/*
	form[role=login] input{
		font-size: 18px;
		margin: 16px 0;
	}
	
	form[role=login] > div {
		text-align: center;
	}

.form-links {
	text-align: center;
	margin-top: 1em;
	margin-bottom: 50px;
}
*/
</style>
</head>

<body>
	<div class="container-fluid"
		style="padding-right: 0px; padding-left: 0px;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
<!-- 		<div class="container" style="padding-top: 8vh"> -->
<!-- 			<div class="row"> -->
<div class="col-sm-12" style="height: 8vh"></div>
					<div class="col-sm-6"
						style="height: 100vh; display: table; table-layout: fixed; padding-right: 0px; padding-left: 0px;">
						<!-- 左邊白 -->
				<section class="login-form">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">

							<div class="panel-body col-sm-12">

								<div class="panel-heading">
									<h3>
										<span></span>&nbsp; <strong>會員登入</strong>
									</h3>
								</div>

								<form action="<c:url value="/auth/facebook" />" method="GET"
									role="form" name="fbForm" id="fbForm">
									<div class="col-sm-12" style="margin: 16px">
										<input type="hidden" name="scope" value="public_profile,email" />
										<!-- <input type="submit" value="">-->
										<button type="button" name="submit_Btn" id="submit_Btn"
											onClick="document.fbForm.submit()"
											style="background-color: white; height: 10vh; border: 2px blue none">
											<img src="/resources/pic/registered/fb_login.png"
												width="100%">
										</button>
									</div>
								</form>

								<form method="post"
									action="${pageContext.request.contextPath}/login" role="login"
									name="normalForm" id="normalForm">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />

									<!-- 
								<img class="fb" name="fb" src="/resources/pic/registered/fb.png" width="50%" style="margin:16px">
								 -->

									<p align="center" style="margin: 16px">or</p>

									<div class="col-sm-12" style="margin: 16px">
										<input type="text" name="username"
											placeholder="請輸入您註冊時的 e-mail" 
											required class="form-control input-md" />
									</div>

									<div class="col-sm-12" style="margin: 16px">
										<input type="password" name="password" placeholder="密碼"
											required class="form-control input-md" />
									</div>


									<div class="col-sm-12" style="margin: 16px">
										<div class="col-sm-6">
											<a href="/registered/forgetPassword"
												class="btn btn-lg  btn-block" id="forgetPassword">忘記密碼</a>
										</div>
										<div class="col-sm-6">
											<!-- <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">登入</button> -->
											<button type="button" name="go"
												onClick="document.normalForm.submit()"
												style="background-color: white; width: 200px; height: 40px; border: 2px blue none">
												<img src="/resources/pic/registered/login.png" width="60%">
											</button>
										</div>
									</div>
							</form>
							</div>

						</div>
						<div class="col-sm-1"></div>
			</section>
					</div>
<!-- 			</div> -->
<!-- 			<div class="row"> -->
				<div class=" col-sm-6  text-center "
					style="height: 100vh; background-color: #5C8DEC; padding-right: 0px; padding-left: 0px;">
					<!--右邊藍 -->
					<div class="col-*-1"></div>
					<div class="col-*-10"
						style="height: 100%; display: flex; justify-content: center; flex-direction: column"
						align='center'>
						<div class="panel-body">

							<!-- 動圖 -->
							<div class="col-*-12"
								style="height: 100%; display: flex; justify-content: center; flex-direction: column; padding-left: 0px; padding-right: 0px"
								align='center'>
								<div class="col-*-12" id="bm" style="height: 100%"></div>
							</div>

							<!-- 免費註冊 -->
							<div>
								<a href="/registered/add" data-loading-text="Loading"
									id="registered"> <img
									src="/resources/pic/registered/freeRegistered.png" border="0"
									width="50%"></a>
							</div>
						</div>
					</div>
					<div class="col-*-1"></div>
				</div>
<!-- 			</div> -->
<!-- 		</div> -->
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
	</div>

</body>
<script type="text/javascript">
	var animation = bodymovin.loadAnimation({
		container : document.getElementById('bm'),
		renderer : 'svg',
		loop : true,
		autoplay : true,
		path : '/resources/pic/registered/registered/1002/balloon.json'
	})
</script>
</html>
