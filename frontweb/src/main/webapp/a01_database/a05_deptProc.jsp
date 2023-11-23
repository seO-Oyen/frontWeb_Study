<%@page import="frontweb.Dept"%>
<%@page import="frontweb.database.PreparedStmtDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String proc= request.getParameter("proc");
String deptnoStr = request.getParameter("deptno");
String dname= request.getParameter("dname");
String loc = request.getParameter("loc");
int deptno = 0;

if (proc == null) proc = "";

boolean isProc = false;
if (deptnoStr != null) {
	deptno = Integer.parseInt(deptnoStr);
	PreparedStmtDao dao = new PreparedStmtDao();
	if(proc.equals("upt")) {
		if (dao.updateDept01(new Dept(deptno, dname, loc)) == 0) {
			proc = "실패";
		}
	}
	if (proc.equals("del")) {
		if (dao.deleteDept01(deptno) == 0) {
			proc = "실패";
		}
	}
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.1.1.js"></script>
<script>
var proc = "<%= proc %>"
if (proc != "") {
	if(proc == "upt") {
		if(comfirm("수정 성공\n상세화면은 확인, 리스트 조회화면은 취소")) {
			location.href = "a05_deptDetail.jsp?deptno=<%= deptno %>"
		} else {
			location.href = "a05_deptList.jsp"
		}
	} else if (proc == "del") {
		alert("삭제 성공")
		location.href = "a05_deptList.jsp"
	} else {
		alert(proc + "\n조회화면으로 이동")
		location.href = "a05_deptList.jsp"
	}
}
</script>
</head>
<body>
	<h2></h2>

</body>
</html>