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
width:23%;
border-radius:50%;
}
.genderImage:hover{
cursor:pointer;
}
#input1 div{
margin-top:3%;
}
#input1 span{
font-size:130%;
}
#input2 select{
width:70%;
height:120%;
}
#input2 span{
font-size:100%;
}
#right-side span{
font-size:100%;
color:#444444;
}
select option{
background-color:white;
}
select {
background-color:white;
}
.infoToggle div{
padding-bottom:2vh;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div style="padding:0;width:100%;height:100%;color:white;padding-top:9vh">
			<div class="col-sm-3" style="background-color:#5C8DEC;height:100%;overflow-y:auto;">
			<div style="margin:7% auto 0 auto; display:table;width:70%;">
				<br/><br/>
				<span style="font-size:190%;">商品專區</span>
				<br/>
				<form id="input1" style="margin-top:10%">
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
				<input type="text" id="bDate" style="width:80%;background-color:#5C8DEC;border:1px white solid;margin-top:2%;font-size:115%" >
				</div>
				<div style="margin-top:8%">
				<span>幣別</span><br/>
				<select id="currency" name="currency" style="border:1px white solid;width:80%;background-color:#5C8DEC;margin-top:2%;font-size:115%">
				<option value="TWD" selected>新台幣</option>
				<option value="USD">美金</option>
				<option value="RMB">人民幣</option>
				<option value="AUD">澳幣</option>
				</select>
				</div>
				</form>
				<div id="bm" style="width: 35vw;position: absolute;left: -70px;top: 360px"></div>
<!-- 				<div id="container-fluid" style="height:100%;position:relative;margin-top:15%"> -->
<!-- 				<div > -->
<!-- 				<img src="/resources/pic/product/商品專區動畫/images/img_1.png" width="100%;" style="width:90%;position:absolute;margin-top:4%;"> -->
<!-- 				</div> -->
<!-- 				<div > -->
<!-- 				<img src="/resources/pic/product/商品專區動畫/images/img_0.png" width="100%" style="width:30%;position:absolute;margin:23% auto auto 50%;"> -->
<!-- 				</div> -->
<!-- 				</div> -->
				</div>
			</div>
			
			<div id="right-side" class="col-sm-9" style="height:100%;padding-right:3%;padding-bottom:3%;padding-left:3%;color:black;overflow-y:scroll;">
			<br/><br/>
			<div>
			<form id="input2">
			<div class="col-sm-4" style="margin-bottom:2%">
			<span>存款方式</span><br/>
			<select id="paymentMethod">
			<option value="">請選擇</option>
			<option value="once">躉繳(一次繳清)</option>
			<option value="many">一般年期</option>
			</select>
			</div>
			<div class="col-sm-4" style="margin-bottom:2%;">
			<span>利率</span><br/>
			<select id="interestRateType">
			<option value="declareInterestRate">宣告利率</option>
			<option value="predictInterestRate">預定利率</option>
			</select>
			</div>
			<div class="col-sm-4" style="margin-bottom:2%;border:1px white solid">
			<span id="dollar">金額(新台幣／年)</span><br/>
			<span>$</span>
			<input type="text" id="premium" placeholder="50,000" style="width:70%;border:1px black solid">
			</div>
			<div class="col-sm-4">
			<span>繳費年期(年)</span><br/>
			<select id="year">
			<option value="">請選擇</option>
			</select>
			</div>
			<div class="col-sm-4" style="margin-bottom:2%;border:1px white solid">
			<span>預計領回時間(年)</span><br/>
			<input type="text" id="yearCode" placeholder="10" style="width:70%;border:1px black solid;">
			</div>
			<div class="col-sm-4">
			<br/>
			<input class="btn btn-secondary float-right productFilter" type="button" value="篩選" style="background-color:white;color:#5C8DEC;border:1px #5C8DEC solid;font-size:100%">
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
			swal("請輸入您的性別");
		}else if($('#bDate').val()==""){
			swal("請輸入您的生日");
		}else if($("#premium").val()==""){
			swal("請輸入您的繳費金額");
		}else if(isNaN($("#premium").val())){
			swal("輸入的繳費金額必須是數字");
		}else if(($("#premium").val()).indexOf('.') > -1){
			swal("輸入的繳費金額必須是整數");
		}else if($("#paymentMethod").val()==""){
			swal("請選擇存款方式");
		}else if($("#yearCode").val()==""){
			swal("請輸入您的預計領回時間");
		}else if($("#year").val()==""){
			swal("請輸入繳費年期");
		}else if(isNaN($("#yearCode").val())){
			swal("輸入的預計領回時間必須是數字");
		}else if(($("#yearCode").val()).indexOf('.') > -1){
			swal("輸入的預計領回時間必須是整數");
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
						installment="一般年期"
					}
					$("#products").append("<div style='display:table;background-color:white;width:100%;height:1em;padding-bottom:2%;'></div><div class='col-sm-12'  style='display:table;background-color:#FAF7F7;width:100%;' ><div class='col-sm-12'  style='padding-top:2%;'><div class='col-sm-8'><div class='col-sm-1'><img width='50vw' height='auto' src='"+row.insurer.imgsrc+"'></div><div class='col-sm-11' style='padding-left:2vw'><span style='font-size:150%'>"+row.insurer.name+"</span><a href='/insurer/list'><img width='15vw' height='auto' src='/resources/pic/product/info-icon.png'></a><br/><span style='font-size:125%;'>"+row.code+"-"+row.localName+"</span></div></div><div class='col-sm-3' ><div class='col-sm-8'><span style='color:#5C8DEC;'>可獲得點數:<br/>"+row.getPoint+"</span></div><div class='col-sm-4'><a href='/product/"+row.id+"/"+$('input[name="gender"]:checked').val()+"/"+$("#bDate").val()+"/"+$('#premium').val()+"/"+$('#yearCode').val()+"' id='interested'><input class='btn btn-secondary' name='interested' type='button' value='我有興趣' style='background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;'></a></div></div><div class='col-sm-1'></div></div><br/><div class='col-sm-12' style='margin-top:1%;margin-bottom:1%'><div class='col-sm-2'></div><div class='col-sm-2'><span>總繳金額</span><br/><span> $"+parseFloat(row.totalPay).toFixed(0)+"</span></div><div class='col-sm-2'><span>領回金額</span><br/><span> $"+parseFloat(row.cashValue).toFixed(0)+"</span></div><div class='col-sm-2'><span>凈報酬</span><br/><span> $"+parseFloat(row.net).toFixed(0)+"</span></div><div class='col-sm-2'><span>IRR</span><br/><span>"+parseFloat(row.irr*100).toFixed(2)+"%</span></div><div class='col-sm-2'><input class='btn btn-secondary float-right moreInfo' type='button' name='#id' style='background-color:#FAF7F7;color:#5C8DEC;border:1px #5C8DEC solid;' value='+'></div></div><div class='col-sm-12 infoToggle' style='padding-bottom:6vh;'><hr/><div class='col-sm-4'><span>保額："+row.insureAmount+" 萬</span></div><div class='col-sm-4'><span>繳費折扣：$"+row.discount+"</span></div><div class='col-sm-4'><span>續年保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>繳別："+installment+"</span></div><div class='col-sm-4'><span>折扣後年繳保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>"+row.interestRateType+"："+parseFloat(row.declareInterestRate*100).toFixed(2)+"%</span></div><div class='col-sm-4'><span>折扣前年繳保費：$"+row.premium+"</span></div><div class='col-sm-4'><span>首年保費：$"+row.premiumAfterDiscount+"</span></div><div class='col-sm-4'><span>繳費方式："+row.paymentMethod+"</span></div></div></div>");

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
	
	$("#currency").on("change",function(){
		var curr = $("#currency option:selected").text();
		var changeDollar= "金額("+curr+"／年)";
		$("#dollar").text(changeDollar);
	})
	
			var animation = bodymovin.loadAnimation({
			  container: document.getElementById('bm'),
			  renderer: 'svg',
			  loop: true,
			  autoplay: true,
			  path: '/resources/pic/product/商品專區動畫/product.json'
			})
	
	</script>
</body>
</html>

