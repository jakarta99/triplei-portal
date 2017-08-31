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
			
			<h3>保險公司管理</h3>
			<div>
          		<a href="<c:url value='/admin/insurer/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>新增</a>
      		</div>
			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/admin/insurer";
			 
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
			            { title: '代碼', name: "code", type: "text", width: 20, validate: "required" },
			            { title: '公司全名', name: "name", type: "text", width: 150 },
			            { title: '公司簡稱', name: "shortName", type: "text", width: 200 },
			            { title: '公司名稱筆畫', name: "sortNo", type: "text", width: 200 },
			            { title: '資本適足率', name: "bisRatio", type: "text", width: 200 },
			            { title: '保單繼續率', name: "persistencyRatio", type: "text", width: 200 },
			            { title: '訴訟率', name: "litigationRatio", type: "text", width: 200 },
			            { title: '投訴率', name: "scomplaintRatio", type: "text", width: 200 },
			            { title: '申訴率', name: "appealRatio", type: "text", width: 200 },
			            { title: '保險安定基金', name: "insuranceGuarantyFund", type: "radio", width: 200 },
			            { title: '信用評等', name: "credit_rating", type: "text", width: 200 },
			            { title: '公司簡介', name: "description", type: "textarea", width: 200 },
			            { title: '公司Logo', name: "logo;", type: "file", width: 200 },
			            
			            
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
