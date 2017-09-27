<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE HTML>
<html lang="en">
<head>
<sec:csrfMetaTags />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
</head>
<body>

	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		
		<div>
			
			<h3>會員管理</h3>
			
			<div class="panel panel-default">
				<div class="panel-body">
					<form class="form-horizontal" id="dataForm">
					<input type="hidden" id="id" name="id" value="${entity.id}"/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4><span class="glyphicon glyphicon-edit"></span>&nbsp; <strong>會員權限修改</strong></h4>
							</div>
							<div class="panel-body">
							
								<div class="form-group required">
			    					<label for="accountNumber" class="col-md-2 control-label">會員帳號</label>
			    					<div class="col-md-8">
			    						<p class="form-control-static">${entity.accountNumber}</p>
			    						<input type="hidden" class="form-control" id="accountNumber" name="accountNumber" value="${entity.accountNumber}"/>
			    					</div>
								</div>	
								
			  					<div class="form-group required">
			    					<label for="name" class="col-md-2 control-label">會員姓名</label>
			    					<div class="col-md-8">
			      						<p class="form-control-static">${entity.name}</p>
			      						<input type="hidden" class="form-control" id="name" name="name" value="${entity.name}"/>
			    					</div>
								</div>	
								
								</div>	
								
								<div class="form-group required">
			    					<label for="email" class="col-md-2 control-label">會員email</label>
			    					<div class="col-md-8">
			      						<input type="text" class="form-control" id="email" name="email" placeholder="email" value="${entity.email}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="exchangedPoint" class="col-md-2 control-label">已兌換點數</label>
			    					<div class="col-md-8">
			      						<input type="text" readonly="readonly" class="form-control" id="exchangedPoint" name="exchangedPoint" placeholder="exchangedPoint" value="${entity.exchangedPoint}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="remainPoint" class="col-md-2 control-label">剩餘點數</label>
			    					<div class="col-md-8">
			      						<input type="text" readonly="readonly" class="form-control" id="remainPoint" name="remainPoint" placeholder="remainPoint" value="${entity.remainPoint}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>
								
								<div class="form-group required">
			    					<label for="audittingPoint" class="col-md-2 control-label">審核中點數</label>
			    					<div class="col-md-8">
			      						<input type="text" readonly="readonly" class="form-control" id="audittingPoint" name="audittingPoint" placeholder="audittingPoint" value="${entity.audittingPoint}"/>
			      						<span class="help-block"><div class="text-danger"></div></span>
			    					</div>
								</div>	
																
								<div class="form-group required">
									<label for="enabled" class="col-md-2 control-label">狀態 </label>
									<div class="col-md-8">
										<select id="enabled" name=enabled class="form-control">
											<option value="true" ${entity.enabled == true ? 'selected': ''}>使用</option>
											<option value="false" ${entity.enabled == false ? 'selected': ''}>停用</option>
										</select>		
									</div>
								</div>
								
								<div class="form-group required">
									<label for="roles" class="col-md-2 control-label">角色 (Role)</label>
									<div class="col-md-7">
										<table name="roles">
											<tr>
												<td>
													<div class="panel panel-info">
														<div class="panel-heading text-center"><strong>未選擇</strong></div>
														<select multiple id="unselectedRoles" name="unselectedRoles" style="height:200px; width:350px" class="form-control">
															<c:forEach items="${unselectedRoles}" var="role">
																<option value="${role.id}">${role.name} (${role.code}) </option>
															</c:forEach>
														</select>
													</div>
												</td>
												
												<td class="col-md-1">
													<button type="button" class="btn btn-info" id="rolesAddBtn">
														<span class="glyphicon glyphicon-chevron-right"/>
													</button></br></br>
													
													<button type="button" class="btn btn-info" id="rolesRemoveBtn">
														<span class="glyphicon glyphicon-chevron-left"/>
													</button>
												</td>
												
												<td>
													<div class="panel panel-info">
														<div class="panel-heading text-center"><strong>已選擇</strong></div>											
														<select multiple id="selectedRoles" name="roles[].id" style="height:200px; width:350px" class="form-control" data-content-type="array">
															<c:forEach items="${selectedRoles}" var="role">
																<option value="${role.id}">${role.name} (${role.code})</option>
															</c:forEach>														
														</select>
													</div>
												</td>
											</tr>
										</table>
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
						<a href="<c:url value='/admin/user/list'/>" class="btn btn-lg btn-primary btn-warning btn-block" data-loading-text="Loading">返回</a>
					</div>
				</div>
			</div>


		</div>
	</div>


</body>
</html>
<script type="text/javascript">
$(function(){
	
	$('#rolesAddBtn').click(function() {
		return $("#unselectedRoles option:selected").remove().appendTo("#selectedRoles");
	});
	
	$('#rolesRemoveBtn').click(function() {
		return $("#selectedRoles option:selected").remove().appendTo("#unselectedRoles");
	});	
	
	$("#saveButton").bind("click", function() {
		$("#selectedRoles option").prop('selected', true); // to select all right options
		
		var $btn = $(this);
			$btn.button("loading");
			
		$.put("<c:url value='/admin/user'/>", "dataForm",
				function(data) {
					console.log(data.messages.length);
					if (data.messages.length==0){
						alert("SUCCESS");
						$btn.button("reset");
					}	
				}, function(data, textStatus, jqXHR) {
					$btn.button("reset");
				});
		
		$btn.button("reset");			
	});
	
})
	
</script>
