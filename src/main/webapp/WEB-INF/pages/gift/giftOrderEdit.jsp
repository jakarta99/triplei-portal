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
			<h3>積點商品訂單修改</h3>

			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
						<input type="hidden" id="id" name="id" value="${entity.id}" />
						<input type="hidden" id="orderTime" name="orderTime" value="${entity.orderTime}" />
						<input type="hidden" id="giftEntity.name" name="giftEntity.name" value="${entity.giftEntity.name}"> 
						<input type="hidden" id="status" name="status" value="${entity.status}"> 
						<input type="hidden" id="createdBy" name="createdBy" value="${entity.createdBy}"> 
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>
									<span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>積點商品訂單修改</strong>
								</h4>
							</div>
							<div class="panel-body">

								<div class="form-group required">
									<label for="giftEntity.name" class="col-md-2 control-label">商品名稱</label>
									<div class="col-md-10">
										<input type="text" disabled="disabled" class="form-control" value="${entity.giftEntity.name}"> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="quantity" class="col-md-2 control-label">數量</label>
									<div class="col-md-10">
										<input type="number" min="1" class="form-control" id="quantity"
											name="quantity" value="${entity.quantity}"> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="recipient" class="col-md-2 control-label">收件人姓名</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="recipient"
											name="recipient" value="${entity.recipient}"> <span
											class="help-block"><div class="text-danger"></div></span>
									</div>
								</div>
								<div class="form-group required">
									<label for="recipientAddress" class="col-md-2 control-label">收件人地址</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="recipientAddress"
											name="recipientAddress" placeholder="Address"
											value="${entity.recipientAddress}" /> <span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="recipientPhone" class="col-md-2 control-label">收件人電話</label>
									<div class="col-md-10">
										<input type="text" class="form-control" id="Phone"
											name="recipientPhone" placeholder="recipientPhone"
											value="${entity.recipientPhone}" /> <span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>

								<div class="form-group required">
									<label for="recipientTime" class="col-md-2 control-label">希望收件時間</label>
									<div class="col-md-10">
										<select class="form-control" id="recipientTime"
											name="recipientTime" > 
											<c:choose>
											<c:when test="${entity.recipientTime == '上午'}">
											<option value="上午">上午</option>
											<option value="下午">下午</option>
											<option value="晚上">晚上</option>
											</c:when>
											<c:when test="${entity.recipientTime == '下午'}">
											<option value="下午">下午</option>
											<option value="上午">上午</option>
											<option value="晚上">晚上</option>
											</c:when>
											<c:when test="${entity.recipientTime == '晚上'}">
											<option value="晚上">晚上</option>
											<option value="上午">上午</option>
											<option value="下午">下午</option>
											</c:when>
											</c:choose>
											</select> <span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>

				<!-- 			<div class="form-group required">
									<label for="recipientTime" class="col-md-2 control-label">訂單狀態</label>
									<div class="col-md-10">
										<select class="form-control" id="status"
											name="status"> 
											<c:choose>
											<c:when test="${entity.status == 'WAITING'}">
											<option value="WAITING">已收到訂單</option>
											<option value="CANCELDONE">取消訂單</option>
											</c:when>
											<c:when test="${entity.status == 'PROCESSING'}">
											<option value="PROCESSING">訂單處理中</option>
											<option value="CANCELDONE">取消訂單</option>
											</c:when>
											<c:when test="${entity.status == 'SHIPORDER'}">
											<option value="SHIPORDER">不可修改</option>
											</c:when>
											<c:when test="${entity.status == 'DONE'}">
											<option value="DONE">不可修改</option>
											</c:when>
											<c:when test="${entity.status == 'CANCELDONE'}">
											<option value="CANCELDONE">不可修改</option>
											</c:when>
											</c:choose>
											</select>
										<span class="help-block"><div
												class="text-danger"></div></span>
									</div>
								</div>

 -->	

							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div>
						<a href="#" class="btn btn-lg btn-primary btn-block"
							data-loading-text="Loading" id="saveButton">修改</a>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<a href="<c:url value='/gift/giftOrder/list'/>"
							class="btn btn-lg btn-primary btn-warning btn-block"
							data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#questionType").hide();
			//<!-- Save -->	
			$("#saveButton").bind(
					"click",
					function() {
						var $btn = $(this);
						$btn.button("loading");

						$.put("<c:url value='/gift/giftOrder'/>", "dataForm",
								function(data) {
									if (data.訂單已修改) {
										//swal("SUCCESS", "問題資料更新成功", "success");
										swal("SUCCESS", "訂單修改成功", "success");
										$btn.button("reset");
									}else if(data.系統發生錯誤){
										swal("非常抱歉", "系統發生錯誤", "warning");
									}else if(data.已超過修改期限){
										swal("非常抱歉", "已超過修改期限", "warning");
									}else if(data.剩餘點數不足){
										swal("非常抱歉", "剩餘點數不足", "warning");
									}else if(data.輸入數量過大){
										swal("非常抱歉", "輸入數量過大", "warning");
									}
								}, function(data, textStatus, jqXHR) {
									$btn.button("reset");
								});

						$btn.button("reset");
					});
		});
	</script>

</body>
</html>


