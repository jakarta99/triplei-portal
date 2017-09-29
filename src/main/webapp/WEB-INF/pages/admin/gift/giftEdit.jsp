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
<title>Triple i</title>



</head>

<body>

	<div class="container-fluid" style="padding:0;">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>

		<div>
			<h3>積點商品管理</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="${entity.id}" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>積點商品修改</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="brand" class="col-md-2 control-label">品牌</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="brand"
											name="brand" placeholder="Brand" value="${entity.brand}" />
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="name" class="col-md-2 control-label">積點商品名稱</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="Name" value="${entity.name}" /> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="colorAndType" class="col-md-2 control-label">顏色/花樣</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="colorAndType"
											name="colorAndType" placeholder="colorAndType"
											value="${entity.colorAndType}" /> <span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="bonus" class="col-md-2 control-label">商品兌換點數</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="bonus"
											name="bonus" placeholder="bonus" value="${entity.bonus}" />
										<span class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="image1" class="col-md-2 control-label">選擇圖片</label>
									<div class="col-md-10">
										<input type="file" class="form-control" id="image1"
											name="image1" placeholder="照片一" value="" /> 
<!-- 											<input -->
<!-- 											type="file" class="form-control" id="image2" name="image2" -->
<!-- 											placeholder="照片二" value="" /> <input type="file" -->
<!-- 											class="form-control" id="image3" name="image3" -->
<!-- 											placeholder="照片三" value="" /> -->
										<div class="text-danger"></div>
										<div class="text-danger"></div>
									</div>
								</div>
							</div>
							<div class="form-group required">
								<label for="remarks" class="col-md-2 control-label">備註</label>
								<div class="col-md-10">
									<input type="text" class="form-control" id="remarks"
										name="remarks" placeholder="remarks" value="${entity.remarks}" />
									<span class="help-block"><div class="text-danger"></div></span>
								</div>
							</div>
							<div class="form-group required">
								<label for="giftType" class="col-md-2 control-label">類別</label>
								<div class="col-md-10">
									<select class="form-control" id="giftType" name="giftType">
										<option value="VOUCHERS">禮劵</option>
										<option value="FURNITURES">家居，廚具</option>
										<option value="ELECTRONICS">3C家電</option>
										<option value="OUTDOOR">戶外運動</option>
										<option value="WOMAN">女仕用品</option>
										<option value="MAN">男仕用品</option>
										<option value="OTHERS">其他</option>
									</select> <span  class="help-block"><div class="text-danger"></div></span>
								</div>
							</div>
							<div class="form-group required">
								<label for="hotGift" class="col-md-2 control-label">熱門商品</label>
								<div class="col-md-10">
									<input type="checkbox" class="form-control" id="hotGift"
										name="hotGift" placeholder="hotGift" value="false"
										onclick="CBSelectedValueToTrue(this);" /> <span
										class="help-block"><div class="text-danger"></div></span>
								</div>
							</div>
<!-- 							<div class="form-group required"> -->
<!-- 								<label for="exchangeDate" class="col-md-2 control-label">兌換日期</label> -->
<!-- 								<div class="col-md-10"> -->
<!-- 									<input type="text" class="form-control" id="exchangeDate" -->
<!-- 										name="exchangeDate" placeholder="exchangeDate" -->
<%-- 										value="${entity.exchangeDate}" /> <span class="help-block"><div --%>
<!-- 											class="text-danger"></div></span> -->
<!-- 								</div> -->
							</div>
						</div>
					</form>
				</div>

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
					<a href="<c:url value='/admin/gift/list'/>"
						class="btn btn-lg btn-primary btn-warning btn-block"
						data-loading-text="Loading">返回</a>
				</div>
			</div>
		</div>
	</div>
	</div>

</body>
</html>
<script type="text/javascript">
	$(function() {
		//<!-- Save -->	
		$("#saveButton").on("click", function() {
			var $btn = $(this);
			$btn.button("loading");

			var formData = new FormData();
			formData.append('id', $("#id").val());
			formData.append('brand', $("#brand").val());
			formData.append('name', $("#name").val());
			formData.append('colorAndType', $("#colorAndType").val());
			formData.append('bonus', $("#bonus").val());
			formData.append('remarks', $("#remarks").val());
			formData.append('giftType', $("#giftType").val());
			if($("#hotGift").val()){		
				formData.append('hotGift', $("#hotGift").val());
			}else if($("#hotGift").val()==false){
				formData.append('hotGift', $("#hotGift").val("false"));
			}
			// 			formData.append('exchangeDate', $("#exchangeDate").val());

			$.each($("input[type='file']")[0].files, function(i, file) {
				formData.append('upload-file', file);
			});
// 			$.each($("input[type='file']")[1].files, function(i, file) {
// 				formData.append('upload-file1', file);
// 			});
// 			$.each($("input[type='file']")[2].files, function(i, file) {
// 				formData.append('upload-file2', file);
// 			});
			$.ajax({
				url : "<c:url value='/admin/gift'/>",
				method : "PUT",
				data : formData,
				enctype : "multipart/form-data",
				processData : false,
				contentType : false,
				success : function(data) {
					alert("修改成功");
				},
				error : function() {
					alert("請確認資料填寫無誤");
				}
			})
			$btn.button("reset");
		});
	});
</script>
<script type="text/javascript">
	function CBSelectedValueToTrue(cb) {
		if (cb.checked) {
			cb.value = "true";
		}else{
			cb.value = "false";
		}
	}
</script>

