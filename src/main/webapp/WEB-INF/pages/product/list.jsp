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
<style>
label{
padding:0;
margin:0;
display:inline;
}
.genderImage{
width:25%;
border-radius:50%;

}
.genderImage:hover{
cursor:pointer;
}
#input1 div{
margin-top:3%;
}
#input1 span{
font-size:150%;
}
#input2 select{
width:70%;
height:120%;
}
#input2 span{
font-size:100%;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;color:white;">
			<div class="col-sm-4" style="background-color:#5C8DEC;height:100%;overflow-y:auto;">
			<div style="margin:7% auto 0 auto; display:table;width:70%;">
				<br/><br/>
				<h1>商品專區</h1>
				<br/>
				<form id="input1">
				<div>
				<span>性別</span>
				<label>
				<input type="radio" class="gender" name="gender" value="Female"><img class="genderImage female" alt="女" name="female" src="/resources/pic/product/female.png">
				</label>
				<label>
				<input type="radio" class="gender" name="gender" value="Male"><img class="genderImage male" alt="男"  name="male" src="/resources/pic/product/male.png">
				</label>
				</div >
				<div>
				<span>生日</span><br/>
				<input type="text" id="bDate" style="width:80%;background-color:#5C8DEC;border:1px white solid;margin-top:2%" >
				</div>
				<div>
				<span>幣別</span><br/>
				<select id="currency" name="currency" style="width:80%;background-color:#5C8DEC;margin-top:2%;">
				<option value="TWD">新台幣</option>
				<option value="USD">美金</option>
				<option value="RMB">人民幣</option>
				<option value="AUD">澳幣</option>
				</select>
				</div>
				</form>
				<div id="container-fluid" style="height:100%;position:relative;">
				<div >
				<img src="/resources/pic/product/商品專區動畫/images/img_1.png" width="100%;" style="width:90%;position:absolute;margin-top:4%;">
				</div>
				<div >
				<img src="/resources/pic/product/商品專區動畫/images/img_0.png" width="100%" style="width:30%;position:absolute;margin:23% auto auto 50%;">
				</div>
				</div>
				</div>
			</div>
			
			<div class="col-sm-8" style="height:100%;padding:3%;color:black;overflow-y:scroll">
			<br/><br/>
			<div>
			<form id="input2">
			<div class="col-sm-4">
			<span>存款方式</span><br/>
			<select id="paymentMethod">
			<option value="1">躉繳</option>
			<option value="#">年繳</option>
			</select>
			</div>
			<div class="col-sm-4">
			<span>利率</span><br/>
			<select id="interestRateType">
			<option value="declareInterestRate">宣告利率</option>
			<option value="predictInterestRate">預定利率</option>
			</select>
			</div>
			<div class="col-sm-4">
			<span>金額(元／年)</span><br/>
			<input type="text" id="premium" placeholder="50,000" style="width:70%">
			</div>
			<div class="col-sm-4">
			<span>繳費年期(年)</span><br/>
			<select id="year">
			<option value="1">1</option>
			<c:forEach var="i" begin="5" end="30" step="5">
			<option value="${i}">${i}</option>
			</c:forEach>
			</select>
			</div>
			<div class="col-sm-4">
			<span>預計領回時間(年)</span><br/>
			<input type="text" id="yearCode" placeholder="10" style="width:70%">
			</div>
			<div class="col-sm-4">
			<br/>
			<input class="btn btn-secondary float-right productFilter" type="button" value="篩選" style="background-color:white;color:#5C8DEC;border:1px #5C8DEC solid;font-size:125%">
			</div>
			</form>
			</div>
			<br/>
			<div style="margin:10%"></div>
			<div id="products" class="products">
			<c:forEach var="p" begin="1" end="3">
			<div style="display:table;background-color:white;width:100%;height:1em;">
			</div>
			<div class="col-sm-12"  style="display:table;background-color:#FAF7F7;width:100%;" >
			<div class="col-sm-12">
			<div  style="padding-top:2%">
			<div class="col-sm-10">
			<img src="#">
			<span>--公司名稱--</span>
			</div>
			<div class="col-sm-2">
			<a href="/product/1" id="interested">
			<input class="btn btn-secondary float-right" name="interested" type="button" value="我有興趣" style="background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;">
			</a>
			</div>
			</div>
			<br/>
			<div style="margin-top:2%">
			<div class="col-sm-3">
			<span>--商品名稱--</span><br/>
			<span style="color:#5C8DEC">可獲得點數：XXXX</span>
			</div>
			<div class="col-sm-2">
			<span>總繳金額</span><br/>
			<span>$$$$$</span>
			</div>
			<div class="col-sm-2">
			<span>領回金額</span><br/>
			<span>$$$$$</span>
			</div>
			<div class="col-sm-2">
			<span>凈報酬</span><br/>
			<span>$$$$$</span>
			</div>
			<div class="col-sm-2">
			<span>IRR</span><br/>
			<span>%%</span>
			</div>
			<input class="btn btn-secondary float-right moreInfo" type="button" name="#id" class="col-sm-1" style="background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;" value="+">
			</div>
			<div class="infoToggle">
			<div class="col-sm-4">
			<span>保額：XX</span>
			</div>
			<div class="col-sm-4">
			<span>繳費折扣：XX</span>
			</div>
			<div class="col-sm-4">
			<span>續年保費：XX,XXX</span>
			</div>
			<div class="col-sm-4">
			<span>繳別：年繳</span>
			</div>
			<div class="col-sm-4">
			<span>折扣後年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-4">
			<span>宣告利率：XX%</span>
			</div>
			<div class="col-sm-4">
			<span>折扣前年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-4">
			<span>首年保費：XX,XXX</span>
			</div>
			<div class="col-sm-4">
			<span>繳費方式：轉帳</span>
			</div>
			</div>
			</div>
			</div>
			</c:forEach>
			</div>
			</div>
		</div>
		</div>
	</div>
	
	<script>
	$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
	$("#bDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	
	$(".gender").hide();
	var gender;
	$(".genderImage").hover(function(){
		gender = $(this).attr("name");
		$(this).attr("src","/resources/pic/product/"+gender+"-hover.png");
	},function(){
		$(this).attr("src","/resources/pic/product/"+gender+".png");
	});
	$(".genderImage").on("click",function(){
		var gender = $(this).attr("name");
		$(".genderImage").off();
		$(".genderImage").on("click",function(){
			$(".male").attr("src","/resources/pic/product/male.png");
			$(".female").attr("src","/resources/pic/product/female.png");
			$(this).attr("src","/resources/pic/product/"+$(this).attr("name")+"-hover.png");
		});
		
	})
	
	$(".infoToggle").hide();
	$(".moreInfo").on("click",function(){
		var moreInfoBtn = $(this).parent().siblings(".infoToggle");
		moreInfoBtn.toggle("drop",500);
	})
	
	$(".products").hide();
	$(".productFilter").on("click",function(){
		if($('input[name="gender"]:checked').val()==null){
			alert("請輸入您的性別");
		}else if($('#bDate').val()==""){
			alert("請輸入您的生日");
		}else if($("#premium").val()==""){
			alert("請輸入您的繳費金額");
		}else if(isNaN($("#premium").val())){
			alert("輸入的繳費金額必須是數字");
		}else if(($("#premium").val()).indexOf('.') > -1){
			alert("輸入的繳費金額必須是整數");
		}else if($("#yearCode").val()==""){
			alert("請輸入您的預計領回時間");
		}else if(isNaN($("#yearCode").val())){
			alert("輸入的預計領回時間必須是數字");
		}else if(($("#yearCode").val()).indexOf('.') > -1){
			alert("輸入的預計領回時間必須是整數");
		}else if($('input[name="gender"]:checked').val()!=null && $('#bDate').val()!="" && $("#premium").val()!="" &&$("#yearCode").val()!=""){
			$(".products").show();
			$.get("/product/getProduct/"+$('input[name="gender"]:checked').val()
					+"/"+$('#bDate').val()+"/"+$("#currency option:selected").val()
					+"/"+$("#paymentMethod option:selected").val()+"/"+$("#interestRateType option:selected").val()
					+"/"+$("#premium").val()+"/"+$("#year option:selected").val()+"/"+$("#yearCode").val(),function(value){
				alert(value);
			})	
			
		}
		
	})
	
	</script>
</body>
</html>

