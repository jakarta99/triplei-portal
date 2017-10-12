<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-xs-12 col-sm-12" style="height: 18vh;background-color: #030033;margin: 0px;color: white;padding-left: 0px;padding-right: 0px" id="footerbar">
<div class="col-xs-3 col-sm-5" style="border-right: 1px white solid;margin-top: 5.5vh;height: 5vh;padding-left: 0px;padding-right: 0px'" align="right" >
<a href="https://www.facebook.com/triplei.triplei/" target="_blank"><img src="/resources/pic/Footer/fb.png" border="0" style="height: 5vh"></a>
<a href="/question/askQuestion"><img src="/resources/pic/Footer/mail.png" border="0" style="height: 5vh;margin-left: 0px"></a>
</div>
<div class="col-xs-9 col-sm-7" style="padding-top: 5vh;font-family: 微軟正黑體;padding-right: 0px;font-size: 2vh" id="footerfont">
<!-- FB:https://www.facebook.com/triplei.triplei/<br> -->
<!-- email:triplei@3i-life.com.tw<br> -->
睿實科技股份有限公司<br>
© 2017 triplei Corporation.版權所有
</div>
</div>


<script>
$(document).ready(function() { 
	if ($(window).width() < 700) { 
		$('#footerbar').find('img').css("height", "4vh");
		$('#footerfont').css("font-size", "1vh");
 	} 
 }); 
</script> 