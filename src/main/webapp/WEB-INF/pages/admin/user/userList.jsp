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
			
			<h3>會員管理</h3>

			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/admin/user";
			 
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
			            { name: '修/刪', width:30, itemTemplate:btns },
						{ name: "id", visible: false},
						{ title: '會員帳號', name: "accountNumber", type: "text", width: 50 },
						{ title: '會員密碼', visible: false },
						{ title: '會員姓名', name: "name", type: "text", width: 50 },
						{ title: '會員email', name: "email", type: "text", width: 100 },
						{ title: '狀態', name: "enabled", type: "checkbox", width: 30} 
			        ]
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('你確定要刪除這筆資料?')) {
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
					
					// return $("<div></div>").append($editBtn);
					return $("<div></div>").append($editBtn).append("&nbsp;").append($delBtn);
				}
			</script>
			
		</div>
	</div>
</body>
</html>