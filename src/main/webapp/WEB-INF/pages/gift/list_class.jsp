<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>
<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>
</head>

<body>

<!-- 	<div id="wrap"> -->
<%-- 		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>		 --%>
<!-- 		<div div class="container-fluid" style="width:100%;height:100%;position:absolute;padding:0"> -->
<!-- 			<div class="col-md-3"><div> -->
<!-- 			<div class="col-md-9"> -->
<!-- 					<div class="row"> -->
<%-- 						<c:forEach items="${modelv}" var="modelv" varStatus="status"> --%>
<!-- 						<div class="col-md-3" style="width:300px ; border: #5C8DEC 1px solid; border-radius: 10px; margin-left: 20px"> -->
<!-- 							<div style="height: 300px; "> -->
<!-- 								<div class="col-md-12" style="height: 220px;"> -->
<%-- 									<img id="image" style="margin-top: 5px ; height: 200px; width: 200px;" src='<c:url value="${model.image1}"/>'> --%>
<!-- 								</div> -->
<!-- 								<div> -->
<!-- 									<div  class="col-md-8" style="height: 20px;"> -->
<%-- 										<span id="giftName" style="text-align: center;">一二三四五六七${model.name}</span></br> --%>
<!-- 									<img alt="" src="/resources/pic/積點專區/點數(小).png" width="15" height="15"> -->
<%-- 										<span id="points" style="text-align: center;">${model.bonus}</span> --%>
<!-- 									</div> -->
<!-- 									<div class="col-md-4" style="height: 20px; "> -->
<!-- 										<p id="placeOrder" class="btn btn-sm btn-primary" style="text-align: center;">立即兌換</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 						<c:if test="${status.count%3==0}"> --%>
<!-- 					</div><div class="row"> -->
<%-- 						</c:if> --%>
<%-- 						</c:forEach> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
				<table
					style="border: 2px solid; margin-left: 200px; cellpadding: 10px">
					<h3 style="margin-left: 200px;">更多商品</h3>
					<tr>
						<c:forEach items="${models}" var="model" varStatus="status">
							<td style="border: 1px solid; margin-left: 5px"><div
									style="height: 300px; width: 300px;">
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">積點商品名稱:${model.name}</p>
									</div>
<!-- 									<div style="height: 20px; width: 300px;"> -->
<%-- 										<p style="text-align: center;">品牌:${model.brand}</p> --%>
<!-- 									</div> -->
									<div style="height: 240px; width: 300px;">
										<p style="text-align: center;">圖片:<br/><img id="image" style="height: 200px; width: 200px;"
										 src='<c:url value="${model.image1}"/>'></p>
									</div>
									<div style="height: 20px; width: 300px;">
										<p style="text-align: center;">商品兌換點數:${model.bonus}</p>
									</div>
<!-- 									<div style="height: 20px; width: 300px;"> -->
<%-- 										<p style="text-align: center;">累積兌換次數:${model.exchangeCount}</p> --%>
<!-- 									</div> -->
<!-- 									<div style="height: 20px; width: 300px;"> -->
<%-- 										<p style="text-align: center;">最大購買數量:${model.exchangePersonMax}</p> --%>
<!-- 									</div> -->
								</div></td>
								<c:if test="${status.count%3==0}">
									</tr><tr>
								</c:if>
						</c:forEach>
					</tr>
				</table>
			
		
</body>
</html>






