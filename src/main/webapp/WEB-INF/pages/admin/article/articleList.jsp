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

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<br/><br/><br/>
			<h3>文章列表</h3>
			<div>
				<a href="<c:url value='/article/insertArticle'/>"
					class="btn btn-sm btn-primary" data-loading-text="Loading"> 
					<span class="glyphicon glyphicon-plus"></span>新增</a>
			</div>
			<div id="jsGrid"></div>

	<script>
	
	
	var MyDateField = function(config) {
	    jsGrid.Field.call(this, config);
	};
	 
	MyDateField.prototype = new jsGrid.Field({
	 
	    sorter: function(date1, date2) {
	        return new Date(date1) - new Date(date2);
	    },
	 
	    itemTemplate: function(value) {
	        return new Date(value).toDateString();
	    },
	 
// 	    insertTemplate: function(value) {
// 	        return this._insertPicker = $("<input class="date-picker">").datepicker({ defaultDate: new Date() });
// 	    },
	    
	    insertTemplate: function (value) {
	        return this._insertPicker = $("<input class='date-picker'>").datetimepicker();
	    },
	 
// 	    editTemplate: function(value) {
// 	        return this._editPicker = $("<input class="date-picker">").datepicker().datepicker("setDate", new Date(value));
// 	    },
	    
	    editTemplate: function (value) {
	        return this._editPicker = $("<input class='date-picker'>").datetimepicker();
	    },
	 
// 	    insertValue: function() {
// 	        return this._insertPicker.datepicker("getDate").toISOString();
// 	    },
	    
	    insertValue: function () {
	        return this._insertPicker.data("DateTimePicker").useCurrent();
	    },
	 
// 	    editValue: function() {
// 	        return this._editPicker.datepicker("getDate").toISOString();
// 	    }
	    
		editValue: function () {
		    return this._editPicker.data("DateTimePicker").useCurrent();
		}
	});
	 
	jsGrid.fields.date = MyDateField;
	
		
			    var BASE_URL = "${pageContext.request.contextPath}/article";
			 
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
			            { name: '刪／修', width:45, itemTemplate:btns },
						{ name: "id", visible: true, width:10},
			            { title: '文章類別', name: "articleType", type: "text", width: 70},
			            { title: '標題', name: "title", type: "text", width: 120 },
			            { title: '文章簡介', name: "introduction", type: "text", width: 30 },
			            { title: '文章內容', name: "content", type: "text", width: 200 },
			            { title: '作者', name: "author", type: "text", width: 60 },
			            { title: '廣告圖', name: "bannerImage", type: "text", width: 50 },
			            { title: '發布時間', name: "publishTime", type:"date", width: 50 },
			            { title: '點閱率', name: "clickCount", type: "text", width: 20 },
			            { title: '輪播', name: "bannerRotation", type: "text", width: 30 },
			            { title: '熱門分類', name: "hotArticle", width: 30, type:"text",edittype:'select', formatter:'select', editoptions:{value:"false:False;true:True"}},
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
					
					return $("<div></div>").append($editBtn).append("&nbsp;&nbsp;&nbsp;").append($delBtn);
				}
			    

			</script>
		</div>
	</div>

</body>
</html>
