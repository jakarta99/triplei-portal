<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<script type="text/javascript" src="<c:url value="/resources/jquery/moment.js"/>"></script>
<%-- <script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.load-indicator.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.load-strategies.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.sort-strategies.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jsgrid-1.5.3/jsgrid.field.js"/>"></script>
<style>
.sort-panel {
	padding: 10px;
	margin: 10px 0;
	background: #fcfcfc;
	border: 1px solid #e9e9e9;
	display: inline-block;
}
</style>
</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br />
			<br />
			<br />

			<h3>許願池商品管理</h3>
			<div>
				<a href="<c:url value='/admin/wish/add'/>"
					class="btn btn-sm btn-primary" data-loading-text="Loading"> <span
					class="glyphicon glyphicon-plus"></span>許願池測試
				</a>
			</div>
			<div class="sort-panel">
				<label>Sorting Field: <select id="sortingField">
						<option>id</option>
						<option value="name">商品名稱</option>
						<option value="brand">品牌</option>
						<option value="wishType">商品類型</option>
						<option value="wishTime">許願時間</option>
						
						
				</select>
					<button type="button" id="sort">Sort</button>
				</label>
			</div>
			<div id="jsGrid"></div>

			<script>
				var BASE_URL = "${pageContext.request.contextPath}/admin/wish";

				$("#jsGrid").jsGrid({
					width : "100%",
					height : "500px",


					

			        filtering: true,
	                selecting: false,
					inserting : false,
					editing : false,
					sorting : true,
					paging : true,
					pageIndex : 1,
					pageSize : 10,
			        pageButtonCount: 5,
					pageLoading : true,
					autoload : true,

					controller : {
						loadData : function(filter) {
							return $.ajax({
								type : "GET",
								url : BASE_URL,
								data : filter,
								dataType : "json",
								cache : false
							});
						},
					},

					fields : [ 
						{name : "id",visible : true,width : 50},
						{title : '商品類型',name : "wishType",type : "text",width : 150},
						{title : '品牌',name : "brand",type : "text",width : 150},
						{title : '商品名稱',name : "name",type : "text",width : 400},
// 						{title : '許願時間',name : "wishTime",type : "text",width : 200},
						
						{ title: '許願時間', name: "created_time", width: 200 ,itemTemplate:function(xxx){ 
			                 return moment({xxx}).format("YYYY/MM/DD"); 
			               }},
						

					]
				});
				$("#sort").click(function() {
					var field = $("#sortingField").val();
					$("#jsGrid").jsGrid("sort", field);
				});
			</script>

		</div>
	</div>



</body>
</html>
