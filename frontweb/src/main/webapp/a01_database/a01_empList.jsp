<%@page import="frontweb.Emp"%>
<%@page import="frontweb.database.PreparedStmtDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
PreparedStmtDao dao = new PreparedStmtDao();
%>

<%-- 
<h3 align="center">사원명 : <%= request.getParameter("ename") == null ?
			"" : request.getParameter("ename") %></h3>
<h3 align="center">직책명 : <%= request.getParameter("job") == null ?
			"" : request.getParameter("job") %></h3>
<h3 align="center">부서 : <%= request.getParameter("deptno") == null ?
			"" : request.getParameter("deptno") %></h3>
--%>

<%
String ename = request.getParameter("ename");
if (ename == null)
	ename = "";

String job = request.getParameter("job");
if (job == null)
	job = "";

String deptnoStr = request.getParameter("deptno");
int deptno = 0;
if (deptnoStr != null)
	deptno = Integer.parseInt(deptnoStr);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.1.1.js"></script>
<script>
	$(document).ready(function() {
		$("[name=deptno]").val("<%= deptno %>")
	})
</script>
</head>
<body>

	<h2 align="center">사원정보 조회</h2>


	<form>
		<table width="40%" border align="center">
			<col width="40%">
			<col width="60%">
			<tr>
				<th>사원명</th>
				<td><input type="text" name="ename" value="<%=ename%>" /></td>
			</tr>
			<tr>
				<th>직책명</th>
				<td><input type="text" name="job" value="<%=job%>" /></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><select name="deptno" style="width: 85%;">
						<option value="0">전체</option>
						<option value="10">인사</option>
						<option value="20">재무</option>
						<option value="30">회계</option>
						<option value="40">기획</option>
				</select></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="검색" />
					<input type="button" value="등록" /></th>
			</tr>
		</table>
	</form>

	<br>
	<table border align="center">
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>직책명</th>
			<th>관리자번호</th>
			<th>급여</th>
			<th>보너스</th>
			<th>부서번호</th>
		</tr>

		<%
		for (Emp emp : dao.getEmpList(new Emp(ename, job, deptno))) {
		%>
		<tr ondblclick="goPage(<%= emp.getEmpno() %>)">
			<th><%=emp.getEmpno()%></th>
			<th><%=emp.getEname()%></th>
			<th><%=emp.getJob()%></th>
			<th><%=emp.getMgr()%></th>
			<th><%=emp.getSal()%></th>
			<th><%=emp.getComm()%></th>
			<th><%=emp.getDeptno()%></th>
		</tr>
		<%
		}
		%>
	</table>

<script type="text/javascript">
	function goPage(empno) {
		location.href = "a01_empDetail.jsp?empno=" + empno;
	}
</script>

</body>
</html>