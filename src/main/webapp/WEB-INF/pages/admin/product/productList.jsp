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
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div>
			<br/><br/><br/>
			
			<h3>保險商品管理</h3>
			<div>
          		<a href="<c:url value='/admin/product/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>上傳</a>
            	
      		</div>
			<div id="jsGrid"></div>
			
			<script>
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
			            { name: 'btns', width:60, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '公司ID', name: "insurer", type: "text", width: 60 },
			            { title: '產品名稱', name: "localName", type: "text", width: 200 },
			            { title: '商品代碼', name: "code", type: "text", width: 75 , validate: "required" },
			            { title: '年度代碼', name: "yearCode", type: "text", width: 75 },
			            { title: '年期', name: "year", type: "text", width: 50 },
			            { title: '宣告利率', name: "declareInterestRate", type: "text", width: 75 },
			            { title: '預定利率', name: "predictInterestRate", type: "text", width: 75 },
			            { title: '幣別', name: "curr", type: "text", width: 50 },
			            { title: '高保費率', name: "highDiscountRatios", type: "text", width: 75 },
			            { title: '基本費率', name: "premiumRatios", type: "radio", width: 75 },
			            { title: '解約金費率', name: "cancelRatios", type: "text", width: 85 },
			            { title: '繳費方式', name: "paymentMethod", type: "textarea", width: 75 },
			            { title: '保額', name: "insureAmount", type: "file", width: 100 },
			            { title: '保費', name: "premium", type: "file", width: 100 },
			            { title: '解約金', name: "cv;", type: "file", width: 100 },
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
					
					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
					$editBtn.attr("href", BASE_URL + "/" + row.id);
					$editBtn.append('<span class="glyphicon glyphicon-pencil"></span> 編輯');
					
					return $("<div></div>").append($editBtn).append("&nbsp;&nbsp;&nbsp;").append($delBtn);
				}
			    
			</script>
		</div>
	</div>

</body>
</html>