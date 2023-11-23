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
	학생명 : <input type="text" name="student">
	국어 : <input type="text" name="kor" value="0">
	영어 : <input type="text" name="eng" value="0">
	수학 : <input type="text" name="math" value="0"	>
	<input type="submit" value="성적처리">
</form>

<%
String name = request.getParameter("student");
String korStr = request.getParameter("kor");
String engStr = request.getParameter("eng");
String mathStr = request.getParameter("math");

if (name == null) name = "";

int kor = 0;
if (korStr != null && !korStr.equals("0")) kor = Integer.parseInt(korStr); 
int eng = 0;
if (engStr != null && !engStr.equals("0")) eng = Integer.parseInt(engStr);
int math = 0;
if (mathStr != null && !mathStr.equals("0")) math = Integer.parseInt(mathStr);
int sum = kor + eng + math;
double avg = sum / 3.0;
%>

<%= name %>의 성적<br>
국어 : <%= kor %><br>
영어 : <%= eng %><br>
수학 : <%= math %><br>
총점 : <%= sum %><br>
평균 : <%= avg %><br>

</body>
</html>