<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
	물건명 : <input type="text" name="item">
	가격 : <input type="text" name="price">
	개수 : <input type="text" name="cnt">
	<input type="submit" value="구매">	
</form>

<%
String name = request.getParameter("item");
String priceStr = request.getParameter("price");
String cntStr = request.getParameter("cnt");

if (name == null) name = "";
int price = 0;
if (priceStr != null) price = Integer.parseInt(priceStr);
int cnt = 1;
if (cntStr != null) cnt = Integer.parseInt(cntStr);
int total = price * cnt;

%>
<h2>구매한 정보</h2>
물건명 : <%= name %> <br>
가격 : <%= total %>원

</body>
</html>