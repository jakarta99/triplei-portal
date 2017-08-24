<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="<c:url value='/resources/jquery/jquery-1.11.1.js' />"></script>
<script type="text/javascript" src='<c:url value="/resources/jquery/jquery-ui-1.10.4.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/bootstrap-3.3.7/dist/js/bootstrap.min.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/jsgrid-1.5.3/jsgrid.min.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/log/log4javascript.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/softleader/jquery.softleader.js" />'></script>



<script>
var log = log4javascript.getLogger("<c:out value='${pageContext.request.servletPath}' />");
var appender = new log4javascript.BrowserConsoleAppender();
log.addAppender(appender);
log.debug("Current page is <c:out value='${pageContext.request.servletPath}' />");

window.$log = log;
</script>