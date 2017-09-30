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
<script type="text/javascript"
	src="<c:url value="/resources/jquery/moment.js"/>"></script>
	
	
	
	
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

	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div style="padding-top:9vh">
			<h3>許願池查看</h3>
			<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">
							<label for="date1" class="col-sm-1 control-label">開始日期</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="date1" name="date1" placeholder="yyyy-MM-dd"/>
							</div>
		          
							<label for="date2" class="col-sm-1 control-label">結束日期</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="date2" name="date2" placeholder="yyyy-MM-dd" />
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
			
			
			
				var BASE_URL = "${pageContext.request.contextPath}/admin/wish";

				$("#jsGrid").jsGrid({
					width : "100%",
					height : "500px",

					autoload : true,
// 			        filtering: true,
	                selecting: false,
					inserting : false,
					editing : false,
					sorting : true,
					paging : true,
					pageIndex : 1,
					pageSize :10,
			        pageButtonCount: 5,
					pageLoading : true,


					controller : {
						loadData : function() {
							return $.ajax({
								type : "GET",
								url : BASE_URL,
								data : "pageIndex=1&pageSize=10&"+$("#listForm").serialize()+"&date1="+$("#date1").val()+"&date2="+$("#date2").val(),
								dataType : "json",
								cache : false
							});
						},
					},

					fields : [ 
						{name : "id",visible : true,width : 50},
						{title : '品牌',name : "brand",type : "text",width : 150},
						{title : '商品名稱',name : "name",type : "text",width : 400},
						{ title: '圖片', name: "image1", width: 80,itemTemplate: function(val) {return $("<img>").attr("src",val).css({ width: 80})}},
						{ title: '許願時間', name: "wishTime",type : "text", width: 200}
						

					]
				});
// 				$("#sort").click(function() {
// 					var field = $("#sortingField").val();
// 					$("#jsGrid").jsGrid("sort", field);
// 				});
			</script>

		</div>
	</div>



</body>
</html>
