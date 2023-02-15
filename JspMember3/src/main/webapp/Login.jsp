<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    //로그인 했는지 안했는지를 체크 -> include 지시어를 이용해서 파일로 불러오기
    //session.setAttribute("idKey", mem_id); LoginProc.jsp를 거쳤는지?
  /*   String mem_id=(String)session.getAttribute("idKey");
    System.out.println("Login.jsp의 mem_id =>"+mem_id); */
%>

<HTML>
 <HEAD>
  <TITLE> 로그인 </TITLE>
<link href="style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js">
</SCRIPT>
<!-- function memberReg()안돼면 여기에 쓰기 -->
 </HEAD>

 <BODY onload="document.login.mem_id.focus()" bgcolor="#FFFFCC">
  <center>
  <!-- mem_id의 상태에따라 로그인 처리 -->
  <br><br><br>
<%--  <%
	//Login.jsp->LoginProc.jsp->LoginSuccess.jsp
	if(mem_id!=null){
%> --%>
<b>${request.mem_id}</b>님 환영합니다.<p>
	당신은 제한된 기능을 사용할 수가 있습니다.
<a href="MemberUpdate.jsp">회원수정</a>
<a href="DelCheckForm.jsp?mem_id=${request.mem_id}">회원탈퇴</a>
<a href="Logout.jsp">로그아웃</a>
<%-- <% }else {%> --%>

  <!-- 로그인 안된 상태 -->
     <TABLE>
    <form name="login" method="post" action="LoginProc.shop">
     <TR>
		<TD align="center" colspan="2">
		<h4>로그인</h4></TD>
     </TR>

     <TR>
		<TD>아이디</TD>
		<TD><INPUT TYPE="text" NAME="mem_id"></TD>
     </TR>
     <TR>
		<TD>비밀번호</TD>
		<TD><INPUT TYPE="password" NAME="mem_passwd"></TD>
     </TR>
     <TR>
		<TD colspan="2"><div align="center">
		<INPUT TYPE="button" value="로그인" onclick="loginCheck()">&nbsp;
		<INPUT TYPE="button" value="회원가입" onclick="memberReg()">
		</div>
		</TD>
     </TR>
	 </form>
     </TABLE>
     <%-- <% }%> --%>
  </center>
 </BODY>
</HTML>
