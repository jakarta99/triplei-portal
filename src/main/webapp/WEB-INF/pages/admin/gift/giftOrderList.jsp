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
		
		<div style="padding-top:8vh">
			<h3>積點商品訂單管理</h3>
			
			<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">
							<label for="Status" class="col-sm-1 control-label">訂單狀態</label>
							<div class="col-sm-2">
								<select id="status" name="status" class="form-control">
									<option value="">全部</option>
									<option value="PROCESSING">訂單處理中</option>
									<option value="SHIPORDER">出貨中</option>
									<option value="DONE">處理完成</option>
									<option value="CANCEL">退訂中</option>
									<option value="CANCELDONE">退訂完成</option>
								</select>
							</div>
						</div>							
					</form>
				</div>
				<div class="row pull-right">
					<button id="searchBtn" class="btn btn-success" data-loading-text="loading..." type="button">搜尋</button>
		          		<button id="resetBtn" class="btn btn-warning" data-loading-text="loading..." type="button" value="reset">重設</button>
				</div>
			</section>
			
			<div id="jsGrid"></div>
			
			<script>
			$("#searchBtn").bind("click",function(){
				$("#jsGrid").jsGrid("reset");
			});
			
			$("#resetBtn").bind("click", function(){
				$("#listForm")[0].reset();
			});
			
			    var BASE_URL = "${pageContext.request.contextPath}/admin/gift/giftOrder";
			 
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
			 
			        controller: {
			            loadData: function (filter) {
			                return $.ajax({
			                    type: "GET",
			                    url: BASE_URL,
			                    data: "pageIndex=" + filter.pageIndex + "&pageSize=10&" + $("#listForm").serialize(),
			                    dataType: "json",
			                    cache: false
			                });
			            },
			        },
			        
			 
			        fields: [
			            { name: '訂單修改', width:60, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '積點商品名稱', name: "giftEntity.name", type: "text", width: 100 },
			            { title: '商品兌換點數', name: "giftEntity.bonus", type: "text", width: 100 },
			            { title: '數量', name: "quantity", type: "text", width: 50 },
			            { title: '兌換日期', name: "orderTime", type: "text", width: 75, itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD").toString();}},
			            { title: '收件人姓名', name: "recipient", type: "text", width: 150 },
			            { title: '收件人地址', name: "recipientAddress", type: "text", width: 150 },
			            { title: '收件人電話', name: "recipientPhone", type: "text", width: 150 },
			            { title: '希望收件時間', name: "recipientTime", type: "text", width: 150 },
			            { title: '訂購會員帳號', name: "createdBy", type: "text", width: 200 },
// 			            { title: '訂購會員email', name: "userEntity.email", type: "text", width: 150 },
			            { title: '訂單處理狀態', name: "status", width: 100, itemTemplate:function(data){
			            	if(data=="WAITING"){
								return $("<span></span>").text("已收到訂單");	
							}else if(data=="PROCESSING"){
								return $("<span></span>").text("訂單處理中");
							}else if(data=="SHIPORDER"){
								return $("<span></span>").text("配送中");
							}else if(data=="DONE"){
								return $("<span></span>").text("配送完成");
							}else if(data=="CANCELDONE"){
								return $("<span></span>").text("退訂完成");
							} }}

			        ]
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('Are You Sure Want to Delete?')) {
							$delBtn.button('loading');
							$.delete_(BASE_URL+ "/" + row.id, function(data) {
								if(data.刪除成功){
									swal("刪除成功","","success");
								}else if(data.exception){
									swal("非常抱歉", "修改未成功", "warning")
								}
								$delBtn.button('reset');
								$("#jsGrid").jsGrid("reset");
							});
						}
					});
					
					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
					$editBtn.attr("href", BASE_URL + "/" + row.id);
					$editBtn.append('<span class="glyphicon glyphicon-pencil"></span> 編輯');
					
					return $("<div></div>").append($editBtn).append("<br/>").append($delBtn);
				}

			</script>

		</div>
	</div>
	
	
	
</body>
</html>
