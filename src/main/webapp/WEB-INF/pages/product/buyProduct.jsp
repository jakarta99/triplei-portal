<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<link rel="stylesheet" href="/resources/jquery/jquery-ui.1.11.2.css">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<script type="text/javascript" src='<c:url value="/resources/jquery/localization/jquery.ui.datepicker-zh-TW1.js" />'></script>

</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<!--  -->
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;">
		<div class="col-sm-12">
		<h1>基本資料及聯絡資訊</h1>
		<form>
		<div class="col-sm-6">
		<div>
		<span>姓名</span><br/>
		<input type="text">
		<label>
		<input type="radio" class="btn btn-secondary gender" value="male">
		<span>先生</span>
		</label>
		<label>
		<input type="radio" class="btn btn-secondary gender" value="female">
		<span>小姐</span>
		</label>
		</div>
		
		
		</div>
		<div class="col-sm-6">
		
		</div>
		</form>
		</div>
		<div class="col-sm-12">
		<h1>確認保單資料</h1>
		<span style="color:grey">與業務員見面後也可更改</span>
		</div>
		</div>
		</div>
	</div>
	
	<script>
	
	
	</script>
</body>
</html>

