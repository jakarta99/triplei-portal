<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>

<body>

	<div class="container-fluid" style="padding: 0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<h3>保險商品管理</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="${entity.id}" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>保險商品修改</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="code" class="col-md-2 control-label">商品代碼</label>
									<div class="col-md-10">
										<p class="form-control-static">${entity.code}</p>
										<input type="hidden" class="form-control" id="code" name="code" value="${entity.code}" />
									</div>
								</div>

								<div class="form-group required">
									<label for="name" class="col-md-2 control-label">商品名稱</label>
									<div class="col-md-10">
										<p class="form-control-static">${entity.localName}</p>
										<input type="hidden" class="form-control" id="localName" name="localName" value="${entity.localName}" />
									</div>
								</div>

								<div class="form-group required">
									<label for="clickCount" class="col-md-2 control-label">點擊數</label>
									<div class="col-md-10">
										<p class="form-control-static">${entity.clickCount}</p>
										<input type="hidden" class="form-control" id="clickCount" name="clickCount" value="${entity.clickCount}" />
									</div>
								</div>

								<div class="form-group required">
									<label for="hotProduct" class="col-md-2 control-label">熱門分類</label>
									<div class="col-md-10">
										<select class="form-control" id="hotProduct" name="hotProduct"
											style="width: 90px">
											<c:if test="${entity.hotProduct=='true'}">
												<option id="hotProduct" value="true" selected/>是
												<option id="hotProduct" value="false" />不是
											</c:if>
											<c:if test="${entity.hotProduct=='false'}">
												<option id="hotProduct" value="true" />是
												<option id="hotProduct" value="false" selected/>不是
											</c:if>
										</select>
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
						<a href="<c:url value='/admin/product/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function() {
	$("#saveButton").on("click",function(){
		var $btn = $(this);
		$btn.button("loading");
				
		var formData = new FormData(); 
		formData.append('id',$("#id").val());
		formData.append('hotProduct',$('#hotProduct:checked').val());
	 	
		$.ajax({ 
			url: "<c:url value='/admin/product/edit'/>", 
			method: "POST", 
			data: formData, 
			enctype:"multipart/form-data",
			processData: false, 
			contentType: false,
			success:function(data){
			swal("SUCCESS");
			},
			error:function(){
				swal("請確認資料無誤");
			}
		})
		$btn.button("reset");
		});
	});
</script>
</body>
</html>
