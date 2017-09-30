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
			<h3>訂單紀錄</h3>
			<div id="jsGrid"></div>
			
			<script>
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
			                    url: BASE_URL +"/"+userName,
			                    data: filter,
			                    dataType: "json",
			                    cache: false,
			                });
			            },
			        },
			        
			 
			        fields: [
			            { title: '訂單標號', name: "orderNo", type: "text", width: 60 },
			            { title: '名字', name: "name", type: "text", width: 50 },
			            { title: '性別', name: "gender",width: 30, itemTemplate: function(val) {if(val=="Female"){return "女"}else{return "男"}} },
			            { title: '年齡', name: "age", type: "text", width: 30 },
			            { title: '公司名稱', name: "product.insurer.shortName", type: "text", width: 60 },
			            { title: '商品代碼', name: "product.code", type: "text", width: 60 },
			            { title: '商品名稱', name: "product.localName", type: "text", width: 100 },
			            { title: '保額', name: "product.insureAmount", width: 50, itemTemplate: function(val) {return val+"萬"}  },
			            { title: '預約時段1', name: "bookedTime_1", type: "text", width: 90 },
			            { title: '預約時段2', name: "bookedTime_2", type: "text", width: 90 },
			            { title: '預約時段3', name: "bookedTime_3", type: "text", width: 90 },
			            { title: '超商', name: "convenienceStoreEntity.manufacturer", type: "text", width: 60 },
			            { title: '門市', name: "convenienceStoreEntity.storeName", width: 60, itemTemplate: function(val) {return val+"門市"} },
			            { title: '業務員', name: "user.name", type: "text", width: 80 },
			            { title: '訂單狀態', name: "orderStatus", type: "text", width: 80 },
			            { title: '可獲得點數', name: "product.getPoint", type: "text", width: 80 },
			        ]
			    });

			</script>
		</div>
	</div>
</body>
</html>