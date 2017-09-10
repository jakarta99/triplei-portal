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
			
			<h3>積點商品管理</h3>
			<div>
          		<a href="<c:url value='/admin/gift/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>新增</a>
      		</div>
			<div id="jsGrid"></div>
			
			<script>
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
			            { title: '商品代碼', name: "code", type: "code", width: 150 },
			            { title: '商品兌換點數', name: "bonus", type: "text", width: 150 },
			            { title: '照片一', name: "image1", type: "text", width: 150 },
			            { title: '照片二', name: "image2", type: "text", width: 150 },
			            { title: '照片三', name: "image3", type: "text", width: 150 },
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
			            { title: '兌換日期', name: "exchangeDate", type: "text", width: 150 },
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
