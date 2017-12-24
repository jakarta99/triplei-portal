<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-111578916-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-111578916-1');
</script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/jquery/jquery-ui.1.11.2.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i商品詳細資料</title>
<script type="text/javascript" src='<c:url value="/resources/jquery/localization/jquery.ui.datepicker-zh-TW1.js" />'></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.symbol.js"></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.axislabels.js"></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.tooltip.min.js"></script>

<style>
label {
	padding: 0;
	margin: 0;
	display: inline;
}

.genderImage {
	width: 23%;
	border-radius: 50%;
}

.genderImage:hover {
	cursor: pointer;
}

#input1 div {
	margin-top: 3%;
}

#input1 span {
	font-size: 130%;
}

td {
	border: 1px black solid;
}

#simpleTable th {
	border: 1px black solid;
	background-color: #5C8DEC;
}

span {
	font-size: 100%;
}
#detailInfo div{
padding-bottom:1vh;
}
</style>
</head>

<body>

	<div id="wrap">
		<div class="container-fluid"
			style="width: 100%; height: 100%; position: absolute; padding: 0;">
			<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
			
			<div class="col-sm-12"
				style="padding: 0; width: 100%; height: 100%; color: white;padding-top:8vh">
				<div class="col-xs-12 col-sm-3"
					style="background-color: #5C8DEC; height: 100%; overflow-y: auto;">
					<div style="margin: 7% auto 0 auto; display: table; width: 70%;">
						<br /> <br />
						<span style="font-size:190%">商品內容</span>
						<br />
						<form id="input1" style="margin-top:10%">
							<div>
								<span>性別</span> <label> <input type="radio"
									class="gender FemaleInput" name="gender" value="Female"><img
									class="genderImage FemaleImg" data-toggle="tooltip" title="女生"
									alt="女" name="female" src="/resources/pic/product/female.png">
								</label> <label> <input type="radio" class="gender MaleInput"
									name="gender" value="Male"><img
									class="genderImage MaleImg" alt="男" data-toggle="tooltip"
									title="男生" name="male" src="/resources/pic/product/male.png">
								</label>
							</div>
							<div>
								<span>生日</span><br /> <input type="text" id="bDate"
									style="width: 80%; background-color: #5C8DEC; border: 1px white solid; margin-top: 2%;font-size:115%">
							</div>
							<div style="margin-top:8%">
								<span>保額(萬)</span><br /> <input type="text" id="insureAmount"
									name="insureAmount" value="<fmt:formatNumber value="${modelf.insureAmount}" maxFractionDigits="2"/>"
									style="width: 80%; color: white;border:1px white solid;background-color:#5C8DEC;margin-top:2%;font-size:115%">
							</div>
							<div style="margin-top: 3%; margin-bottom: 3%">
								<a id="changeID" href=""> <input type="button"
									class="btn btn-secondary" value="更改保額" id="changeAmount"
									style="border: 1px white solid; color: white; background-color: #5C8DEC;">
								</a>
							</div>
						</form>
						<div id="bm" style="width: 100%;position: relative;top: 20px;"></div>
					</div>
				</div>

				<div class="col-xs-12 col-sm-9"
					style="height: 100%; padding: 3%; color: black; overflow-y: scroll;">
					<br />
					<div id="productDetails" class="productDetails">
						<div class="col-sm-12" style="padding-bottom:1vh">
							<div class="col-sm-1" style="margin-bottom: 2vh">
								<img src="${modelf.insurer.imgsrc}" width="70vw">
							</div>
							<div class="col-sm-8" style="padding-left:2vw;padding-top:2vh">
								<span style="font-size: 120%;">${modelf.insurer.name}</span>
								<br /> <span style="font-weight: bold; font-size: 150%">${modelf.code}-${modelf.localName}</span>
							</div>
							<div class="col-sm-3" style="color: #5C8DEC">
								<span style="font-size: 150%">可獲得點數</span> <br />
								<div>
									<img src="/resources/pic/積點專區/點數(小).png" width="27vw"
										style="margin-top: 3%; margin-right: 3%; float: left"> 
										<span style="font-size: 200%; font-weight: bold"><fmt:formatNumber value="${modelf.getPoint}" type="number"/></span>
								</div>
							</div>
							<br /> <br /> <br /> <br />
						</div>

						<div class="col-sm-12"
							style="background-color: #5C8DEC; padding-bottom: 2%;color:white"
							align="center">
							<br />
							<div class="col-sm-1" style="padding-bottom:2vh"></div>
							<div class="col-sm-2" style="padding-bottom:2vh">
								<span>幣別</span> <br /> <span>${modelf.curr}</span>
							</div>
							<div class="col-sm-2" style="padding-bottom:2vh">
								<span>總繳金額</span> <br /> <span>$<fmt:formatNumber
										value="${modelf.totalPay}" maxFractionDigits="0" type="number" /></span>
							</div>
							<div class="col-sm-2" style="padding-bottom:2vh">
								<span>領回金額</span> <br /> <span>$<fmt:formatNumber
										value="${modelf.cashValue}" maxFractionDigits="0" type="number" /></span>
							</div>
							<div class="col-sm-2" style="padding-bottom:2vh">
								<span>凈報酬</span> <br /> <span>$<fmt:formatNumber
										value="${modelf.net}" maxFractionDigits="0" type="number" /></span>
							</div>
							<div class="col-sm-2" style="padding-bottom:2vh">
								<span>IRR</span> <br /> <span><fmt:formatNumber
										value="${modelf.irr*100}" maxFractionDigits="2" />%</span>
							</div>
							<div class="col-sm-1" style="padding-bottom:2vh"></div>
							<br /> <br />
						</div>

						<div id="detailInfo" class="col-sm-12">
							<hr />

							<div class="col-sm-3" style="padding-bottom:1vh">
								<span>保額：<fmt:formatNumber value="${modelf.insureAmount}" maxFractionDigits="2" /> 萬</span>
							</div>
							<div class="col-sm-4" style="padding-bottom:1vh">
								<span>保費折扣：<fmt:formatNumber value="${modelf.discount*100}"
										maxFractionDigits="2" />%
								</span>
							</div>
							<div class="col-sm-5" style="padding-bottom:1vh">
								<span>首年保費：$<fmt:formatNumber value="${modelf.premiumAfterDiscount}" type="number"/></span>
							</div>
							<div class="col-sm-3" style="padding-bottom:1vh">
								<c:if test="${modelf.year!=1}">
									<span>繳別：年繳</span>
								</c:if>
								<c:if test="${modelf.year==1}">
									<span>繳別：躉繳</span>
								</c:if>
							</div>
							<div class="col-sm-4" style="padding-bottom:1vh">
								<span>折扣前年繳保費：$<fmt:formatNumber value="${modelf.premium}" type="number"/></span>
							</div>
							<div class="col-sm-5" style="padding-bottom:1vh">
								<span>續年保費：$<fmt:formatNumber value="${modelf.premiumAfterDiscount}" type="number"/></span>
							</div>
							<div class="col-sm-3" style="padding-bottom:1vh">
							<c:if test="${modelf.interestRateType=='宣告利率'}">
								<span>${modelf.interestRateType}：<fmt:formatNumber value="${modelf.declareInterestRate*100}" maxFractionDigits="2" />%</span>
							</c:if>
							<c:if test="${modelf.interestRateType=='預定利率'}">
								<span>${modelf.interestRateType}：<fmt:formatNumber value="${modelf.predictInterestRate*100}" maxFractionDigits="2" />%</span>
							</c:if>
							</div>
							<div class="col-sm-4" style="padding-bottom:1vh">
								<span>折扣後年繳保費：</span><span id="getPremiumAfterDiscount">$<fmt:formatNumber value="${modelf.premiumAfterDiscount}" type="number"/></span>
							</div>
							<div class="col-sm-5" style="padding-bottom:1vh">
								<span>繳費方式：${modelf.paymentMethod}</span>
							</div>
							<br />
						</div>

						<div class="col-sm-12">
							<hr />
							<br />
							<div class="col-sm-10">
								<span>簡易試算表</span>
							</div>
							<div class="col-sm-2" style="text-align: right;">
								<input id="tableExpand" type="button" value="+"
									class="btn btn-secondary" style="background-color: white;">
							</div>
							<div class="col-sm-12 text-center">
								<div id="simpleTable">
									<div style="height: 30vh; overflow-y: scroll;">
										<table style="margin:0 auto;">
											<tr style="color:white;">
												<th class="col-sm-3 text-center">保單年度</th>
												<th class="col-sm-3 text-center">繳費金額</th>
												<th class="col-sm-3 text-center">總解約金/領回金額</th>
												<th class="col-sm-3 text-center">IRR</th>
											</tr>
											<c:forEach begin="0" items="${totalCancelRatio}" var="pay">
												<tr>
													<td class="col-sm-3"><fmt:formatNumber
															value="${pay[0]}" maxFractionDigits="0" /></td>
													<td class="col-sm-3"><fmt:formatNumber
															value="${pay[1]}" maxFractionDigits="0" /></td>
													<td class="col-sm-3"><fmt:formatNumber
															value="${pay[2]}" maxFractionDigits="0" /></td>
													<c:choose>
														<c:when test="${pay[3]>0.0}">
															<td class="col-sm-3"><fmt:formatNumber
																	value="${pay[3]*100}" maxFractionDigits="2" />%</td>
														</c:when>
														<c:otherwise>
															<td class="col-sm-3" style="color: red"><fmt:formatNumber
																	value="${pay[3]*100}" maxFractionDigits="2" />%</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<hr />
							<br />
							<div class="col-sm-10">
								<span>IRR走勢圖</span>
							</div>
							<div class="col-sm-2" style="text-align: right">
								<input type="button" id="IRRExpand" value="+" class="btn btn-secondary" style="background-color: white;">
							</div>
							<div id="IRRgraph" class="col-sm-12">
								<div id="flot-IRR" style="width: 65vw; height: 50vh;"></div>
							</div>
						</div>
						<hr />
						<br />
					</div>
					<br />
					<div class="col-sm-12" style="text-align: right;">
						<br /> <a href="#" id="iWantToBuy">
							<input type="button" id="iWantToBuy"
							class="btn btn-secondary iWantToBuy"
							style="color: white; background-color: #5C8DEC;" value="我想購買">
						</a> 
<!-- 						<a href="/recipient/add/1">我要購買</a> -->
					</div>
				</div>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
			</div>
		</div>
	</div>
	<input id="getProductId" value="${modelf.id}" style="visibility:hidden"/>
	<input id="scorePoints" value="${modelf.getPoint}" style="visibility:hidden"/>
	<input id="discountedPremium" value="${modelf.premiumAfterDiscount}" style="visibility:hidden"/>
	<script>
		 var dataIRR1= [
			 
				<c:forEach begin="0" var="item" items="${totalIrrAndCancelRatio}">
				[${item[0]},${item[1]}],
</c:forEach>
];
		  var dataIRR2= [
				<c:forEach begin="0" var="item" items="${totalIrrAndCancelRatio}">
				[${item[0]},parseFloat(${item[2]*100}).toFixed(2)],
</c:forEach>
       ];

				  $.plot($("#flot-IRR"),
				        [
				            {
				              data: dataIRR1,
				              label: "解約金",
				              points: { show: false},
				              lines: { show: true},
				            },
				            {
				              data: dataIRR2,
				              label: "IRR",
				              points: { show: false },
				              lines: { show: true},
				              yaxis: 2,
				              color:"#5C8DEC"
				            }
				        ],
				        {            
				            grid: {
				                backgroundColor: "white",
				                hoverable:true,
				                borderWidth: 1
				            },
				            legend: {
				            	noColumns:1,
				                labelBoxBorderColor: "none",
				                    position: "se",
				            },
				            tooltip:{
				            	show:true,
				            	content:"%s | %lx：%x | %ly：%y",
				            },
				            xaxis: {
				                axisLabel: "年期",
				                axisLabelUseCanvas: true,
				                axisLabelFontSizePixels: 15,
				                axisLabelFontFamily: "Arial",
				                axisLabelPadding: 10,
				                
				            },
				            yaxes: [
				                {
				                    /* First y axis */
				                	axisLabel: "解約金($)",
				                	axisLabelUseCanvas: true,
				                	axisLabelFontSizePixels: 15,
				                    axisLabelFontFamily: "Arial",
				                    axisLabelPadding: 10,
				                },
				                {
				                    /* Second y axis */
				                    axisLabel: "IRR(%)",
				                    axisLabelUseCanvas: true,
				                    axisLabelFontSizePixels: 15,
				                    axisLabelFontFamily: "Arial",
				                    axisLabelPadding: 10,
				                    position: "right",  /* left or right */
				                }
				            ]      
				        }
				    );
				    
				    
	
	var path = window.location.pathname;
	var yearCode = path.substring(path.lastIndexOf('/')+1);
	var birth = path.split("/")[4];
	var sex = path.split("/")[3];
	var totalInsure = path.split("/")[5];
	
	$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
	var date = new Date(birth);
	
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
		if(gender!="male"){
			$(".MaleImg").attr("src","/resources/pic/product/male.png");
		}else{
			$(".FemaleImg").attr("src","/resources/pic/product/female.png");
		}
	},function(){
		$(this).attr("src","/resources/pic/product/"+gender+".png");
	});
	
	$(".genderImage").on("click",function(){
		var gender = $(this).attr("name");
		$(".genderImage").off();
		
		$('[data-toggle="tooltip"]').tooltip("hide");
		$(".genderImage").on("click",function(){
			
			$(".MaleImg").attr("src","/resources/pic/product/male.png");
			$(".FemaleImg").attr("src","/resources/pic/product/female.png");
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
	
	
	
	
	
	if(sex!="Male"){
		$(".FemaleInput").prop("checked",true);
		$(".FemaleImg").attr("src","/resources/pic/product/female-hover.png");
	}else{
		$(".MaleInput").prop("checked",true);
		$(".MaleImg").attr("src","/resources/pic/product/male-hover.png");
	}
	
	$("#insureAmount").on("blur",function(){
	var url = "/product/Adjustment/"+$('input[name="gender"]:checked').val()+"/"+$('#bDate').val()+"/"+$("#insureAmount").val()+"/"+${modelf.id}+"/"+yearCode;
	$("#changeID").attr("href",url);
	})
	
	$(".FemaleInput").on("click",function(){
		var url = "/product/Adjustment/"+$('input[name="gender"]:checked').val()+"/"+$('#bDate').val()+"/"+$("#insureAmount").val()+"/"+${modelf.id}+"/"+yearCode;
		$("#changeID").attr("href",url);
	})
	
	$(".MaleInput").on("click",function(){
		var url = "/product/Adjustment/"+$('input[name="gender"]:checked').val()+"/"+$('#bDate').val()+"/"+$("#insureAmount").val()+"/"+${modelf.id}+"/"+yearCode;
		$("#changeID").attr("href",url);
	})
	
	$("#bDate").on("blur",function(){
		var url = "/product/Adjustment/"+$('input[name="gender"]:checked').val()+"/"+$('#bDate').val()+"/"+$("#insureAmount").val()+"/"+${modelf.id}+"/"+yearCode;
	})
	
	$("#iWantToBuy").on("click",function(){
		var productID = $("#getProductId").val();
		var birth = $("#bDate").val();
		var gender = $('input[name="gender"]:checked').val();
		var insureAmount = $("#insureAmount").val();
		var premiumAfterDiscount = $("#discountedPremium").val();
		var getPoint = $("#scorePoints").val();
		var url = "/recipient/add/"+productID+"/"+birth+"/"+gender+"/"+insureAmount+"/"+premiumAfterDiscount+"/"+getPoint;
		$(this).attr("href",url);	
		$(this).click();
	});
	
	window.onresize = function(event) {
		 $.plot($("#flot-IRR"),
			        [
			            {
			              data: dataIRR1,
			              label: "解約金",
			              points: { show: false},
			              lines: { show: true},
			              
			            },
			            {
			              data: dataIRR2,
			              label: "IRR",
			              points: { show: false },
			              lines: { show: true},
			              yaxis: 2,
			            }
			        ],
			        {            
			            grid: {
                            backgroundColor:"white",
			                hoverable:true,
			                borderWidth: 1
			            },
			            legend: {
			            	noColumns:1,
			                labelBoxBorderColor: "none",
			                    position: "se",
			            },
			            tooltip:{
			            	show:true,
			            	content:"%s | %lx：%x | %ly：%y",
			            },
			            xaxis: {
			                axisLabel: "年期",
			                axisLabelUseCanvas: true,
			                axisLabelFontSizePixels: 15,
			                axisLabelFontFamily: "Arial",
			                axisLabelPadding: 10,
			                
			            },
			            yaxes: [
			                {
			                    /* First y axis */
			                	axisLabel: "解約金($)",
			                	axisLabelUseCanvas: true,
			                	axisLabelFontSizePixels: 15,
			                    axisLabelFontFamily: "Arial",
			                    axisLabelPadding: 10,
			                },
			                {
			                    /* Second y axis */
			                    axisLabel: "IRR(%)",
			                    axisLabelUseCanvas: true,
			                    axisLabelFontSizePixels: 15,
			                    axisLabelFontFamily: "Arial",
			                    axisLabelPadding: 10,
			                    position: "right",  /* left or right */
			                }
			            ]      
			        }
			    );
    }
	var animation = bodymovin.loadAnimation({
		  container: document.getElementById('bm'),
		  renderer: 'svg',
		  loop: true,
		  autoplay: true,
		  path: '/resources/pic/product/商品專區動畫/data.json'
		})
	</script>
</body>
</html>


