<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<script type="text/javascript" src="<c:url value="/resources/jquery/moment.js"/>"></script>

<title>Triple i</title>

</head>

<body>
	
	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding-top:8vh ;">
			<div class="col-xs-12 col-sm-3" style="background-color:#5C8DEC; color:white;">
			
				<div class="col-xs-1 col-sm-1"></div>
				<div class="col-xs-10 col-sm-10" style="height: 100vh;padding-left: 0px;padding-right: 0px;">
				<div class="col-xs-12 col-sm-12" style="padding-top: 15vh;padding-bottom: 0vh">
				<span style="font-size: 2.5em;font-family: 微軟正黑體;">T-Point</span>
				<span style="font-size: 1.5em;font-family: 微軟正黑體;">積點專區</span>
				</div>
				<div class="col-xs-12 col-sm-12">
				<img alt="" src="/resources/pic/積點專區/點數(大).png" width="41" height="41">
				<span id="userPoint" style="font-size: 3.5em;vertical-align: middle;">${userPoint}</span>
				</div>
				<div class="col-xs-12 col-sm-12">
				<div class="col-xs-12 col-sm-12" style="height: 15vh;border: 1px white solid;border-radius: 20px;margin-top: 3vh;padding-left: 0px;padding-right: 0px;">
				<div class="col-xs-6 col-sm-6" style="border-right: 1px white solid;height: 80%;margin-top:5%;display: flex; justify-content: center; flex-direction: column" align="center">
				<p style="margin-top: 10%;margin-bottom: 10%;">審核中點數</p>
				<span id="audittingPoint" >${audittingPoint}</span>
				</div>
				<div class="col-xs-6 col-sm-6" style="height: 80%;margin-top:5%;display: flex; justify-content: center; flex-direction: column" align="center">
				<p style="margin-top: 10%;margin-bottom: 10%">已兌換點數</p>
				<span id="exchangedPoint" >${exchangedPoint}</span>
				</div>
				</div></div>
					<div class="col-xs-12 col-sm-12" id="bm" style="width: 140%;position: relative;top: 20px;left: -50px"></div>
				</div>
				<div class="col-xs-1 col-sm-1"></div>
			</div>
			
			<div class="col-sm-9" style="padding-top:8vh">
			<div id="jsGrid" style="overflow:auto"></div>
			</div>
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/gift/giftOrder";
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "80vh",
			 
			        inserting: false,
			        editing: false,
			        sorting: false,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 10,
			        pageLoading: true,
			        autoload: true,
			        pageNextText:"下一頁",
			        pagePrevText:"上一頁",
			        pageFirstText:"第一頁",
			        pageLastText:"最後一頁",
			 
			        controller: {
			            loadData: function (filter) {
			                return $.ajax({
			                    type: "GET",
			                    url: BASE_URL,
			                    data: filter,
			                    dataType: "json",
			                    cache: false
			                });
			            },
			        },
			        
			 
			        fields: [
						{ name: "id", visible: false},
			            { title: '積點商品名稱', name: "giftEntity.name", type: "text", width: 100 },
			            { title: '商品兌換點數', name: "giftEntity.bonus", type: "text", width: 100 },
			            { title: '數量', name: "quantity", type: "text", width: 50 },
			            { title: '兌換日期', name: "orderTime", type: "text", width: 90, itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD").toString();} },
			            { title: '收件人姓名', name: "recipient", type: "text", width: 100 },
			            { title: '收件人地址', name: "recipientAddress", type: "text", width: 250 },
			            { title: '收件人電話', name: "recipientPhone", type: "text", width: 100 },
			            { title: '希望收件時間', name: "recipientTime", type: "text", width: 100 },
			            { title: '訂單處理狀態', name: "status", width: 100, itemTemplate:function(data){
			            	if(data=="WAITING"){
								return $("<span id='status'></span>").text("已收到訂單");	
							}else if(data=="PROCESSING"){
								return $("<span id='status'></span>").text("訂單處理中");
							}else if(data=="SHIPORDER"){
								return $("<span id='status'></span>").text("配送中");
							}else if(data=="DONE"){
								return $("<span id='status'></span>").text("配送完成");
							}else if(data=="CANCELDONE"){
								return $("<span id='status'></span>").text("退訂完成");
							} }},
			            { title: '訂單修改',name: "status", width:85, itemTemplate:btns }
     
			        ]
			    });
			    
			    function btns(value, row) {
					var $cancelBtn ;
					var $editBtn ;
// 					|| value=="PROCESSING"
					if(value=="WAITING" ){
					$cancelBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$cancelBtn.empty();
					$cancelBtn.append('<span class="glyphicon glyphicon-trash"></span> 取消訂單');
			    	
					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
					$editBtn.empty();
					$editBtn.attr("href", BASE_URL + "/" + row.id);
					$editBtn.append('<span id="orderCancel" class="glyphicon glyphicon-pencil"></span> 修改訂單');
					
					}else{
			    	$cancelBtn = $('');
			    	$editBtn = $('');
			    	}
					$cancelBtn.click(function() {
						if (confirm('您確定要取消這筆訂單嗎?')) {
							$cancelBtn.button('loading');
							$.ajax({
								url:"<c:url value='/gift/giftOrder/orderCancel'/>"+ "/" + row.id, 
								method:"PUT",
								data:$('#status').text(),
								success:function(data) {
									if(data.訂單已取消){
										swal("訂單已取消","","success")
										.then((value) => {
										location.replace("/gift/giftOrder/list");
							    		});
									}
								$cancelBtn.button('reset');
								$("#jsGrid").jsGrid("reset");
								}
							});
						}
					});
					
					
					return $("<div></div>").append($editBtn).append("<br/>").append($cancelBtn).append("<br/>");
					
			    
			    }
			     
			</script>

<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>
	
	
	
</body>
</html>
