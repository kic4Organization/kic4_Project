<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="hewon.MemberDAO" %>

<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>
<%
//	한글처리
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mem" class="hewon.MemberDTO" />
<jsp:setProperty name="mem" property="*" />
<%
	MemberDAO memMgr=new MemberDAO();
	boolean check=memMgr.memberInsert(mem);//이미 메모리에 저장된 상태
%>
<!DOCTYPE html>
<html>
<body bgcolor="#FFFFCC">
<br>
<center>
<%
	if(check){//회원가입에 성공했다면
		out.println("<b>회원가입을 축하드립니다.</b><p>");
		out.println("<a href=Login.jsp>로그인</a>");
	}else{//회원가입 실패라면
		out.println("<b>다시 입력하여 주십시오.</b><p>");
		out.println("<a href=Register.jsp>다시 가입</a>");
	}

%>
</center>
</body>
</html>