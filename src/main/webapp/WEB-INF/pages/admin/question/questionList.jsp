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
<script type="text/javascript" src="<c:url value="/resources/jquery/moment.js"/>"></script>
<title>Triple i</title>



</head>

<body>
	
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div>
			<br/><br/><br/>
			
			<h3 style="text-align:center;">問題一覽</h3>
		</br>
			<div id="jsGrid"></div>
			
			<script>
			    var BASE_URL = "${pageContext.request.contextPath}/admin/question";
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			 
			        inserting: false,
			        editing: false,
			        sorting: true,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 10,
			        pageLoading: true,
			        autoload: true,
			        filter:true,
			 
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
			            { name: '編輯', width:60, itemTemplate:btns },
						{ name: "id", visible: false},
			            { title: '問題分類1', name: "questionType", type: "text", width: 150, validate: "required" },
			            { title: '問題分類2', name: "questionType2", type: "text", width: 150 },
			            { title: 'Email', name: "askerEmail", type: "text", width: 200 },
			            { title: '問題內容', name: "content", type: "text", width: 200 },
			            { title: '提問時間', name: "postTime", type: "text", width: 200 , itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD HH:mm").toString();}},
			            
			            
			        ]
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('確定要刪除嗎?')) {
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
					
					
					var $emailBtn = $('<a class="btn btn-info btn-xs"></a>');
					$emailBtn.attr("href", BASE_URL + "/" + row.id + "/emailResponse");
					$emailBtn.append('<span class="glyphicon glyphicon-pencil"></span> 回覆');
					
					return $("<div></div>").append($editBtn).append("&nbsp;&nbsp;&nbsp;").append($emailBtn).append("&nbsp;&nbsp;&nbsp;").append($delBtn);
				}

			</script>

		</div>
	</div>
	
	
	
</body>
</html>
