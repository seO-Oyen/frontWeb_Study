<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="frontweb.database.PreparedStmtDao"
    import="frontweb.vo.EmpDTO"
    %>
    
<%
String empnoStr = request.getParameter("empno");
int empno = 0;
EmpDTO emp = new EmpDTO();

if (empnoStr != null) empno = Integer.parseInt(empnoStr);
if (empnoStr != null) {
	empno = Integer.parseInt(empnoStr);
	PreparedStmtDao dao = new PreparedStmtDao();
	emp = dao.getEmp(empno);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.1.1.js"></script>
<script>
$(document).ready( function(){
   $("h2").text("사원정보상세<%= empno %>");
   $("#uptBtn").click(function() {
	   if(confirm("수정하시겠습니까?")) {
		   $("[name=proc]").val("upt")
		   $("form").attr("action", "a01_empProc.jsp");
		   $("form").submit();
	   }
   })
   $("#delBtn").click(function() {
	   if(confirm("삭제하시겠습니까?")) {
		   $("[name=proc]").val("del")
		   $("form").attr("action", "a01_empProc.jsp");
		   $("form").submit();
	   }
   })
})
</script>
</head>
<body>
    <h2></h2>
<form>
	<input type = "hidden" name="proc"/>
    <table  align="center" border width="50%">
       <col width="40%">
       <tr><th>사원번호</th><td><input type="number" name="empno" value="<%=emp.getEmpno()%>"/></td></tr>
       <tr><th>사원명</th><td><input type="text" name="ename" value="<%=emp.getEname()%>"/></td></tr>
       <tr><th>직책명</th><td><input type="text" name="job" value="<%=emp.getJob()%>"/></td></tr>
       <tr><th>관리자번호</th><td><input type="number" name="mgr" value="<%=emp.getMgr()%>"/></td></tr>
       <tr><th>입사일</th><td><input type="date" name="hiredatestr" value="<%=emp.getHiredateStr()%>"/></td></tr>
       <tr><th>급여</th><td><input type="number" name="sal" value="<%=emp.getSal()%>"/></td></tr>
       <tr><th>보너스</th><td><input type="number" name="comm" value="<%=emp.getComm()%>"/></td></tr>
       <tr><th>부서번호</th><td><input type="number" name="deptno" value="<%=emp.getDeptno()%>"/></td></tr>
       <tr><th colspan="2">
            <input type="button" id="uptBtn" value="수정"/>
            <input type="button" id="delBtn" value="삭제"/>
            <input type="button" value="메인리스트화면" 
               onclick="location.href='a01_empList.jsp'"/>       
          </th></tr>
    </table>
    </form>
</body>
</html>