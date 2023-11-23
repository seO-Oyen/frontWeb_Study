<%@page import="frontweb.Dept"%>
<%@page import="frontweb.database.PreparedStmtDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String deptnoStr = request.getParameter("deptno");
String dname = request.getParameter("dname");
String loc = request.getParameter("loc");

int deptno = 0;
if (deptnoStr != null && !deptnoStr.trim().equals(""))
	deptno = Integer.parseInt(deptnoStr);

boolean isIns = false;
if (deptnoStr != null) {
	PreparedStmtDao dao = new PreparedStmtDao();
	int deptCnt = dao.insertDept(new Dept(deptno, dname, loc));
	isIns = deptCnt > 0;
}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.1.1.js"></script>
<script>
	var isIns = <%= isIns %>
	if (isIns) {
		if (!confirm("등록 성공\n계속 하시겠습니까?")) {
			location.href = "a05_deptList.jsp";
		}
	}
</script>
</head>
<body>
	<form>
		<table border>
			<tr><th>부서번호</th><td><input name="deptno" /></td></tr>
			<tr><th>부서이름</th><td><input name="dname" /></td></tr>
			<tr><th>부서위치</th><td><input name="loc" /></td></tr>
		</table>
		  
		<input type="submit" value="등록" />
		<input type="button" value="메인리스트화면"
    			onclick="location.href='a05_deptList.jsp'" />
	</form>

</body>
</html>