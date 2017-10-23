<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
#AskQuestion{
    position: fixed;  /*固定在網頁上不隨卷軸移動，若要隨卷軸移動用absolute*/
    top: 80%;  /*設置垂直位置*/
    right: 0px;  /*設置水平位置，依所放的內容多寡需要自行手動調整*/
    padding: 10px 20px;
/*     border-radius: 10px;   */
/*     -moz-border-radius: 10px; */
/*     -webkit-border-radius: 10px; */
}
#AskQuestion:hover{  /*當滑鼠移至此區塊時，伸縮區塊*/
    right: -5px;
}

</style>

<!-- Fixed navbar -->
<div id="AskQuestion">
<a href="/question/askQuestion">
<img alt="" id="questionlogo" src="/resources/pic/question.png" style="height : 8vh ; width: 8vh"></a>
</div>

<script>
$(document).ready(function() {
	var wdth = $(window).width();
	if (wdth < 700) {
		$("#AskQuestion").css("top","73%");
		$("#AskQuestion").css("right","-10px");
		$("#questionlogo").css("height","11vh");
		$("#questionlogo").css("width","11vh");
		
	}
	
});
</script>