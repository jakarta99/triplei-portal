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
<script type="text/javascript" src="<c:url value="/resources/jquery/moment.js"/>"></script>
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
			    var BASE_URL = "${pageContext.request.contextPath}/article";
			    var url = "${pageContext.request.contextPath}";
			 
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			        
			        inserting: false,
			        editing: false,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 10,
			        pageLoading: true,
			        autoload: true,
			        filter:true,
			        sorting:true,
			 
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
						{ name: "id", visible: false, width:10},
			            { title: '文章類別', name: "articleType", type: "text", width: 55, 
							itemTemplate:function(value){
								if(value=="EDITOR_CHOICE"){
									return $("<span></span>").text("編輯精選");	
								}else if(value=="NEWS"){
									return $("<span></span>").text("新聞專區");
								}else if(value=="GOODREAD"){
									return $("<span></span>").text("小資族必讀");
								}else if(value=="INVESTMENT_TIPS"){
									return $("<span></span>").text("理財觀念");
								}	
							}
			            },
			            { title: '標題', name: "title", type: "text", width: 70 },
			            { title: '文章簡介', name: "introduction", type: "text", width: 130},
			            { title: '文章內容', name: "content", type: "text", width: 200 },
			            { title: '作者', name: "author", type: "text", width: 50 },
			            { title: '廣告圖', name: "bannerImage",width: 80,  itemTemplate: function(val) {return $("<img>").attr("src", val).css({ height: 80, width: 80}) }},
			            { title: '發布時間', name: "publishTime", width: 55, itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD").toString();}},
			            { title: '點閱率', name: "clickCount", type: "text", width: 20 },
			            { title: '輪播', name: "bannerRotation", type: "checkbox", width: 25 },
			            { title: '熱門分類', name: "hotArticle", width: 25, type:"checkbox"},
			            { title: '文章上架', name: "storeShelves", width: 25, type:"checkbox"},
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
					
					return $("<div></div>").append($editBtn).append("&nbsp;").append($delBtn);
				}
			    

			</script>
		</div>
	</div>

</body>
</html>
