<%@page import="frontweb.vo.EmpDTO"%>
<%@page import="frontweb.database.PreparedStmtDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String empnoStr = request.getParameter("empno");
String ename = request.getParameter("ename");
String job = request.getParameter("job");
String mgrStr = request.getParameter("mgr");
String hiredateStr = request.getParameter("hiredate");
String salStr = request.getParameter("sal");
String commStr = request.getParameter("comm");
String deptnoStr = request.getParameter("depstno");

int empno, mgr, deptno;
double sal, comm;
sal = comm = empno = mgr = deptno = 0;
if (empnoStr != null && !empnoStr.trim().equals(""))
	empno = Integer.parseInt(empnoStr);
if (mgrStr != null && !mgrStr.trim().equals(""))
	mgr = Integer.parseInt(mgrStr);
if (salStr != null && !salStr.trim().equals(""))
	sal = Double.parseDouble(salStr);
if (commStr != null && !commStr.trim().equals(""))
	comm = Double.parseDouble(commStr);
if (deptnoStr != null && !deptnoStr.trim().equals(""))
	deptno = Integer.parseInt(deptnoStr);

boolean isInsert = false;
if (empnoStr != null) {
	PreparedStmtDao dao = new PreparedStmtDao();
	dao.insertEmp01(new EmpDTO(
				empno, ename, job, mgr, hiredateStr, sal, comm, deptno
			));
	isInsert = true;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/frontweb/com/a01_common.css">
<script src="/frontweb/com/jquery-3.6.0.js"></script>
<script>
$(document).ready( function(){
	$("h2").text("사원정보 등록");
})
</script>
</head>
<body>
    <h2 align="center"></h2>
    <form>
    <table border width="50%" align="center">
    	<col width="40%">
    	<tr><th>사원번호</th><td><input type="number" name="empno" /></td></tr>
    	<tr><th>사원명</th><td><input type="text" name="ename" /></td></tr>
    	<tr><th>직책명</th><td><input type="text" name="job" /></td></tr>
    	<tr><th>관리자번호</th><td><input type="number" name="mgr" /></td></tr>
    	<tr><th>입사일</th><td><input type="text" name="hiredatestr" /></td></tr>
    	<tr><th>급여</th><td><input type="number" name="sal" /></td></tr>
    	<tr><th>보너스</th><td><input type="number" name="comm" /></td></tr>
    	<tr><th>부서번호</th><td><input type="number" name="deptno" /></td></tr>
    	<tr><th colspan="2">
    		<input type="submit" value="등록" />
    		<input type="button" value="메인리스트화면"
    			onclick="location.href='a01_empList.jsp'" />
    	</th></tr>
    </table>
    </form>

</body>
</html>