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
		
		<div style="padding-top:9vh">
			<h3>保險商品管理</h3>
			<div>
          		<a href="<c:url value='/admin/product/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>上傳</a>
            	
      		</div>
      		<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">
		          
							<label for="localName" class="col-sm-1 control-label">商品名稱</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="localName" name="localName" placeholder="商品名稱" />
							</div>
						
						</div>
						<div class="form-group">
							<label for="Code" class="col-sm-1 control-label">商品代碼</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="code" name="code" placeholder="code"/>
							</div>
		          
							<label for="interestRateType" class="col-sm-1 control-label">利率別</label>
							<div class="col-sm-2">
								<select id="interestRateType" name="interestRateType" class="form-control">
									<option value="">請選擇</option>
									<option value="宣告利率">宣告利率</option>
									<option value="預定利率">預定利率</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="year" class="col-sm-1 control-label">年期</label>
							<div class="col-sm-5">
								<input type="number" class="form-control" id="year" name="year" placeholder="年期" value="0">
							</div>
		          
							<label for="curr" class="col-sm-1 control-label">幣別</label>
							<div class="col-sm-2">
								<select id="curr" name="curr" class="form-control">
									<option value="">請選擇</option>
									<option value="TWD">新台幣</option>
									<option value="USD">美金</option>
									<option value="AUD">澳幣</option>
									<option value="RMB">人民幣</option>
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

			    var BASE_URL = "${pageContext.request.contextPath}/admin/product";
			 
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
			 
			        controller: {
			            loadData: function () {
			                return $.ajax({
			                    type: "GET",
			                    url: BASE_URL,
			                    data: "pageIndex=1&pageSize=10&"+$("#listForm").serialize(),
			                    dataType: "json",
			                    cache: false
			                });
			            },
			        },
			        
			 
			        fields: [
			            { name: 'btns', width:60, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '公司', name: "insurer.shortName", type: "text", width: 60 },
			            { title: '產品名稱', name: "localName", type: "text", width: 200 },
			            { title: '商品代碼', name: "code", type: "text", width: 75 , validate: "required" },
			            { title: '年度代碼', name: "yearCode", type: "text", width: 75 },
			            { title: '年期', name: "year", type: "text", width: 50 },
			            { title: '宣告利率', name: "declareInterestRate", width: 75 ,itemTemplate: function(val) {return (val*100).toFixed(2)+"%" }},
			            { title: '預定利率', name: "predictInterestRate", width: 75 ,itemTemplate: function(val) {return (val*100).toFixed(2)+"%" }},
			            { title: '幣別', name: "curr", type: "text", width: 50 },
			            { title: '高保費率', name: "highDiscountRatios", width: 75 ,itemTemplate: function(val,row) {return $("<a></a>").attr("href",BASE_URL+"/highDiscountRatio/" + row.id).append("<button id='CR' style='font-size:10px'>檢視</button>")}},
			            { title: '基本費率', name: "premiumRatios", width: 75 ,itemTemplate: function(val,row) {return $("<a></a>").attr("href",BASE_URL+"/premiumRatio/" + row.id).append("<button id='CR' style='font-size:10px'>檢視</button>")}},
			            { title: '解約金費率', name: "cancelRatios", width: 80 ,itemTemplate: function(val,row) {return $("<a></a>").attr("href",BASE_URL+"/cancelRatio/" + row.id).append("<button id='CR' style='font-size:10px'>檢視</button>")}},
			            { title: '繳費方式', name: "paymentMethod", type: "textarea", width: 75 },
			            { title: '點數趴數', name: "bonusPoint", width: 75 ,itemTemplate: function(val) {return (val*100).toFixed(2)+"%" }},
			            { title: '保額', name: "insureAmount", visible: false},
			            { title: '保費', name: "premium", visible: false},
			            { title: '解約金', name: "cv;", visible: false},

			        ]
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('Are You Sure Want to Delete?')) {
							$delBtn.button('loading');
							$.delete_(BASE_URL+ "/" + row.id, function() {
								$delBtn.button('reset');
								$("#jsGrid").jsGrid("reset");
							});
						}
					});
					
// 					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
// 					$editBtn.attr("href", BASE_URL + "/" + row.id);
// 					$editBtn.append('<span class="glyphicon glyphicon-pencil"></span> 編輯');
					
					return $("<div></div>").append($delBtn);
				}
	    
			</script>
		</div>
	</div>

</body>
</html>