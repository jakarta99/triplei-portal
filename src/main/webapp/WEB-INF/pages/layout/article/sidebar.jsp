<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>

h4{
	margin-top:0;
	text-align:left;
	font-weight:normal;
	opacity: 0.8;
}

</style>
</head>
<body>
<div class="col-lg-3" style="background-color: #5C8DEC; height: 100%; overflow-y: auto;" id="first">
	<div  class="col-xs-1 col-sm-0"></div>
	<div class="col-xs-10 col-sm-12" style="text-align: center; margin-top: 7%; display: table">
	<br /> <br />
		<h2 class="col-md-12 col-sm-12" style="text-align:left;"><a href='/article/list' style="color:white;" id="firsth1">文章專欄</a></h2>
		<br/>
			<div class="col-md-12" style="padding-top:7%;height: 20vh;">
				<h4><a href='/article/editorChoice' style="color:white;">編輯精選</a></h4>
				<h4><a href='/article/news' style="color:white;">新聞專區</a></h4>
				<h4><a href='/article/goodRead' style="color:white;">小資族必讀</a></h4>
				<h4><a href='/article/investmentTips' style="color:white;">理財觀念</a></h4>
			</div>
		<br/>
		<br/>
		<br/>
	</div>
	<div class="col-xs-1 col-sm-0"></div>
</div>

<script>
	$(document).ready(function() {
		var wdth = $(window).width();
		if (wdth < 700) {
			$('#firsth1').css("margin-top", "7%");
			$('#first').css("height", "63vh");
		}
	});
</script>
</body>
</html>