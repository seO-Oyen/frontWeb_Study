<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="frontweb.Person" 
	import="java.util.*"
	import="frontweb.Emp" 
	import="frontweb.dao.A04_PreparedDao"%>
<%
A04_PreparedDao dao = new A04_PreparedDao();
System.out.println(dao.getEmpList().size());

for (Emp emp : dao.getEmpList()) {
	System.out.print(emp.getEmpno() + "\t");
	System.out.print(emp.getEname() + "\t");
	System.out.print(emp.getJob() + "\t");
	System.out.println(emp.getSal());
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--
	jsp에서 사용하는 주석처리
--%>
<body>
	<%
		// html에서 자바 코드 사용할때 쓰는 script
		Person p01 = new Person("홍길동", 25, "서울");
	%>
	<h2>단일 객체 사용</h2>
	<h3>
		이름 :
		<%= p01.getName() %></h3>
	<h3>
		나이 :
		<%=p01.getAge()%></h3>
	<h3>
		사는곳 :
		<%=p01.getLoc()%></h3>

	<%
	List<Person> plist = new ArrayList<Person>();

	plist.add(new Person("김길동", 30, "부산"));
	plist.add(new Person("신길동", 20, "제주"));
	plist.add(new Person("오길동", 25, "광주"));
	%>

	<h2>리스트형 객체 사용</h2>
	<table border>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>사는곳</th>
		</tr>
		<tr>
			<td>홍길동</td>
			<td>25</td>
			<td>서울</td>
		</tr>
		<tr>
			<td><%=p01.getName()%></td>
			<td><%=p01.getAge()%></td>
			<td>p01.getLoc</td>
		</tr>
		<%
		for (int i = 0; i < plist.size(); i++) {
		%>
		<tr>
			<td><%=plist.get(i).getName()%></td>
			<td><%=plist.get(i).getAge()%></td>
			<td><%=plist.get(i).getLoc()%></td>
		</tr>
		<%
		}
		%>

	</table>

	<h2>DB연동 객체 사용</h2>
	<form> 
	<!--
		form에 action(요청을 호출하는 페이지) 저장하지 않으면
		현재 페이지를 호출
	-->
		사원명 : <input type="text" name="ename" value="${ param.ename }" /> <br>
		<input type="submit" value="검색">
	</form>
	<table border>
		<tr><th>사원번호</th><th>사원명</th><th>직책</th><th>급여</th></tr>
		<tr><td>7780</td><td>홍길동</td><td>대리</td><td>4000</td></tr>

		<%
			String ename = request.getParameter("ename");
			if(ename==null) ename="";
			for(Emp emp:dao.getEmpList(ename)){ 
      	%>	
			<tr><td><%= emp.getEmpno() %></td>		
				<td><%= emp.getEname() %></td>
				<td><%= emp.getJob() %></td>
				<td><%= emp.getSal() %></td></tr>
		<% } %>
	</table>

</body>
</html>