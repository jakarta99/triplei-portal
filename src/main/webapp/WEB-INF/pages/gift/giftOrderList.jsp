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
<title>Triple i</title>

</head>

<body>
	
	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div style="padding-top:8vh">
			<h3>積點商品訂單</h3>
			
			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/gift/giftOrder";
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			 
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
			            { name: '訂單修改', width:75, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '積點商品名稱', name: "giftEntity.name", type: "text", width: 150 },
// 			            { title: '商品兌換點數', name: "bonus", type: "text", width: 150 },
			            { title: '數量', name: "quantity", type: "text", width: 150 },
			            { title: '兌換日期', name: "orderTime", type: "text", width: 150 },
// 			            itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD").toString();}
			            { title: '收件人姓名', name: "recipient", type: "text", width: 150 },
			            { title: '收件人地址', name: "recipientAddress", type: "text", width: 150 },
			            { title: '收件人電話', name: "recipientPhone", type: "text", width: 150 },
			            { title: '希望收件時間', name: "recipientTime", type: "text", width: 150 },
			            { title: '訂單處理狀態', name: "status", width: 150, itemTemplate:function(data){
			            	if(data=="PROCESSING"){
								return $("<span id='status'></span>").text("訂單處理中");	
							}else if(data=="SHIPORDER"){
								return $("<span id='status'></span>").text("出貨中");
							}else if(data=="DONE"){
								return $("<span id='status'></span>").text("處理完成");
							}else if(data=="CANCEL"){
								return $("<span id='status'></span>").text("退訂中");
							}else if(data=="CANCELDONE"){
								return $("<span id='status'></span>").text("退訂完成");
							} }}
     
			        ]
			    });
			    
			    function btns(value, row) {
					var $cancelBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$cancelBtn.append('<span class="glyphicon glyphicon-trash"></span> 取消訂單');
					
					$cancelBtn.click(function() {
						if (confirm('您確定要取消這筆訂單嗎?')) {
							$cancelBtn.button('loading');
							$.ajax({
								url:"<c:url value='/gift/giftOrder/orderCancel'/>"+ "/" + row.id, 
								method:"PUT",
								data:$('#status').text(),
								success:function(data) {
								if(data.訂單已取消){
								swal("訂單已取消","","success");
								}
								$cancelBtn.button('reset');
								$("#jsGrid").jsGrid("reset");
							}
							});
						}
					});
					
// 					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
// 					$editBtn.attr("href", BASE_URL + "/" + row.id);
// 					$editBtn.append('<span id="orderCancel" class="glyphicon glyphicon-pencil"></span> 取消訂單');
					
					return $("<div></div>").append($cancelBtn).append("<br/>");
// 					.append($editBtn)
					
			    
			    }
			     
// 			    $('#oederCancel').on(
// 			    	'click',
// 			    	function(){
// 			    		if($('#processing').text()=="訂單處理中"){
// 					    	swal({
// 					    		  title: "您確定要取消這筆訂單嗎?",
// 					    		  text: "點擊將進入取消訂單程序",
// 					    		  icon: "warning",
// 					    		  buttons: true,
// 					    		  dangerMode: true,
// 					    		})
// 					    		.then((willDelete) => {
// 					    		  if (willDelete) {
// 					    		    swal("即將幫您取消這筆訂單", {
// 					    		      icon: "success", 
// 					    		      $.ajax({
// 					    		    	  url : '<c:url value="/gift/giftOrder/orderCancel"/>', 
// 					    		    	  method : "POST",
// 					    		  		  data : datas,
// 					    		    	  success: function(){
// 					    		    		  location.href("/gift/giftOrder/list");
// 					    		    	  }
					    		    	  
// 					    		      });
					    		    
// 					    		    });
// 					    		  } else {
// 					    		  }
// 					    		});
// 					    }
			    		
			    		
// 			    	})
			    
			</script>

		</div>
	</div>
	
	
	
</body>
</html>
