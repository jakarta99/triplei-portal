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
span{
font-size:110%;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;color:white;">
			<div class="col-sm-3" style="background-color:#5C8DEC;height:100%;overflow-y:auto;">
			<div style="margin:7% auto 0 auto; display:table;width:70%;">
				<br/><br/>
				<h1>商品專區</h1>
				<br/>
				<form id="input1">
				<div>
				<span>性別</span>
				<label>
				<input type="radio" class="gender" name="gender" value="Female"><img class="genderImage female" data-toggle="tooltip" title="女生" alt="女" name="female" src="/resources/pic/product/female.png">
				</label>
				<label>
				<input type="radio" class="gender" name="gender" value="Male"><img class="genderImage male" alt="男" data-toggle="tooltip" title="男生"  name="male" src="/resources/pic/product/male.png">
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
			
			<div class="col-sm-9" style="height:100%;padding:3%;color:black;overflow-y:scroll">
			<br/><br/>
			<div>
			<form id="input2">
			<div class="col-sm-4">
			<span>存款方式</span><br/>
			<select id="paymentMethod">
			<option value="">請選擇</option>
			<option value="once">躉繳</option>
			<option value="many">年繳</option>
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
			<option value="">請選擇</option>
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
		
			</div>
			</div>
		</div>
		</div>
	</div>
	
	<script>
	$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
	var date = new Date();
	date.setMonth(0,1);
	date.setYear(date.getYear()-30);
	
	$("#bDate").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd",
	}).datepicker('setDate',date);
	
	$(".gender").hide();
	var gender;
	$(".genderImage").hover(function(){
		gender = $(this).attr("name");
		$('[data-toggle="tooltip"]').tooltip(); 
		$(this).attr("src","/resources/pic/product/"+gender+"-hover.png");
	},function(){
		$(this).attr("src","/resources/pic/product/"+gender+".png");
	});
	$(".genderImage").on("click",function(){
		var gender = $(this).attr("name");
		$(".genderImage").off();
		
		$('[data-toggle="tooltip"]').tooltip("hide");
		$(".genderImage").on("click",function(){
			
			$(".male").attr("src","/resources/pic/product/male.png");
			$(".female").attr("src","/resources/pic/product/female.png");
			$(this).attr("src","/resources/pic/product/"+$(this).attr("name")+"-hover.png");
		});
		
	})
	
	$("#paymentMethod").on("change",function(){
		$("#year").empty();
		$("#year").append("<option value=''>請選擇</option>")
		if($("#paymentMethod option:selected").val()=="once"){
		$("#year").append("<option value='1'>1</option>");
		}else if($("#paymentMethod option:selected").val()=="many"){
			$.ajax({
				url:"/product/getYear",
				type:"GET",
				success:function(val){					
					val.sort();
					$.each(val,function(index,value){
						$("#year").append("<option value='"+value+"'>"+value+"</option>");
					})
				}
			})
		}
	})
	
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
		}else if($("#paymentMethod").val()==""){
			alert("請選擇存款方式");
		}else if($("#yearCode").val()==""){
			alert("請輸入您的預計領回時間");
		}else if($("#year").val()==""){
			alert("請輸入繳費年期");
		}else if(isNaN($("#yearCode").val())){
			alert("輸入的預計領回時間必須是數字");
		}else if(($("#yearCode").val()).indexOf('.') > -1){
			alert("輸入的預計領回時間必須是整數");
		}else if($('input[name="gender"]:checked').val()!=null && $('#bDate').val()!="" && $("#premium").val()!="" &&$("#yearCode").val()!=""){
			$("#products").show();
			$("#products").empty();
			$.get("/product/getProduct/"+$('input[name="gender"]:checked').val()
					+"/"+$('#bDate').val()+"/"+$("#currency option:selected").val()
					+"/"+$("#paymentMethod option:selected").val()+"/"+$("#interestRateType option:selected").val()
					+"/"+$("#premium").val()+"/"+$("#year option:selected").val()+"/"+$("#yearCode").val(),function(data){
				
				var val = eval(data.responseText);
				
				if(val!=null){
	 				$.each(val,function(index,value){
					var installment="";
					var row= val[index];
					if(row.year==1){
						installment="躉繳";
					}
					if(row.year!=1){
						installment="年繳"
					}
					$("#products").append("<div style='display:table;background-color:white;width:100%;height:1em;padding-bottom:2%;'></div><div class='col-sm-12'  style='display:table;background-color:#FAF7F7;width:100%;' ><div class='col-sm-12'  style='padding-top:2%'><div class='col-sm-2' style='padding:0'><img width='40%' style='display:block;margin:auto' src='"+row.insurer.imgsrc+"'></div><div class='col-sm-9' style='margin-top:1%'><span style='font-size:150%'>"+row.insurer.name+"</span><br/><span style='font-size:125%'>"+row.code+"-"+row.localName+"</span></div><div class='col-sm-1'><a href='/product/"+row.insurer.id+"/"+$('input[name="gender"]:checked').val()+"/"+$("#bDate").val()+"/"+$('#premium').val()+"/"+$('#yearCode').val()+"' id='interested'><input class='btn btn-secondary' name='interested' type='button' value='我有興趣' style='background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;'></a></div></div><br/><div class='col-sm-12' style='margin-top:1%;margin-bottom:1%'><div class='col-sm-3'><span style='color:#5C8DEC'>可獲得點數："+row.getPoint+"</span></div><div class='col-sm-2'><span>總繳金額</span><br/><span> $"+parseFloat(row.totalPay).toFixed(0)+"</span></div><div class='col-sm-2'><span>領回金額</span><br/><span> $"+parseFloat(row.cashValue).toFixed(0)+"</span></div><div class='col-sm-2'><span>凈報酬</span><br/><span> $"+parseFloat(row.net).toFixed(0)+"</span></div><div class='col-sm-2'><span>IRR</span><br/><span>"+parseFloat(row.irr*100).toFixed(2)+"%</span></div><div class='col-sm-1'><input class='btn btn-secondary float-right moreInfo' type='button' name='#id' style='background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;' value='+'></div></div><div class='col-sm-12 infoToggle' style='padding-bottom:1%;'><hr/><div class='col-sm-4'><span>保額："+row.insureAmount+" 萬</span></div><div class='col-sm-4'><span>繳費折扣：$"+row.discount+"</span></div><div class='col-sm-4'><span>續年保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>繳別："+installment+"</span></div><div class='col-sm-4'><span>折扣後年繳保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>"+row.interestRateType+"："+parseFloat(row.declareInterestRate*100).toFixed(2)+"%</span></div><div class='col-sm-4'><span>折扣前年繳保費：$"+row.premium+"</span></div><div class='col-sm-4'><span>首年保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>繳費方式："+row.paymentMethod+"</span></div></div></div>");

	 				})
	 				$(".infoToggle").hide();
		$(".moreInfo").on("click",function(){
			var moreInfoBtn = $(this).parent().parent().siblings(".infoToggle");
			moreInfoBtn.toggle("drop",500);
		})
				}
			})
		}
	})
	
	</script>
</body>
</html>

