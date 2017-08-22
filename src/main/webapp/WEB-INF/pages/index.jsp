<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<c:import url="/WEB-INF/pages/layout/javascript.jsp"></c:import>
<c:import url="/WEB-INF/pages/layout/css.jsp"></c:import>
<title>Triple i</title>

</head>

<body>

	<div class="container-fluid">
		

		<div class="col-lg-4" style="height:100vh;display: table;table-layout: fixed;">
			<div style="display:table-cell;vertical-align:middle;">
			<h1>最專業的保險理財</h1>
			<h1>讓你的財富開始飛</h1>
			<div>TRIPLE-I為您準備了簡易的商品比較及公開透明的資訊，讓您輕鬆篩選商品，並擁有專業的保險團隊提供相關知識，即時為您解決理賠問題或保險疑問</div>
			<button>開始篩選儲蓄險</button>
			</div>
		</div>
		<div class="col-lg-8 bg-info" style="height:100vh;">
			<a href="/">Triple i index</a> Hello World <br />
			<ul>
				<li><a href='/insurer/list'>各公司資訊</a></li>
				<li><a href='/product/list'>商品專區</a></li>
				<li><a href='/gift/list'>積點專區</a></li>
				<li><a href='/article/list'>文章專欄</a></li>
				<li><a href='#'>登入</a></li>
			</ul>
			<br /> <br /> <br /> <a href='/qa'>線上客服</a>
		</div>

	</div>
</body>
</html>