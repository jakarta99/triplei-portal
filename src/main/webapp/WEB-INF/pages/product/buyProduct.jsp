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
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding:0;width:100%;height:100%;">
		<div class="col-sm-12" style="margin-top:4%;">
		<span style="font-size:150%">基本資料及聯絡資訊</span>
		<form>
		<div class="col-sm-6" style="border-right:1px lightgrey solid">
		<div style="width:85%">
		<div class="col-sm-12">
		<span>姓名</span>
		<br/>
		<input class="col-sm-8" type="text">
		<label class="col-sm-2">
		<input type="radio" class="btn btn-secondary gender" value="male">
		<span>先生</span>
		</label>
		<label class="col-sm-2">
		<input type="radio" class="btn btn-secondary gender" value="female">
		<span>小姐</span>
		</label>
		</div>
		<div class="col-sm-12">
		<span>手機號碼</span>
		<br/><br/>
		<input type="text" class="col-sm-2" placeholder="+886" style="margin-right:1%">
		<input type="text" class="col-sm-9" placeholder="0988-888-888">
		</div>
		<div class="col-sm-12">
		<br/>
		<span>有空時段1</span>
		<input class="col-sm-12 date" id="date1" type="text" placeholder="日期" >
		<br/><br/>
		<select id="session1" class="col-sm-3">
		<option value="morning">上午</option>
		<option value="afternoon">下午</option>
		<option value="evening">晚上</option>
		</select>
		<select id="shift1" class="col-sm-9">
		</select>
		<br/>
		</div>
		<div class="col-sm-12">
		<br/>
		<span>有空時段2</span>
		<input class="col-sm-12 date" type="text" placeholder="日期" >
		<br/><br/>
		<select id="session2" class="col-sm-3">
		<option value="morning">上午</option>
		<option value="afternoon">下午</option>
		<option value="evening">晚上</option>
		</select>
		<select id="shift2" class="col-sm-9">
		</select>
		<br/>
		</div>
		<div class="col-sm-12">
		<br/>
		<span>有空時段3</span>
		<input class="col-sm-12 date" type="text" placeholder="日期" >
		<br/><br/>
		<select id="session3" class="col-sm-3">
		<option value="morning">上午</option>
		<option value="afternoon">下午</option>
		<option value="evening">晚上</option>
		</select>
		<select id="shift3" class="col-sm-9">
		</select>
		<br/><br/><br/>
		</div>
		</div>
		</div>
		<div class="col-sm-6">
		<div class="col-sm-12" style="width:85%">
		<span>選擇地點</span>
		<br/>
		<div class="col-sm-12" style="padding:0">
		<select id="city" class="col-sm-6">
		<option value="Taipei" selected="selected">台北市</option>
		<option value="New Taipei City">新北市</option>
		</select>
		<select id="region" class="col-sm-6">
		</select>
		</div>
		<div class="col-sm-12" style="padding:0">
		<select id="address" class="col-sm-12">
		</select>
		</div>
		<div class="col-sm-12" style="padding:0">
		<select id="storeName" style="width:80%">
		</select>
		</div>
		</div>
		<br/><br/>
		<div id="map" class="col-sm-12">
		<!-- GoogleMap 放這 -->
		<img>
		</div>
		<div>
		
		</div>
		</div>
		</form>
		</div>
		<div class="col-sm-12">
		<span style="font-size:150%">確認保單資料</span>
		<span style="color:grey;margin-left:1%;">與業務員見面後也可更改</span>
		<div class="col-sm-12" style="border:1px solid lightgrey">
		<div class="col-sm-12" style="margin-bottom:2%;margin-top:1%;">
		<img src="">
		<span style="font-size:115%">保險公司</span>
		<br/>
		<span style="font-size:115%">保險商品編號+名稱</span>
		</div>
		
		<div class="col-sm-12" style="margin-bottom:4%;">
		<div class="col-sm-3" align="center">
		<span>幣別</span>
		<br/>
		<span>新台幣</span>
		</div>
		<div class="col-sm-3" align="center"  style="border-left:1px lightgrey solid">
		<span>保額</span>
		<br/>
		<span>XX,XXX</span>
		</div>
		<div class="col-sm-3" align="center" style="border-left:1px lightgrey solid">
		<span>每期保費</span>
		<br/>
		<span>XX,XXX</span>
		</div>
		<div class="col-sm-3" align="center" style="border-left:1px lightgrey solid">
		<span>可獲得點數</span>
		<br/>
		<span>X,XXX</span>
		</div>
		</div>
		</div>
		</div>
		</div>
		</div>
	</div>
	
	<script>
	$(".date").on("change",function(){
		$(this).attr("placeholder","");
	})
	$.datepicker.setDefaults($.datepicker.regional['zh-TW']);
	$(".date").datepicker({
		changeMonth : true,
		changeYear : true,
		yearRange : 'c:2+c',
		dateFormat : "yy-mm-dd"
	});
	$("#date1").datepicker('setDate',new Date());
	
	$("#session1").on("change",function(){
		var sessionVal = $("#session1 option:selected").val();
		var shift = $("#shift1");
		if(sessionVal=="morning"){
			shift.empty();
			shift.append("<option value='8'>08:00~09:00</option>");
			shift.append("<option value='9'>09:00~10:00</option>");
			shift.append("<option value='10'>10:00~11:00</option>");
			shift.append("<option value='11'>11:00~12:00</option>");
		}else if(sessionVal=="afternoon"){
			shift.empty();
			shift.append("<option value='12'>12:00~13:00</option>");
			shift.append("<option value='1'>13:00~14:00</option>");
			shift.append("<option value='2'>14:00~15:00</option>");
			shift.append("<option value='3'>15:00~16:00</option>");
			shift.append("<option value=‘4'>16:00~17:00</option>");
			shift.append("<option value=‘5'>17:00~18:00</option>");
		}else if(sessionVal=="evening"){
			shift.empty();
			shift.append("<option value=‘6'>18:00~19:00</option>");
			shift.append("<option value=‘7'>19:00~20:00</option>");
			shift.append("<option value=‘8'>20:00~21:00</option>");
			shift.append("<option value=‘9'>21:00~22:00</option>");
		}
	})
	$("#session2").on("change",function(){
		var sessionVal = $("#session2 option:selected").val();
		var shift = $("#shift2");
		if(sessionVal=="morning"){
			shift.empty();
			shift.append("<option value='8'>08:00~09:00</option>");
			shift.append("<option value='9'>09:00~10:00</option>");
			shift.append("<option value='10'>10:00~11:00</option>");
			shift.append("<option value='11'>11:00~12:00</option>");
		}else if(sessionVal=="afternoon"){
			shift.empty();
			shift.append("<option value='12'>12:00~13:00</option>");
			shift.append("<option value='1'>13:00~14:00</option>");
			shift.append("<option value='2'>14:00~15:00</option>");
			shift.append("<option value='3'>15:00~16:00</option>");
			shift.append("<option value=‘4'>16:00~17:00</option>");
			shift.append("<option value=‘5'>17:00~18:00</option>");
		}else if(sessionVal=="evening"){
			shift.empty();
			shift.append("<option value=‘6'>18:00~19:00</option>");
			shift.append("<option value=‘7'>19:00~20:00</option>");
			shift.append("<option value=‘8'>20:00~21:00</option>");
			shift.append("<option value=‘9'>21:00~22:00</option>");
		}
	})
	$("#session3").on("change",function(){
		var sessionVal = $("#session3 option:selected").val();
		var shift = $("#shift3");
		if(sessionVal=="morning"){
			shift.empty();
			shift.append("<option value='8'>08:00~09:00</option>");
			shift.append("<option value='9'>09:00~10:00</option>");
			shift.append("<option value='10'>10:00~11:00</option>");
			shift.append("<option value='11'>11:00~12:00</option>");
		}else if(sessionVal=="afternoon"){
			shift.empty();
			shift.append("<option value='12'>12:00~13:00</option>");
			shift.append("<option value='1'>13:00~14:00</option>");
			shift.append("<option value='2'>14:00~15:00</option>");
			shift.append("<option value='3'>15:00~16:00</option>");
			shift.append("<option value=‘4'>16:00~17:00</option>");
			shift.append("<option value=‘5'>17:00~18:00</option>");
		}else if(sessionVal=="evening"){
			shift.empty();
			shift.append("<option value=‘6'>18:00~19:00</option>");
			shift.append("<option value=‘7'>19:00~20:00</option>");
			shift.append("<option value=‘8'>20:00~21:00</option>");
			shift.append("<option value=‘9'>21:00~22:00</option>");
		}
	})


	</script>
</body>
</html>

