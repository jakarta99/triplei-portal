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

body {
	background: url(http://i.imgur.com/GHr12sH.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
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
</style>
</head>

<body>
<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	<section class="container">
	
			<section class="login-form">
				<form method="post" action="${pageContext.request.contextPath}/login" role="login">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" />
					<input type="text" name="username" placeholder="account" required class="form-control input-lg" />
					<input type="password" name="password" placeholder="Password" required class="form-control input-lg" />
					<button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Sign in</button>
					<div>
						<a href="/registered/add">Create account</a> or <a href="/registered/forgetPassword">forget password</a>
					</div>
				</form>

				<div class="form-links">
					<a href="${pageContext.request.contextPath}/">www.triple-i.com.tw</a>
					<div class="bg-danger">
						<ul>
							<li> admin / admin1234 for Administrator Testing</li>
							<li> user / user1234 for User Testing </li>
						</ul>
					</div>
				</div>
			</section>
	</section>

</body>
</html>