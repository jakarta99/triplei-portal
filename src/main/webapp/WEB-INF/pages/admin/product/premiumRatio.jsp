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
			<h3>保險商品管理:基本費率</h3>
			
			<div id="jsGrid"></div>
		</div>
	</div>
			
			<script>
			var pathName = window.location.pathname
			var getId = pathName.substring(pathName.lastIndexOf('/') + 1);
			
			    var BASE_URL = "${pageContext.request.contextPath}/admin/product/premiumRatio/load/"+getId;
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			 
			        inserting: false,
			        editing: false,
			        sorting: false,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 20,
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
			            { title: '商品代碼', name: "productId", type: "text", width: 75 , validate: "required" },
			            { title: '年齡', name: "insAge", type: "text", width: 75 },
			            { title: '性別', name: "gender", type: "text", width: 50 },
			            { title: '基本費率', name: "premiumRatio", type: "text", width: 75 },

			        ]
			    })
			    
			</script>
</body>
</html>