<%@page contentType="text/html;charset=euc-kr"%>
<jsp:useBean id="memDao"
             class="member.MemberDAO"/>
<%
   //scrip.js->memid값을 받아서 처리
   String memid = request.getParameter("memid");
   System.out.println("memid="+memid);
   
   //중복id체크 메서드 호출
   boolean check = memDao.checkId(memid);
   System.out.println("check="+check);//false

%>
<HTML>
 <HEAD>
  <TITLE> 중복ID체크 </TITLE>
<script language="JavaScript" src="script.js?ver=1.0"></script>
 </HEAD>
 <BODY bgcolor="#FFFFCC">
  <br>
  <center>
  <b><%=memid%></b>
  <%
    if(check){
    out.println("는 이미 존재하는 ID입니다<p>");
     }else{
    out.println("는 사용가능 합니다<p>");
	 }
  %>
    <a href="#" onClick="self.close()">닫기</a>
  </center>
 </BODY>
</HTML>
