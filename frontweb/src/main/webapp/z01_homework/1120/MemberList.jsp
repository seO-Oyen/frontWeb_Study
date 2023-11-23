<%@page import="frontweb.database.A1120Dao"%>
<%@page import="frontweb.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
A1120Dao dao = new A1120Dao();
List<Member> members = dao.getMemberList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.1.1.js"></script>
<script>
	
</script>
</head>
<body>
	<form>
		<table border>
			<tr><th>번호</th><td><input type="text"></td></tr>
			<tr><th>이름</th><td><input type="text"></td></tr>
			<tr><th>아이디</th><td><input type="text"></td></tr>
			<tr><th>권한</th><td>
				<select>
					<option>관리자</option>
					<option>유저</option>
				</select>
			</td></tr>
			<tr><th colspan=2><input type="button" value="검색"></th></tr>
			
		</table>
	
	</form>

	<br>
	
	<table border>
		<tr><th>번호</th>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>권한</th>
			<th>포인트</th></tr>
		<%
		for (Member member : members) {
		%>
		<tr><td><%= member.getMno() %></td>
			<td><%= member.getName() %></td>
			<td><%= member.getId() %></td>
			<td><%= member.getPwd() %></td>
			<td><%= member.getAuth() %></td>
			<td><%= member.getPoint() %></td></tr>
		<%
		}
		%>
	</table>

</body>
</html>