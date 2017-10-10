<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="/resources/pic/icon.png">
<script type="text/javascript" src="<c:url value='/resources/jquery/jquery-1.11.1.js' />"></script>
<script type="text/javascript" src='<c:url value="/resources/jquery/jquery-ui-1.10.4.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/bootstrap-3.3.7/dist/js/bootstrap.min.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/jsgrid-1.5.3/jsgrid.min.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/log4javascript-1.4.13/log4javascript.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/softleader/jquery.softleader.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/form2js/form2js.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/bodymovin/bodymovin.js" />'></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<script>
var log = log4javascript.getLogger("<c:out value='${pageContext.request.servletPath}' />");
log.setLevel(log4javascript.Level.ALL);
var appender = new log4javascript.BrowserConsoleAppender();
appender.setLayout(new log4javascript.PatternLayout('%d{HH:mm:ss} %-5p %m'));
log.addAppender(appender);

log.debug("Current page is <c:out value='${pageContext.request.servletPath}' />");


window.$log = log;

/*SweetAlert套用*/ 

window.legacyAlert = window.alert;

	window.alert = function(msg, title, type, params) {
	    	var title = (title == null) ? 'Error' : title;
	    	var type = (type == null) ? 'Warning' : type;
	   	 	swal($.extend({
	            	title: title,
	            	text: msg,
	            	type: type
	        	}, params || {})
	    	);
		};

function alertMessage(message) {
	alert(message);
}

// 解決dialog閃一下消失

$(".dialog-init").css("display","block");

</script>