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
<script type="text/javascript"
	src="<c:url value="/resources/jquery/moment.js"/>"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Triple i</title>
<style>
.ui-dialog{
border:1px black solid;
}
.ui-widget-header{
	background-color:#D7D9D8;
	border-radius:5px;
}
.ui-widget-content{
	background-color:white;
	border-radius:5px;
}
.ui-dialog-titlebar {
    padding: 0.1em .5em;
    position: relative;
    font-size: 120%;
}
.ui-dialog-titlebar-close{
	position:relative;
	float:right;
	font-size:70%;
}
</style>
</head>

<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br /> <br /> <br />
			<h3>超商一覽</h3>
	<div>
				<a href="/admin/convenienceStore/insertPage"
					class="btn btn-sm btn-primary" data-loading-text="Loading"> 
					<span class="glyphicon glyphicon-plus"></span>新增</a>
			</div>
			<div id="jsGrid"></div>
			<div id="detailsDialog">
			<span id="content" style="word-wrap:break-word"></span>
			</div>
			<script type="text/javascript">

			    var BASE_URL = "${pageContext.request.contextPath}/admin/convenienceStore";
			 
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
			        pageNextText:"下一頁",
			        pagePrevText:"上一頁",
			        pageFirstText:"第一頁",
			        pageLastText:"最後一頁",
			        dialogClass: 'detailsDialog',
			        rowDoubleClick: function(args) {
			            showDetailsDialog("內容",args.item);
			        },
			 
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
			            { name: '刪／修', width:40, itemTemplate:btns },
						{ name: "id", visible: false},
			            
			            { title: '廠商', name: "manufacturer", type: "text", width: 150 },
			            { title: '店名', name: "storeName", type: "text", width: 150 },
			            { title: '縣市', name: "city", type: "text", width: 150 },
			            { title: '區域', name: "region",type:"text", width: 150 },
			            { title: '地址', name: "address",type:"text",width: 150 },

			        ]
			    });
			    
			    var showDetailsDialog = function(dialogType, values) {
			    	$("#content").text(values.content);
			        $("#detailsDialog").dialog("option","title",dialogType).dialog("open");
			    };
			    
			    $("#detailsDialog").dialog({
			    	autoOpen: false,
			        width:400,
			        modal:true,
			        show: {
			          effect: "blind",
			          duration: 500
			        },
			        hide: {
			          effect: "blind",
			          duration: 500
			        }
			    });
			    
			    function btns(value, row) {
					var $delBtn = $('<button type="button" class="btn btn-danger btn-xs"></button>');
					$delBtn.append('<span class="glyphicon glyphicon-trash"></span> 刪除');
					
					$delBtn.click(function() {
						if (confirm('你確定要刪除這筆資料?')) {
// 							$delBtn.button('loading');
							$.delete_(BASE_URL+ "/" + row.id, function() {
// 								$delBtn.button('reset');
								$("#jsGrid").jsGrid("reset");
							});
						}
					});
					
// 					var $editBtn = $('<a class="btn btn-info btn-xs"></a>');
// 					$editBtn.attr("href", BASE_URL + "/" + row.id);
// 					$editBtn.append('<span class="glyphicon glyphicon-pencil"></span> 編輯');
// 					.append("<br/>").append($editBtn)
					return $("<div></div>").append($delBtn);
				}
			    
			    
			</script>

		</div>
	</div>



</body>
</html>
