<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>요청 값 처리</h2>
<form>
	이름 : <input type="text" name="name" /> <br>
	<input type="submit" />

</form>

<h2>전송된 이름 : <%= request.getParameter("name") %></h2>

<!-- 
a03_requestExp.jsp
	물건명 : []
	가격 : []
	[구매]
	
	구매한 정보
	물건명 : @@@
	가격 : @@@
-->

</body>
</html>