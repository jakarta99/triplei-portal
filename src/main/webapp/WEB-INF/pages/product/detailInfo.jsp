<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<link rel="stylesheet" href="/resources/jquery/jquery-ui.1.11.2.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<script type="text/javascript" src='<c:url value="/resources/jquery/localization/jquery.ui.datepicker-zh-TW1.js" />'></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
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
#simpleTable th{
border:1px black solid;
background-color:#5C8DEC;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0;">
		<!--  -->
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div class="col-sm-12" style="padding:0;width:100%;height:100%;color:white;">
			<div class="col-sm-3" style="background-color:#5C8DEC;height:100%;overflow-y:auto;">
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
			
			<div class="col-sm-9" style="height:100%;padding:3%;color:black;overflow-y:scroll">
			<br/><br/><br/>
			<div id="productDetails" class="productDetails">
			<div class="col-sm-12">
			<div class="col-sm-2">
			<img src="/resources/pic/保險公司logo/三商美邦人壽.png" width="100%">
			</div>
			<div class="col-sm-7">
			<span style="font-size:120%">保險公司名稱</span>
			<br/><br/>
			<span style="font-weight:bold;font-size:150%">保險商品代號+全名</span>
			</div>
			<div class="col-sm-3" style="color:#5C8DEC">
			<span style="font-size:150%">可獲得點數</span>
			<br/>
			<div>
			<img src="/resources/pic/積點專區/點數(小).png" width="25em" style="margin-top:3%;margin-right:3%;float:left">
			<span style="font-size:200%;font-weight:bold">XXXX</span>
			</div>
			</div>
			<br/><br/><br/><br/>
			</div>
		
			<div class="col-sm-12" style="background-color:#5C8DEC;padding-bottom:2%" align="center">
			<br/>
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
			<br/><br/>
			</div>
			
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-3">
			<span>保額：XX</span>
			</div>
			<div class="col-sm-4">
			<span>保費折扣：X%</span>
			</div>
			<div class="col-sm-5">
			<span>首年保費：XX,XXX</span>
			</div>
			<div class="col-sm-3">
			<span>繳別：年繳</span>
			</div>
			<div class="col-sm-4">
			<span>折扣前年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-5">
			<span>續年保費：XX,XXX</span>
			</div>
			<div class="col-sm-3">
			<span>宣告利率：X.X%</span>
			</div>
			<div class="col-sm-4">
			<span>折扣後年繳保費：XX,XXX</span>
			</div>
			<div class="col-sm-5">
			<span>繳費方式：匯款或金融機構轉帳(1%折扣)</span>
			</div>
			<br/>
			</div>
			
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-10">
			<span>簡易試算表</span>
			</div>
			<div class="col-sm-2" style="text-align:right;">
			<input id="tableExpand" type="button" value="+" class="btn btn-secondary" style="background-color:white;">
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
			</div>
			<div class="col-sm-12">
			<hr/><br/>
			<div class="col-sm-10">
			<span>IRR走勢圖</span>
			</div>
			<div class="col-sm-2" style="text-align:right">
			<input type="button" id="IRRExpand" value="+" class="btn btn-secondary" style="background-color:white;" >
			</div>
			<div id="IRRgraph">
			<div id="chartContainer" style="height:300px;width:100%"></div>
			</div>
			</div>
			<hr/><br/>
			</div>
			<br/>
			<div class="col-sm-12" style="text-align:right;">
			<br/>
			<a href="/product/buyProduct/TWD/1/1/1" id="iWantToBuy">
			<input type="button" id="iWantToBuy" class="btn btn-secondary iWantToBuy" style="color:white;background-color:#5C8DEC;" value="我要購買">
			</a>
			<a href="/recipient/add/1">我要購買</a>
			</div>
			</div>
		</div>
		</div>
	</div>
	
	<script>
	new Morris.Line({
		  element: 'chartContainer',
		  data: [
			  	{ y: '0', a: 50, b: 70 },
			  	{ y: '1', a: 100, b: 90 },
			    { y: '2', a: 75,  b: 85 },
			    { y: '3', a: 50,  b: 40 },
			    { y: '4', a: 75,  b: 55 },
			    { y: '5', a: 50,  b: 50 },
			    { y: '6', a: 75,  b: 65 },
			    { y: '7', a: 100, b: 110 },
			    { y: '8', a: 50,  b: 40 },
			    { y: '9', a: 75,  b: 85 },
			    { y: '10', a: 95,  b: 90 },
			    { y: '11', a: 75,  b: 120 },
		  ],
		  xkey: 'y',
		  ykeys: ['a', 'b'],
		  labels: ['IRR', '解約金'],
		  numLines:'5',
		  resize:true,
		  parseTime:false,
		});
	
	$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
	$("#bDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	$("#bDate").datepicker('setDate',new Date());
	
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
	
// 		var currency = $().val();
// 		var insureAmount = $().val();
// 		var premium = $().val();
// 		var points = $().val();
// 		var url = "/product/buyProduct/"+currency+"/"+insureAmount+"/"+premium+"/"+points;
	
// 	$("#iWantToBuy").on("click",function(){
// 		$.get("/product/buyProduct/TWD/1/1/1");		
// 	});
	</script>
</body>
</html>

