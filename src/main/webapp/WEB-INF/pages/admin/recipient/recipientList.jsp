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
		
		<div>
			<h3>訂單管理</h3>
			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/admin/recipient";
			 
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
			                    cache: false,
			                });
			            },
			        },
			        
			 
			        fields: [
			            { name: '刪／修', width:60, itemTemplate:btns },
			            { title: '名字', name: "name", type: "text", width: 50 },
			            { title: '性別', name: "gender", type: "text", width: 60 },
			            { title: '年齡', name: "age", type: "text", width: 30 },
			            { title: '公司名稱', name: "product.insurer.shortName", type: "text", width: 60 },
			            { title: '商品代碼', name: "product.code", type: "text", width: 50 },
			            { title: '商品名稱', name: "product.localName", type: "text", width: 100 },
			            { title: '保額', name: "product.insureAmount", type: "text", width: 50 },
			            { title: '電話', name: "tel", type: "text", width: 100 },
			            { title: '預約時段1', name: "bookedTime_1", type: "text", width: 90 },
			            { title: '預約時段2', name: "bookedTime_2", type: "text", width: 90 },
			            { title: '預約時段3', name: "bookedTime_3", type: "text", width: 90 },
			            { title: '超商名稱', name: "convenienceStoreEntity.storeName", type: "text", width: 80 },
			            { title: '超商區域', name: "convenienceStoreEntity.region", type: "text", width: 80 },
			            { title: '超商地址', name: "convenienceStoreEntity.address", type: "text", width: 80 },
			            { title: '業務員', name: "user.name", type: "text", width: 80 },
			            { title: '訂單狀態', name: "orderStatus", type: "text", width: 80 },
			            { title: '訂單標號', name: "id", type: "text", width: 80 },
			            { title: '會員帳號', name: "createdBy", type: "text", width: 200 },
			        ]
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('你確定要刪除這筆資料?')) {
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
					
					return $("<div></div>").append("<br/>").append($editBtn).append("<br/>").append($delBtn);
				}
			</script>
		</div>
	</div>
</body>
</html>