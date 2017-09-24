<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<style>

@CHARSET "UTF-8";
/*
.progress-bar {
    color: #333;
} 


* {
    -webkit-box-sizing: border-box;
	   -moz-box-sizing: border-box;
	        box-sizing: border-box;
	outline: none;
}

    .form-control {
	  position: relative;
	  font-size: 16px;
	  height: auto;
	  padding: 10px;
		@include box-sizing(border-box);

		&:focus {
		  z-index: 2;
		}
	}



.login-form {
	margin-top: 60px;
}

form[role=login] {
	color: #5d5d5d;
	background: #f2f2f2;
	padding: 26px;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}
	form[role=login] img {
		display: block;
		margin: 0 auto;
		margin-bottom: 35px;
	}
	form[role=login] input,
	form[role=login] button {
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
	.form-links a {
		color: #fff;
	}
*/	
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
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
</style>
</head>

<body>
<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<section class="container">
	
			<section class="login-form">

			<div class="col-sm-12">
				</br>
				</br>
				
				<div class="col-sm-6">
					<div style="height:100%;">
					<form method="post" action="${pageContext.request.contextPath}/login" role="login">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<h2>會員登入</h2>
						<br>
						<!-- <img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" /> -->
						<img class="fb" name="fb" src="/resources/pic/registered/fb.png" width="50%">
						<br><br>
						<p align="center"> or </p>
						<div class="col-sm-12">
							<input type="text" name="username" placeholder="請輸入您註冊時的 e-mail"
								required class="form-control input-md" />
						</div>

						<div class="col-sm-12">
							<input type="password" name="password" placeholder="密碼" required
								class="form-control input-md" />
						</div>
						</br>
						
						<div class="col-sm-12">
							<div class="col-sm-6">
								<a href="/registered/forgetPassword" class="btn btn-lg  btn-block" id="forgetPassword">忘記密碼</a>
							</div>
							<div class="col-sm-6">
								<button type="submit" name="go" class="btn btn-lg btn-primary btn-block">登入</button>
							</div>
						</div>
						
					</form>
					</div>
				</div>

				</br>
				</br>

				<div class="col-sm-6" style="height:100%;overflow-y:auto;text-align:center">
					<div>
						<img class="hotBalloon" name="hotBalloon" src="/resources/pic/registered/registered/images/img_1.png" width="10%">
						<img class="joinUs" name="joinUs" src="/resources/pic/registered/registered/images/img_2.png" width="30%">
					</div>
					<div>
						<img class="mountain" name="mountain" src="/resources/pic/registered/registered/images/img_0.png" width="60%">
					</div>
						
					</br>
					<div>
						<a href="/registered/add" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="registered">免費註冊</a>
					</div>
						
				</div>
			</div>

		</section>
	</section>

</body>
</html>
<footer>
<!-- 
	<div class="form-links">
		<a href="${pageContext.request.contextPath}/">www.triple-i.com.tw</a>
	</div>
 -->	
</footer>