<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>
<body>
<div class="container-fluid" style="margin-bottom:4%">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
	</div>

<div>
		<c:import url="/WEB-INF/pages/layout/article/sidebar.jsp"></c:import>
		</div>
	<!-- Testing inserting images -->
<div class="col-lg-8 bg-info"
				style="height: 100vh; display: table; table-layout: fixed;float:right;padding:0;">
				<div style="display: table-cell; vertical-align: middle;">
	<h2>新聞專區</h2>
		<div id="jsGrid"></div>
		</div>
	</div>
	<!-- Testing ends here -->
	
			
			<!-- <script>
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
			            { title: '保險安定基金', name: "insuranceGuarantyFund", type: "text", width: 200 },
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

			</script> -->
</body>
</html>



