<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.math.BigInteger"%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="java.awt.image.BufferedImage"%>
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
								<input type="date" class="form-control" id="date1" name="date1" placeholder="yyyy-MM-dd" value=""/>
							</div>
		          
							<label for="date2" class="col-sm-1 control-label">結束日期</label>
							<div class="col-sm-5">
								<input type="date" class="form-control" id="date2" name="date2" placeholder="yyyy-MM-dd" value="" />
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
						loadData : function(filter) {
							return $.ajax({
								type : "GET",
								url : BASE_URL,
								data : "pageIndex=" + filter.pageIndex + "&pageSize=10&"+$("#listForm").serialize()+"&date1="+$("#date1").text()+"&date2="+$("#date2").text(),
								dataType : "json",
								cache : false
							});
						},
					},

					fields : [ 
						{ name: '刪除', width:50, itemTemplate:btns },
						{name : "id",visible : true,width : 50},
						{title : '品牌',name : "brand",type : "text",width : 150},
						{title : '商品名稱',name : "name",type : "text",width : 400},
						{ title: '圖片', name: "showImage", width: 80,itemTemplate: function(val) {return $("<img>").attr("src","data:image/jpg;base64,"+val).css({ width: 80})}},
						{ title: '許願時間', name: "wishTime",type : "text", width: 200}
						

					]
				});
				
				 function btns(value, row) {
						var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
						$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
						
						$delBtn.click(function() {
							swal({
								  title: "你確定要刪除這個願望?",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  $delBtn.button('loading');
			 							$.delete_(BASE_URL+ "/" + row.id, function() {
			 								$delBtn.button('reset');
			 								$("#jsGrid").jsGrid("reset");
			 							});
								    swal("刪除成功!", {
								      icon: "success",
								    });
								  } 
								});
						});
						
						// return $("<div></div>").append($editBtn);
						return $("<div></div>").append($delBtn).append("&nbsp;");
					}
// 				$("#sort").click(function() {
// 					var field = $("#sortingField").val();
// 					$("#jsGrid").jsGrid("sort", field);
// 				});
			</script>

		</div>
	</div>



</body>
</html>
