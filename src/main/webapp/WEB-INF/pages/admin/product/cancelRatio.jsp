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

			<h3>保險商品管理:解約金費率</h3>

			<div id="jsGrid"></div>
		</div>
	</div>
			<script>
			var pathName = window.location.pathname
			var getId = pathName.substring(pathName.lastIndexOf('/') + 1);
			
			    var BASE_URL = "${pageContext.request.contextPath}/admin/product/cancelRatio/load/"+getId;
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			 
			        inserting: false,
			        editing: false,
			        sorting: false,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 50,
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
						{ name: "id", visible: false},
			            { title: '商品代碼', name: "productId", type: "text", width: 25 },
			            { title: '姓別', name: "gender", type: "text", width: 15 },
			            { title: '年齡', name: "insAge", type: "text", width: 15 },
			            { title: '年期', name: "year", type: "text", width: 15 },
			            { title: '第0年', name: "cancelRatio_0", type: "text", width: 20 },
			            { title: '第1年', name: "cancelRatio_1", type: "text", width: 20 },
			            { title: '第2年', name: "cancelRatio_2", type: "text", width: 20 },
			            { title: '第3年', name: "cancelRatio_3", type: "text", width: 20 },
			            { title: '第4年', name: "cancelRatio_4", type: "text", width: 20 },
			            { title: '第5年', name: "cancelRatio_5", type: "text", width: 20 },
			            { title: '第6年', name: "cancelRatio_6", type: "text", width: 20 },
			            { title: '第7年', name: "cancelRatio_7", type: "text", width: 20 },
			            { title: '第8年', name: "cancelRatio_8", type: "text", width: 20 },
			            { title: '第9年', name: "cancelRatio_9", type: "text", width: 20 },
			            { title: '第10年', name: "cancelRatio_10", type: "text", width: 20 },
			            { title: '第11年', name: "cancelRatio_11", type: "text", width: 20 },
			            { title: '第12年', name: "cancelRatio_12", type: "text", width: 20 },
			            { title: '第13年', name: "cancelRatio_13", type: "text", width: 20 },
			            { title: '第14年', name: "cancelRatio_14", type: "text", width: 20 },
			            { title: '第15年', name: "cancelRatio_15", type: "text", width: 20 },
			            { title: '第16年', name: "cancelRatio_16", type: "text", width: 20 },
			            { title: '第17年', name: "cancelRatio_17", type: "text", width: 20 },
			            { title: '第18年', name: "cancelRatio_18", type: "text", width: 20 },
			            { title: '第19年', name: "cancelRatio_19", type: "text", width: 20 },

			        ]
			    })
			    
			</script>

</body>
</html>