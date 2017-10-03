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

	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div style="padding-top:9vh">
			<h3>文章列表</h3>
			<section class="well">
				<div>
					<form role="form" class="form-horizontal" id="listForm">
						<div class="form-group">
							<label for="title" class="col-sm-1 control-label">標題</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="title" name="title" placeholder="標題"/>
							</div>
		          
							<label for="author" class="col-sm-1 control-label">作者</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="author" name="author" placeholder="作者" />
							</div>
						</div>
						<div class="form-group">
							<label for="articleType" class="col-sm-1 control-label">文章分類</label>
							<div class="col-sm-5">
								<select id="articleType" name="articleType" class="form-control">
									<option value="">全部</option>
									<option value="EDITOR_CHOICE">編輯精選</option>
									<option value="NEWS">新聞專區</option>
									<option value="GOODREAD">小資族必讀</option>
									<option value="INVESTMENT_TIPS">理財觀念</option>
								</select>
							</div>
							<label for="content" class="col-sm-1 control-label">內文搜索</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="content" name="content" placeholder="內文搜索" />
							</div>
						</div>							
					</form>
				</div>
				<div class="row pull-right">
					<button id="searchBtn" class="btn btn-success" data-loading-text="loading..." type="button">搜尋</button>
		          		<button id="resetBtn" class="btn btn-warning" data-loading-text="loading..." type="button" value="reset">重設</button>
				</div>
			</section>
			
			
			
			
			<div style="padding-top:1vh">
				<a href="<c:url value='/admin/article/insertArticle'/>"
					class="btn btn-sm btn-primary" data-loading-text="Loading"> 
					<span class="glyphicon glyphicon-plus"></span>新增</a>
			</div>
			<div id="jsGrid" style="padding-top:1vh"></div>

	<script>
	
	$("#searchBtn").bind("click",function(){
		$("#jsGrid").jsGrid("reset");
	});
	
	$("#resetBtn").bind("click", function(){
		$("#listForm")[0].reset();
	});
			    var BASE_URL = "${pageContext.request.contextPath}/admin/article";
			    var url = "${pageContext.request.contextPath}";
			    
			    $("#jsGrid").jsGrid({
			        width: "100%",
			        height: "500px",
			        
			        inserting: false,
			        editing: false,
			        paging: true,
			        pageIndex: 1,
			        pageSize: 5,
			        pageLoading: true,
			        autoload: true,
			        sorting:true,
			 
			        controller: {
			            loadData: function (filter) {
			            	return $.ajax({
			                    type: "GET",
			                    url: BASE_URL,
			                    data: "pageIndex=" + filter.pageIndex + "&pageSize=5&"+$("#listForm").serialize(),
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
			            { title: '文章內容', name: "content", type: "text", width: 200,visible:false },
			            { title: '作者', name: "author", type: "text", width: 50 },
			            { title: '廣告圖', name: "bannerImage",width: 80,  itemTemplate: function(val) {return $("<img>").attr("src",val).css({ width: 80})}},
			            { title: '發布時間', name: "publishTime", width: 55, itemTemplate: function(value) {return moment({value}).format("YYYY/MM/DD").toString();}},
			            { title: '點閱率', name: "clickCount", type: "number", width: 30 },
			            { title: '輪播', name: "bannerRotation", type: "checkbox", width: 25 },
			            { title: '熱門分類', name: "hotArticle", width: 25, type:"checkbox"},
			            { title: '文章上架', name: "storeShelves", width: 25, type:"checkbox"},
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
					
					return $("<div></div>").append($editBtn).append("&nbsp;").append($delBtn);
				}

			</script>
		</div>
	</div>

</body>
</html>
