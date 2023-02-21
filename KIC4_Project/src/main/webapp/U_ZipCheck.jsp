<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.*,member.*"%>
<jsp:useBean id="memMgr" class="member.MemberDBMgr"/>

<%//검색전->check=y , 검색후->check=n
  //Register.jsp->script.js->check,area3
  request.setCharacterEncoding("utf-8");

String check=request.getParameter("check");//y
String area3=request.getParameter("area3");

//검색된 데이터를 화면에 출력
Vector zipcodeList = memMgr.zipcodeRead(area3);
int totalList = zipcodeList.size();//검색갯수
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>우편번호 검색 </title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
<script>

   function dongCheck(){
          if(document.zipForm.area3.value==""){
                 alert("동이름을 입력하세요");
                document.zipForm.area3.focus();
                   return;
                   }
       document.zipForm.submit();//ZipCheck.jsp  
   }
 
  function sendAddress(zipcode,area1,area2,area3,area4){
 var address=area1+" "+area2+" "+area3+" "+area4;
  opener.document.regForm.zipcode.value=zipcode;
  opener.document.regForm.addr.value=addr;
   self.close();
  }
</script>
</head>
<body bgcolor="FFFFCC">
<center>
<b>우편번호 찾기</b>
<table>
   <form name="zipForm" action="U_ZipCheck.shop" method="post">
       <tr>
           <td><br>
           동이름 입력:<input name="area3" type="text">
           <input type="button" value="검색" onclick="dongCheck();">
           </td>
       </tr>
       <input type="hidden" name="check" value="n">
    </form>
  <!-- 찾은 데이터를 보여주는 코딩(검색시작) --> 
  <%
   if(check.equals("n")){ //검색버튼을 눌렀다면
     if(zipcodeList.isEmpty()){
  %>
    <tr><td align="center"><br>
	검색된 결과가 없습니다.
	</td></tr>

  <% }else { %>
  <tr><td align="center"><br>
  *검색후 , 아래 우편번호를 클릭하면 자동으로 입력됩니다.</td></tr>

  <!-- 실제 데이터를 출력하는 코딩 -->
  <%
     for(int i=0;i<totalList;i++){
	 ZipcodeDTO zipDTO = (ZipcodeDTO)zipcodeList.elementAt(i);
	 String tempZipcode =zipDTO.getZipcode().trim();
	 String tempArea1 = zipDTO.getArea1().trim();
	 String tempArea2 = zipDTO.getArea2().trim();
	 String tempArea3 = zipDTO.getArea3().trim();
	 String tempArea4 = zipDTO.getArea4().trim();
  %>
   <tr><td>
 <a href="JavaScript:sendAddress('<%=tempZipcode%>','<%=tempArea1%>','<%=tempArea2%>','<%=tempArea3%>','<%=tempArea4%>')">
 
 <%=tempZipcode%>&nbsp;<%=tempArea1%>&nbsp;<%=tempArea2%>&nbsp;<%=tempArea3%>&nbsp;
 <%=tempArea4%></a><br>
  <%
       }//for
     }//else { %
   }// if(check.equals("n")){
  %>
 </td></tr>
 <tr><td align = "center"><br>
 <a href="javascript:this.close();">닫기</a><tr></td>
 </table>
 </center>
 </body>
 </html>