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
<title>Triple i業務員訂單紀錄</title>

</head>
<body>
	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div style="padding-top:8vh">
			<h3>訂單紀錄</h3>
			<div class="text-center">			
			<h5>你是業務員</h5>
			</div>
			
			<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">

							<label for="orderNo" class="col-sm-1 control-label">訂單標號</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="orderNo"
									name="orderNo" placeholder="訂單標號" />
							</div>
							<label for="orderStatus" class="col-sm-1 control-label">訂單狀態</label>
							<div class="col-sm-2">
								<select id="orderStatus" name="orderStatus" class="form-control">
									<option value="">全部</option>
									<option value="未見面">未見面</option>
									<option value="已見面，未購買(刪除審核中點數)">已見面，未購買(刪除審核中點數)</option>
									<option value="已見面，已購買">已見面，已購買</option>
									<option value="保單處理中">保單處理中</option>
									<option value="已寄發保單">已寄發保單</option>
									<option value="已完成(含派送點數)">已完成(含派送點數)</option>
								</select>
							</div>

						</div>
						<div class="form-group">

							<label for="name" class="col-sm-1 control-label">聯絡人姓名</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="聯絡人姓名">
							</div>

						</div>
					</form>
				</div>
				<div class="row pull-right">
					<button id="searchBtn" class="btn btn-success"
						data-loading-text="loading..." type="button">搜尋</button>
					<button id="resetBtn" class="btn btn-warning"
						data-loading-text="loading..." type="button" value="reset">重設</button>
				</div>
			</section>
			<div id="jsGrid"></div>
			
			<script>
			$("#searchBtn").bind("click", function() {
				$("#jsGrid").jsGrid("reset");
			});

			$("#resetBtn").bind("click", function() {
				$("#listForm")[0].reset();
			});
			
			    var BASE_URL = "${pageContext.request.contextPath}/recipient";
			 	var userName = "<c:out value="${userName}"/>";
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
			                    url: BASE_URL +"/saless/"+userName,
			                    data: "pageIndex=" + filter.pageIndex + "&pageSize=10&"
								+ $("#listForm").serialize(),
			                    dataType: "json",
			                    cache: false,
			                });
			            },
			        },
			        
			 
			        fields: [
			            { name: '修改', width:60, itemTemplate:btns },
			            { title: '訂單標號', name: "orderNo", type: "text", width: 80 },
			            { title: '名字', name: "name", type: "text", width: 50 },
			            { title: '性別', name: "gender",width: 30, itemTemplate: function(val) {if(val=="Female"){return "女"}else{return "男"}} },
			            { title: '年齡', name: "age", type: "text", width: 30 },
			            { title: '公司名稱', name: "product.insurer.shortName", type: "text", width: 80 },
			            { title: '商品代碼', name: "product.code", type: "text", width: 50 },
			            { title: '商品名稱', name: "product.localName", type: "text", width: 100 },
			            { title: '保額', name: "insureAmount", width: 80, itemTemplate: function(val) {return val+"萬"}  },
			            { title: '電話', name: "tel", type: "text", width: 100 },
			            { title: '預約時段1', name: "bookedTime_1", type: "text", width: 90 },
			            { title: '預約時段2', name: "bookedTime_2", width: 90, itemTemplate: function(val) {if(val.endsWith("00")) {return val}else {return "無"}}  },
			            { title: '預約時段3', name: "bookedTime_3", width: 90, itemTemplate: function(val) {if(val.endsWith("00")) {return val}else {return "無"}}  },
			            { title: '超商', name: "convenienceStoreEntity.manufacturer", width: 60,itemTemplate: function(val) {if(val==null){return "無";}else{return val;}} },
			            { title: '超商名稱', name: "convenienceStoreEntity.storeName", width: 60, itemTemplate: function(val) {if(val==null){return "無";}else{return val+"門市";}} },
			            { title: '超商區域', name: "convenienceStoreEntity.region", width: 80,itemTemplate: function(val) {if(val==null){return "無";}else{return val;}} },
			            { title: '超商地址', name: "convenienceStoreEntity.address", width: 80,itemTemplate: function(val) {if(val==null){return "無";}else{return val;}} },
			            { title: '業務員', name: "user.name", type: "text", width: 80 },
			            { title: '訂單狀態', name: "orderStatus", type: "text", width: 90 },
			        ]
			    });
			    
			    function btns(value, row) {
					
					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
					$editBtn.attr("href", BASE_URL + "/sales/" + row.id);
					$editBtn.append('<span class="glyphicon glyphicon-pencil"></span> 編輯');
					
					
					if(row.orderStatus=='已完成(含派送點數)' || row.orderStatus=='已見面，未購買(刪除審核中點數)'){
							return "";
						}else{
							return $("<div></div>").append("<br/>").append($editBtn).append("<br/>");
						}
				}

			</script>
<c:import url="/WEB-INF/pages/layout/question.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>
</body>
</html>