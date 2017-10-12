<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i問題一覽</title>

<style>
#mainNavbar {
	border-color: #333333;
	background-color: #030033;
}
</style>
</head>

<body>
	
	<div class="container-fluid">
		<c:import url="/WEB-INF/pages/layout/navbar.jsp"></c:import>
		<div style="padding-top:8vh">
			<div class="col-lg-4"
				style="height: 100vh; display: table; table-layout: fixed;">
				<div style="display: table-cell; vertical-align: middle;">
					<h1>問題一覽</h1>
				</div>
			</div>
			<div class="col-lg-8 bg-info"
				style="height: 100vh; display: table; table-layout: fixed;">

					<div style="display: table-cell; vertical-align: middle;">

<%-- 						<c:forEach items="${questions}" var="model"> --%>
<!-- 						<tr></tr> -->
<%-- 						${model.id}, ${model.questionType}, ${model.askerEmail} <br/> --%>
<%-- 						</c:forEach> --%>
					</div>
<!-- 					 <table id="table1" class="table table-bordered table-striped table-hover" > -->
<!--             <thead> -->
<!--                 <tr> -->
<!--                     <th>ID</th> -->
<!--                     <th>QUESTION_TYPE</th> -->
<!--                     <th>ASKER_EMAIL</th> -->
<!--                     <th>POST_TIME</th> -->
<!--                 </tr> -->
<!--             </thead> -->
<!--             <tbody> -->
              
<!--             </tbody> -->
<!--         </table> -->
			</div>

<c:import url="/WEB-INF/pages/layout/footnavbar.jsp"></c:import>
		</div>
	</div>
<!-- 	  <script> -->
//         $(function () {
//             $('#table1').DataTable({
//                "ajax": "/question/list",
//                "columns": [
//                    { "questions": "ProductID" },
//                    { "questions": "ProductName" },
//                    { "questions": "UnitPrice" },
//                    { "questions": "UnitsInStock" }
//                ]
//             });
<!--             </script> -->


<thead>
                          <tr>
                             <th>ID</th>
                             <th>EMAIL</th>
                             <th>TYPE</th>
                             <th>CONTEXT</th>
                            
                          </tr>
                       </thead>
                       <tbody>   
                       </tbody>
                       <tfoot>
                       <tr>
                       <form name="myForm">
                        <td><input type="hidden" id="ProductID" name="ProductID"><span></span></td>
                        <td><input type="text" class="form-control" id="ProductName" name="ProductName" placeholder="產品名稱"></td>
                        <td><input type="text" style="width:100px" class="form-control" id="UnitPrice" name="UnitPrice" placeholder="價格"></td>
                        <td><input type="text" style="width:100px" class="form-control" id="UnitsInStock" name="UnitsInStock" placeholder="庫存量"></td>
                        <td><button id="buttonAdd" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span></button>
                          <button id="buttonUpdate" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span></button></td>
                       </tr>
                       </form>
                       </tfoot>
                   </table>
</body>
</html>
