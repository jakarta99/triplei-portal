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
			
			<h3>問題一覽</h3>
			<div>
          		<a href="<c:url value='/admin/question/add'/>" class="btn btn-sm btn-primary" data-loading-text="Loading">
            	<span class="glyphicon glyphicon-plus"></span>新增</a>
      		</div>
			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/admin/question";
			 
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
			            { title: '問題分類1', name: "questionType", type: "text", width: 150, validate: "required" },
			            { title: '問題分類2', name: "questionType2", type: "text", width: 150 },
			            { title: 'Email', name: "askerEmail", type: "text", width: 200 },
			            { title: '問題內容', name: "content", type: "text", width: 200 },
			            { title: '提問時間', name: "postTime", type: "text", width: 200 },
			            
			            
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
