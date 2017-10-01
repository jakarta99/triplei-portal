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
			<h3>積點商品管理</h3>
			<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">
							<label for="name" class="col-sm-1 control-label">名稱</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="name" name="name" placeholder="名稱"/>
							</div>
		          
							<label for="brand" class="col-sm-1 control-label">品牌</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="brand" name="brand" placeholder="品牌" />
							</div>
						</div>
						<div class="form-group">
							<label for="brand" class="col-sm-1 control-label">兌換點數區間</label>
							<div class="col-sm-5">
								從  <input type="number" id="val1" name="val1" placeholder="低" value="0"/> ~ 
								<input type="number"  id="val2" name="val2" placeholder="高" value="0"/> :範圍
							</div>
						</div>							
					</form>
				</div>
				<div class="row pull-right">
					<button id="searchBtn" class="btn btn-success" data-loading-text="loading..." type="button">搜尋</button>
		          		<button id="resetBtn" class="btn btn-warning" data-loading-text="loading..." type="button" value="reset">重設</button>
				</div>
			</section>
			
			
			
			<div>
          		<a href="<c:url value='/admin/gift/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>新增</a>
      		</div>

      		
      		
			<div id="jsGrid"></div>
			
			<script>
			$("#searchBtn").bind("click",function(){
				$("#jsGrid").jsGrid("reset");
			});
			
			$("#resetBtn").bind("click", function(){
				$("#listForm")[0].reset();
			});
			
			
			
			    var BASE_URL = "${pageContext.request.contextPath}/admin/gift";
			 
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
			                    data: "pageIndex=1&pageSize=10&"+$("#listForm").serialize()+"&val1="+$("#val1").val()+"&val2="+$("#val2").val(),
			                    dataType: "json",
			                    cache: false
			                });
			            },
			        },
			        
			 
			        fields: [
			            { name: '刪／修', width:60, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '品牌', name: "brand", type: "text", width: 150 },
			            { title: '積點商品名稱', name: "name", type: "text", width: 150 },
			            { title: '顏色/花樣', name: "colorAndType", type: "text", width: 150 },
// 			            { title: '商品代碼', name: "code", type: "code", width: 150 },
			            { title: '商品兌換點數', name: "bonus", type: "text", width: 150 },
			            { title: '照片', name: "image1", type: "text", width: 150 ,itemTemplate: function(val) {return $("<img>").attr("src",val).css({ width: 80 })}},
// 			            { title: '照片二', name: "image2", type: "text", width: 150 },
// 			            { title: '照片三', name: "image3", type: "text", width: 150 },
			            { title: '類別', name: "giftType", width: 150,type:"text",itemTemplate:function(data){
			            	
			            	if(data=="VOUCHERS"){
								return $("<span></span>").text("禮劵");	
							}else if(data=="FURNITURES"){
								return $("<span></span>").text("傢俱，廚具");
							}else if(data=="ELECTRONICS"){
								return $("<span></span>").text("3C家電");
							}else if(data=="OUTDOOR"){
								return $("<span></span>").text("戶外運動");
							}else if(data=="WOMAN"){
								return $("<span></span>").text("女仕用品");
							}else if(data=="MAN"){
								return $("<span></span>").text("男仕用品");
							}else if(data=="OTHERS"){
								return $("<span></span>").text("其他");
							}
			            }
			            },
			            { title: '熱門商品', name: "hotGift", type: "checkbox", width: 150 },
// 			            { title: '兌換日期', name: "exchangeDate", type: "text", width: 150 },
			            { title: '備註', name: "remarks", type: "text", width: 150 }
     
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
					
					return $("<div></div>").append($editBtn).append("<br/>").append($delBtn);
				}

			</script>

		</div>
	</div>
	
	
	
</body>
</html>
