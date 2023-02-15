<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="hewon.MemberDAO" %>

<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>
<%
//	MemberUpdate.jsp=>MemberUpdateProc.jsp
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mem" class="hewon.MemberDTO" />
<jsp:setProperty name="mem" property="*" />
<%
//추가
	String mem_id=request.getParameter("mem_id");
	System.out.println("MemberUpdateProc.jsp의 mem_id => "+mem_id);

	MemberDAO memMgr=new MemberDAO();
	boolean check=memMgr.memberUpdate(mem);//회원수정 메서드
	System.out.println("MemberUpdate.jsp의 회원수정유무(check) => "+check);
%>
<!DOCTYPE html>
<html>
<body bgcolor="#FFFFCC">
<br>
<center>
<%
	if(check){//회원수정에 성공했다면
%>
	<script>
		alert("성공적으로 수정되었습니다.!");
		location.href="Login.jsp";
	</script>
<% }else{//회원수정이 실패라면%>
		<script>
			alert("수정도중 에러가 발생이 되었습니다.");
			history.back();
		</script>
<%}%>
</center>
</body>
</html>