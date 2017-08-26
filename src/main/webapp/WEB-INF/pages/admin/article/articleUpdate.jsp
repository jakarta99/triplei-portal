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
										<!-- 			      						<input type="text" class="form-control" id="code" name="code" placeholder="code" value=""/> -->
										<select class="form-control" id="articleType"
											name="articleType" style="width: 120px">
											<option value="EDITOR_CHOICE">編輯精選</option>
											<option value="NEWS">新聞專區</option>
											<option value="GOODREAD">小資族必讀</option>
											<option value="INVESTMENT_TIPS">理財觀念</option>
										</select> <span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="title" class="col-md-2 control-label">標題</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="title"
											name="title" placeholder="標題" value="${entity.title}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="introduction" class="col-md-2 control-label">文章簡介</label>
									<div class="col-md-10">
										<select class="form-control" id="introduction"
											name="introduction" style="width: 70px">
											<option value="false">false</option>
											<option value="true">true</option>
										</select> <span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="content" class="col-md-2 control-label">文章內容</label>
									<div class="col-md-10">
										<textarea class="form-control" id="content"
											name="content" placeholder="200字以內" >${entity.content}</textarea> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="author" class="col-md-2 control-label">作者</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="author"
											name="author" placeholder="作者名字" value="${entity.author}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="bannerImage" class="col-md-2 control-label">廣告圖</label>
									<div class="col-md-10">
										<input type="file"> <span class="help-block">
											<div class="text-danger"></div>
										</span>
									</div>
								</div>

								<div class="form-group required">
									<label for="bannerRotation" class="col-md-2 control-label">輪播</label>
									<div class="col-md-10">
										<select class="form-control" id="bannerRotation"
											name="bannerRotation" style="width: 70px">
											<option value="false">false</option>
											<option value="true">true</option>
										</select> <span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="hotArticle" class="col-md-2 control-label">熱門分類</label>
									<div class="col-md-10">
										<select class="form-control" id="hotArticle" name="hotArticle"
											style="width: 70px">
											<option value="false">false</option>
											<option value="true">true</option>
										</select> <span class="help-block"><div class="text-danger"></div></span>
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
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">儲存</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/article/getArticles'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>
	
	
	<script type="text/javascript">
$(function() {
	//<!-- Save -->	
	$("#saveButton").on("click",
		function() {
		var $btn = $(this);
			$btn.button("loading");
	
			$.put("<c:url value='/article'/>", "dataForm",
					function(data) {
						if (data.messages.length==0) {
							//swal("SUCCESS", "保險公司資料更新成功", "success");
							alert("SUCCESS");
							$btn.button("reset");
						}
					}, function(data, textStatus, jqXHR) {
						$btn.button("reset");
					});
			
			$btn.button("reset");
		});
	
	
	var intro = document.getElementById("mySelect").selectedIndex = ${entity.introduction};
// 	$("#articleType").val(${entity.articleType}); 
// 	$("#introduction").val(${entity.introduction}); 
// 	$("#introduction option[value="${entity.introduction}"]").props("selected",true);
// 	$("#introduction").find(${entity.introduction}).props("selected","selected");
// 	$("#bannerRotation").val(${entity.bannerRotation}); 
// 	$("#hotArticle").val(${entity.hotArticle}); 
// 	var type = ${entity.articleType}
// 	$("#articleType option[value=type]").attr('selected','selected');
// 	$('#articleType').find(${entity.articleType}).attr('selected', true);
// 	$('#introduction').find(${entity.introduction}).attr('selected', true);
// 	var introduction = ${entity.introduction}
// 	$("#introduction option:contains introduction+"]").attr('selected', 'selected');
// 	$("#introduction").options[value=${entity.introduction}].selected==true;
	("select#introduction option[value='${entity.introduction}']").attr("selected", "selected");
// 	$('select[name^="introduction"] option[value="${entity.introduction}""]').props("selected","selected");
	});
</script>
	
</body>
</html>


