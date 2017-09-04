<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="<c:url value="/resources/fckeditor/fckeditor.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form action="show.jsp" method="post" target="_blank"> 
<FCK:editor instanceName="fckEdit"  width="700" height="500"  toolbarSet="Default" />
<input type="submit" value="Submit"> 
</form> 

</body>
</html>