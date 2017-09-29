<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

<script type="text/javascript" src="<c:url value="/fckeditor/fckeditor.js"/>"></script>

</head>

<body>
	
	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div style="padding-top:9vh">
			<h3>文章管理</h3>
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
					<input type="hidden" id="id" name="id" value="${entity.id}"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>文章修改</strong></h4>
							</div>
							<div class="panel-body">
								<div class="form-group required">
									<label for="articleType" class="col-md-2 control-label">文章類別</label>
									<div class="col-md-10">
										<select class="form-control" id="articleType" name="articleType" style="width: 120px">
											<c:if test="${entity.articleType=='EDITOR_CHOICE'}">
												<option value="EDITOR_CHOICE"  selected="selected">編輯精選</option>
												<option value="NEWS">新聞專區</option>
												<option value="GOODREAD">小資族必讀</option>
												<option value="INVESTMENT_TIPS">理財觀念</option>
											</c:if>
											<c:if test="${entity.articleType=='NEWS'}">
												<option value="EDITOR_CHOICE">編輯精選</option>
												<option value="NEWS" selected="selected">新聞專區</option>
												<option value="GOODREAD">小資族必讀</option>
												<option value="INVESTMENT_TIPS">理財觀念</option>
											</c:if>
											<c:if test="${entity.articleType=='GOODREAD'}">
												<option value="EDITOR_CHOICE" selected>編輯精選</option>
												<option value="NEWS">新聞專區</option>
												<option value="GOODREAD" selected="selected">小資族必讀</option>
												<option value="INVESTMENT_TIPS">理財觀念</option>
											</c:if>
											<c:if test="${entity.articleType=='INVESTMENT_TIPS'}">
												<option value="EDITOR_CHOICE" selected>編輯精選</option>
												<option value="NEWS">新聞專區</option>
												<option value="GOODREAD">小資族必讀</option>
												<option value="INVESTMENT_TIPS" selected="selected">理財觀念</option>
											</c:if>
										</select><span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="title" class="col-md-2 control-label">標題</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="title" name="title" placeholder="標題" value="${entity.title}" />
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="introduction" class="col-md-2 control-label">文章簡介</label>
									<div class="col-md-10">
										<input class="form-control" id="introduction" name="introduction" placeholder="50字以內" value="${entity.introduction}"/>
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="content" class="col-md-2 control-label">文章內容</label>
									<div class="col-md-10">
										<textarea class="form-control" id="content" name="content">${entity.content}</textarea> 
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="author" class="col-md-2 control-label">作者</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="author" name="author" placeholder="作者名字" value="${entity.author}" />
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="bannerImage" class="col-md-2 control-label">廣告圖</label>
									<div class="col-md-10">
										<input type="file" class="form-control" id="bannerImage" name="bannerImage">
											<input type="file" id="hiddenImage" value="${entity.bannerImage}">
											<img src="${entity.bannerImage}">
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="bannerRotation" class="col-md-2 control-label">輪播</label>
									<div class="col-md-10">
										<select class="form-control" id="bannerRotation" name="bannerRotation" style="width:90px">
											<c:if test="${entity.bannerRotation=='true'}">
												<option value="false">不要</option>
												<option value="true" selected="selected">要</option>
											</c:if>
											<c:if test="${entity.bannerRotation=='false'}">
												<option value="false" selected="selected">不要</option>
												<option value="true">要</option>
											</c:if>
										</select>
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="hotArticle" class="col-md-2 control-label">熱門分類</label>
									<div class="col-md-10">
										<select class="form-control" id="hotArticle" name="hotArticle" style="width:90px">
											<c:if test="${entity.hotArticle=='true'}">
												<option value="false">不要</option>
												<option value="true" selected="selected">要</option>
											</c:if>
											<c:if test="${entity.hotArticle=='false'}">
												<option value="false" selected="selected">不要</option>
												<option value="true">要</option>
											</c:if>
										</select>
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="storeShelves" class="col-md-2 control-label">文章上架</label>
									<div class="col-md-10">
										<select class="form-control" id="storeShelves" name="storeShelves" style="width:90px">
											<c:if test="${entity.storeShelves=='true'}">
												<option value="false">不要</option>
												<option value="true" selected="selected">要</option>
											</c:if>
											<c:if test="${entity.storeShelves=='false'}">
												<option value="false" selected="selected">不要</option>
												<option value="true">要</option>
											</c:if>
										</select>
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/admin/article/getArticles'/>" class="btn btn-lg btn-primary btn-warning btn-block" data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$(function() {
		$("#hiddenImage").hide();
	
		var oFCKeditor = new FCKeditor("content");
		oFCKeditor.BasePath = "/fckeditor/";
		oFCKeditor.Height = 400;
		oFCKeditor.ToolbarSet = "Article" ;
		oFCKeditor.ReplaceTextarea();
	
	//<!-- Save -->	
	$("#saveButton").on("click",
		function() {
		var $btn = $(this);
			$btn.button("loading");
			
			var contentVal = FCKeditorAPI.GetInstance('content').GetHTML();
			$("#content").val(contentVal);
	
			var formData = new FormData(); 
			formData.append('id',$("#id").val());
			formData.append('articleType',$("#articleType").val());
			formData.append('title',$("#title").val());
			formData.append('introduction',$("#introduction").val());
			formData.append('content',$("#content").val());
			formData.append('author',$("#author").val());
			formData.append('bannerRotation',$("#bannerRotation").val());
			formData.append('hotArticle',$("#hotArticle").val());
			formData.append('storeShelves',$("#storeShelves").val());
			if("#bannerImage"==null){
				$("#bannerImage").val($("#hiddenImage").val());
			}else{
			$.each($("input[type='file']")[0].files, function (i, file) { 
				formData.append('upload-file', file); }); }
			$.ajax({ 
				url: "<c:url value='/admin/article'/>", 
				method: "PUT", 
				data: formData, 
				enctype:"multipart/form-data",
				processData: false, 
				contentType: false,
				success:function(data){
					alert("SUCCESS");
				}
			})
			
			$btn.button("reset");
		});
	});
</script>
	
</body>
</html>