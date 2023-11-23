<%@page import="frontweb.Dept"%>
<%@page import="frontweb.database.PreparedStmtDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
PreparedStmtDao dao = new PreparedStmtDao();
%>

<h2>부서정보</h2>
<form>
	<table border>
		<col width="30%">
		<tr><th>부서명</th><td><input type="text" name="deptName"></td></tr>
		<tr><th>부서위치</th><td><input type="text" name="loc"></td></tr>
		<tr><th colspan="2">
			<input type="button" onclick="location.href='a05_deptInsert.jsp'" value="등록">
			<input type="submit" value="검색">
		</th></tr>
	</table>
</form>
<% 
String name = request.getParameter("deptName");
String loc = request.getParameter("loc");
if (name == null) name = "";
if (loc == null) loc = "";


%>
<table border>
		<col width="33%"><col width="33%"><col width="34%">
		<tr><th>부서번호</th><th>부서이름</th><th>부서위치</th></tr>
		<% for (Dept dept : dao.getDept01List(name, loc)) { %>
		<tr ondblclick="goPage(<%= dept.getDeptno() %>)">
			<td><%= dept.getDeptno() %></td>
			<td><%= dept.getDname() %></td>
			<td><%= dept.getLoc() %></td></tr>
		<% } %>
</table>

<script>
	function goPage(deptno) {
		location.href = "a05_deptDetail.jsp?deptno=" + deptno;
	}
</script>


</body>
</html>