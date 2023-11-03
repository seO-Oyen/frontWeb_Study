<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- z04_schProduct.jsp?panem=사과
	로 호출되었을 때, 이동되는 페이지와 요청값을 받는 처리 -->
	<h2>넘겨온 물건명 : ${ param.pname }</h2>
	<h2>넘겨온 물건명 : <%= request.getParameter("pname") %></h2>

</body>
</html>