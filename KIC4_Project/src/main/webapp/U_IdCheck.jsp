<%@page contentType="text/html;charset=euc-kr"%>
<jsp:useBean id="memDao"
             class="member.MemberDAO"/>
<%
   //scrip.js->memid���� �޾Ƽ� ó��
   String memid = request.getParameter("memid");
   System.out.println("memid="+memid);
   
   //�ߺ�idüũ �޼��� ȣ��
   boolean check = memDao.checkId(memid);
   System.out.println("check="+check);//false

%>
<HTML>
 <HEAD>
  <TITLE> �ߺ�IDüũ </TITLE>
<script language="JavaScript" src="script.js?ver=1.0"></script>
 </HEAD>
 <BODY bgcolor="#FFFFCC">
  <br>
  <center>
  <b><%=memid%></b>
  <%
    if(check){
    out.println("�� �̹� �����ϴ� ID�Դϴ�<p>");
     }else{
    out.println("�� ��밡�� �մϴ�<p>");
	 }
  %>
    <a href="#" onClick="self.close()">�ݱ�</a>
  </center>
 </BODY>
</HTML>
