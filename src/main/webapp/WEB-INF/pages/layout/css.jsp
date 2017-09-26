<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href='<c:url value="/resources/bootstrap-3.3.7/dist/css/bootstrap.min.css"/>' rel="stylesheet">
<link href='<c:url value="/resources/jsgrid-1.5.3/jsgrid.min.css"/>' rel="stylesheet">
<link href='<c:url value="/resources/jsgrid-1.5.3/jsgrid-theme.min.css"/>' rel="stylesheet">
<link href='<c:url value="/resources/jquery/jquery-ui.1.11.2.css"/>' rel="stylesheet">
 


<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
body{
	font-family: 'Noto Sans TC', serif;
	//padding-top: 50px;
}
body::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	background-color: #F5F5F5;
	border-radius: 10px;
}

body::-webkit-scrollbar
{
	width: 10px;
	background-color: #F5F5F5;
}

body::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #9D9D9D;
}


#style-1::-webkit-scrollbar-track
{
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
	background-color: #F5F5F5;
	border-radius: 10px;
}

#style-1::-webkit-scrollbar
{
	width: 10px;
	background-color: #F5F5F5;
}

#style-1::-webkit-scrollbar-thumb
{
	border-radius: 10px;
	-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
	background-color: #9D9D9D;
}
</style>