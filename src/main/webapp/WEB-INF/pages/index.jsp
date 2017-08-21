<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<a href="/">Triple i index</a>
Hello World <br/>

1+1 = <c:out value="${1+1}"/> <br/>

1+2 = ${1+2 }

<a href='/product'>開始篩選儲蓄險</a>

<br/>
<br/>
<br/>

<ul>
	<li><a href='/insurer/list'>各公司資訊</a></li>
	<li><a href='/product/list'>商品專區</a></li>
	<li><a href='/gift/list'>積點專區</a></li>
	<li><a href='/article/list'>文章專欄</a></li>
	<li><a href='#'>登入</a></li>
</ul>
<br/>
<br/>
<br/>
<a href='/qa'>線上客服</a>