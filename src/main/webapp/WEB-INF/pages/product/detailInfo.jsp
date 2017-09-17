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
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
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
td{
border:1px black solid;

}
th{
border:1px black solid;
background-color:#5C8DEC;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<!--  -->
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;color:white;">
			<div class="col-sm-4" style="background-color:#5C8DEC;height:100%;">
			<div style="margin:7% auto 0 auto; display:table;width:70%;">
				<br/><br/>
				<h1>商品專區</h1>
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
				<span>保額(萬)</span><br/>
				<input type="text" id="insureAmount" name="insureAmount" placeholder="40" style="width:80%;color:black;">
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
			
			<div class="col-sm-7" style="height:100%;padding:3%;color:black;overflow-y:scroll">
			<br/><br/><br/>
			<div id="productDetails" class="productDetails">
			<div class="col-sm-2">
			<img src="companyIMG">
			</div>
			<div class="col-sm-7">
			<span>保險公司名稱</span>
			<br/>
			<span>保險商品代號+全名</span>
			</div>
			<div class="col-sm-3" style="color:#5C8DEC">
			<span>可獲得點數</span>
			<br/>
			<img src="點數icon">
			<span>XXXX</span>
			</div>
			<div class="col-sm-12">
			<div class="col-sm-3">
			<span>總繳金額</span>
			<br/>
			<span>XXX,XXX</span>
			</div>
			<div class="col-sm-3">
			<span>領回金額</span>
			<br/>
			<span>X,XXX,XXX</span>
			</div>
			<div class="col-sm-3">
			<span>凈報酬</span>
			<br/>
			<span>XXX,XXX</span>
			</div>
			<div class="col-sm-3">
			<span>IRR</span>
			<br/>
			<span>X.XX%</span>
			</div>
			<br/>
			</div>
			
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-2">
			<span>保額：XX</span>
			</div>
			<div class="col-sm-4">
			<span>保費折扣：X%</span>
			</div>
			<div class="col-sm-6">
			<span>首年保費：XX,XXX</span>
			</div>
			<div class="col-sm-2">
			<span>繳別：年繳</span>
			</div>
			<div class="col-sm-4">
			<span>折扣前年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-6">
			<span>續年保費：XX,XXX</span>
			</div>
			<div class="col-sm-2">
			<span>宣告利率：X.X%</span>
			</div>
			<div class="col-sm-4">
			<span>折扣後年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-6">
			<span>繳費方式：匯款或金融機構轉帳(1%折扣)</span>
			</div>
			<br/>
			</div>
			
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-10">
			<span>簡易試算表</span>
			</div>
			<div class="col-sm-2">
			<input id="tableExpand" type="button" value="+" class="btn btn-secondary" style="background-color:white">
			</div>
			<div class="col-sm-12 text-center">
			<div id="simpleTable">
			<table>
			<tr>
			<th class="col-sm-3 text-center">保單年度</th>
			<th class="col-sm-3 text-center">繳費金額</th>
			<th class="col-sm-3 text-center">總解約金/領回金額</th>
			<th class="col-sm-3 text-center">IRR</th>
			</tr>
			<c:forEach var="i" begin="1" end="10">
			<tr>
			<td  class="col-sm-3">${i}</td>
			<td class="col-sm-3">XX,${i}XX</td>
			<td class="col-sm-3">X${i},XXX</td>
			<c:choose>
			<c:when test="${-1.0>0.0}">
			<td class="col-sm-3">1.0%</td>
			</c:when>
			<c:otherwise>
			<td class="col-sm-3" style="color:red">-1.0%</td>
			</c:otherwise>
			</c:choose>
			</tr>
			</c:forEach>
			</table>
			</div>
			</div>
			<br/>
			</div>
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-10">
			<span>IRR走勢圖</span>
			</div>
			<div class="col-sm-2">
			<input type="button" id="IRRExpand" value="+" class="btn btn-secondary" style="background-color:white">
			</div>
			<div id="IRRgraph"  class="col-sm-12">
			<div id="chartContainer" class="col-sm-12" style="width:100%;display:block;"></div>
			</div>
			<br/><hr/>
			</div>
			<br/>
			</div>
			<div class="col-sm-12" style="text-align:right;">
			<input type="button" class="btn btn-secondary iWantToBuy" style="color:white;background-color:#5C8DEC;" value="我要購買">
			<a href="/recipient/add/1">我要購買</a>
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
	
	$("#simpleTable").hide();
	$("#tableExpand").on("click",function(){
		$("#simpleTable").toggle("drop",1000);
	})
	
	$("#IRRgraph").hide();
	$("#IRRExpand").on("click",function(){
		$("#IRRgraph").toggle("drop",1000);
	})
	
	/*CanvasJS free license, Search for another charts plugin */
	var options = {
		title: {
			text: "IRR走勢圖"
		},
                animationEnabled: true,
		data: [
		{
			type: "spline",  
	        name: "IRR",        
	        showInLegend: true,
	        dataPoints: [
	        {label: "1" , y: 20} ,     
	        {label:"2", y: 13} ,     
	        {label: "3", y: 13} ,     
	        {label: "4", y: 16} ,     
	        {label: "5", y: 11}              
	        ]
		},{        
	        type: "spline",  
	        name: "解約金",        
	        showInLegend: true,
	        dataPoints: [
	        {label: "1" , y: 7} ,     
	        {label:"2", y: 8} ,     
	        {label: "3", y: 9} ,     
	        {label: "4", y: 13} ,     
	        {label: "5", y: 13}              
	        ]
	      },
		]
	};

	$("#chartContainer").CanvasJSChart(options);
	

	</script>
</body>
</html>

