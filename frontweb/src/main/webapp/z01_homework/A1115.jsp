<%@page import="frontweb.vo.EmpHire"%>
<%@page import="frontweb.Emp"%>
<%@page import="java.util.List"%>
<%@page import="frontweb.homework.A1115"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../jquery/chap05/jquery-3.1.1.js"></script>
</head>
<body>

<!-- 1) **사용자 이름 출력하기**: 사용자로부터 이름을 입력받아, "안녕하세요, [이름]님!" 이라고 출력하는 페이지 만들기. -->
<%-- 
<h2>문제 1번</h2>

<form>
	<input type="text" placeholder="이름을 입력하세요." name="userName">
	<input type="submit" value="전송">
</form>
<%
String userName = request.getParameter("userName");
if(userName != null) {
%>
<div>안녕하세요, <%= userName %>님!</div>
<% } %>
--%>

<!-- 2) **간단한 계산기**: 두 숫자와 연산자(+, -, *, /)를 입력받아 결과를 출력하는 JSP 페이지 만들기. -->
<%-- 
<h2>문제 2번</h2>
<form>
	<input type="text" placeholder="숫자 1" name="num01">
	<input type="text" placeholder="숫자 2" name="num02">
	<input type="text" name="a">
	<input type="submit" value="전송">
</form>
<%
if (request.getParameter("num01") != null && request.getParameter("num02") != null) {
int num01 = Integer.parseInt(request.getParameter("num01"));
int num02 = Integer.parseInt(request.getParameter("num02"));
String a = request.getParameter("a");
double result = 0;

if (a.equals("+")) {
	result = num01 + num02;
} else if (a.equals("-")) {
	result = num01 - num02;
} else if (a.equals("*")) {
	result = num01 * num02;
} else if (a.equals("/")) {
	result = num01 / num02;
}
%>
<div>결과 : <%= result %></div>
<% } %>
--%>

<!-- 사용자 아이디와 비밀번호를 입력받아, 특정 아이디/비밀번호와 일치할 때 "로그인 성공"을, 그렇지 않으면 "로그인 실패"를 출력하는 페이지 만들기. -->
<%-- 
<h2>문제 3번</h2>
<form>
	<input type="text" placeholder="아이디" name="id"> <input
		type="text" placeholder="비밀번호" name="pwd"> <input
		type="submit" value="로그인">
</form>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
if ((id != null && !id.isEmpty()) && (pwd != null && !pwd.isEmpty())) {
	if (id.equals("himan") && pwd.equals("7777")) {
%>
<div><%=id%>님 로그인 되었습니다.
</div>
<%
	} else {
%>
<div>로그인이 실패하였습니다</div>
<%
	}
}
%>
--%>

<!-- 부서번호별 직원 조회 -->
<%-- 
<%
String deptnoStr = request.getParameter("deptno");
int deptno = 10;
if(deptnoStr != null) deptno = Integer.parseInt(deptnoStr);

A1115 dao = new A1115();
List<Emp> empList = dao.getEmpListByDeptno(deptno);
%>
<h2>문제 4번</h2>
<form>
	부서번호 : <select name="deptno">
		<option>10</option>
		<option>20</option>
		<option>30</option>
		<option>40</option>
	</select>
	<input type="submit" value="조회">
</form>

<table border>
	<tr><th>사원번호</th><th>사원명</th>
		<th>급여</th><th>부서번호</th></tr>
		<% for(Emp emp : empList) { %>
	<tr><td><%= emp.getEmpno() %></td>
		<td><%= emp.getEname() %></td>
		<td><%= emp.getSal() %></td>
		<td><%= emp.getDeptno() %></td></tr>
		<% } %>
</table>
<script>
	$("[name=deptno]").val("<%= deptno %>");
	$("[name=deptno]").change(function() {
		$("form").submit();
	})
</script>
--%>

<!-- 2-1 -->
<h2>문제 2-1번</h2>
<%
String job = request.getParameter("job");
if (job == null) job = "";

String hire_qua = request.getParameter("hire_qua");
if (hire_qua == null) hire_qua = "1";

A1115 dao = new A1115();

%>
<h2 align="center">사원정보 조회</h2>
<form>
<table align="center" width="40%" border>
	<tr><th>직책명</th><td><input type="text" name="job" value=""></td></tr>
	<tr><th>입사분기</th>
		<td><select name="hire_qua">
				<option value="1">1/4분기</option>
				<option value="2">2/4분기</option>
				<option value="3">3/4분기</option>
				<option value="4">4/4분기</option>
			</select></td></tr>
	<tr><th colspan="2"><input type="submit" value="조회"></th></tr>
</table>
</form>

<table align="center" width="80%" border>
	<col width="25%"><col width="25%"><col width="25%"><col width="25%">
	<tr><th>사원번호</th>
		<th>직책명</th>
		<th>입사분기</th>
		<th>입사일</th></tr>
	<% for (EmpHire emp : dao.getEmpHireInfo(job, hire_qua)) { %>
	<tr><td><%= emp.getEmpno() %></td>
		<td><%= emp.getJob() %></td>
		<td><%= emp.getHire_qua() %></td>
		<td><%= emp.getHire_str() %></td></tr>
	<% } %>
</table>

<script>
	$("[name=job]").val("<%= job %>");
	$("[name=hire_qua]").val("<%= hire_qua %>");
	$("[name=hire_qua]").change(function() {
		$("form").submit();
	})
</script>

</body>
</html>