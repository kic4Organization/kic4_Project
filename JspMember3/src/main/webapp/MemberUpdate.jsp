<%@ page contentType="text/html;charset=UTF-8"
			import="hewon.*" %>
<html>
<head>
<%
//소스를 변경해도 바로 반영 X -> 서버에서 기본적으로 전의 페이지를 불러오기
 response.setHeader("Cache-Control","no-cache");//메모리 저장 X
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>
<title>회원가입</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js?ver=2.0"></script>
</head>
<body bgcolor="#996600">
<br><br>
<%
    //로그인 했는지 안했는지를 체크 -> include 지시어를 이용해서 파일로 불러오기
    //session.setAttribute("idKey", mem_id); LoginProc.jsp를 거쳤는지?
    String mem_id=(String)session.getAttribute("idKey");
    System.out.println("Login.jsp의 mem_id =>"+mem_id);
    MemberDAO memMgr=new MemberDAO();
    MemberDTO mem=memMgr.getMember(mem_id);
%>

<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
      <table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="MemberUpdateProc.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF"><b>회원 수정</b></font></td>
          </tr>
          <tr> 
          <!-- 매개변수를 전달할려면 input box에 value에 저장된 것만
          		  request.getParameter("mem_id"); (X) <---null 
           -->
            <td width="16%">아이디</td>
            <td width="57%"><%=mem.getMem_id()%></td>
            <td width="27%">아이디를 적어 주세요.</td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="mem_passwd" size="15"
            			value="<%=mem.getMem_passwd()%>"> </td>
            <td>패스워드를 적어주세요.</td>
          </tr>
          <!-- 패스워드는 한번만 -->
            <td>이름</td>
            <td> <input type="text" name="mem_name" size="15"
            			value="<%=mem.getMem_name()%>"> </td>
            <td>고객실명을 적어주세요.</td>
          </tr>
          <!-- 주민등록번호 입력폼 삭제 -->
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mem_email" size="27"
            			value="<%=mem.getMem_email()%>"> </td>
            <td>이메일을 적어주세요.</td>
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="mem_phone" size="20"
            			value="<%=mem.getMem_phone()%>"> </td>
            <td>연락처를 적어 주세요.</td>
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td> <input type="text" name="mem_zipcode" size="7"
            			value="<%=mem.getMem_zipcode()%>">
                 <input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
            <td>우편번호를 검색 하세요.</td>
          </tr>
		  <tr>  
            <td>주소</td>
            <td><input type="text" name="mem_address" size="70"
            			value="<%=mem.getMem_address()%>"></td>
            <td>주소를 적어 주세요.</td>
          </tr>
		  <tr>  
            <td>직업</td>
            <!-- 콤보박스에 없는 값을 필드에 저장시키면 연결이 안된 것 처럼 나온다. -->
            <td><select name=mem_job>
 					<option value="0">선택하세요.
 					<option value="회사원">회사원
 					<option value="연구전문직">연구전문직
 					<option value="교수학생">교수학생
 					<option value="일반자영업">일반자영업
 					<option value="공무원">공무원
 					<option value="의료인">의료인
 					<option value="법조인">법조인
 					<option value="종교,언론,에술인">종교.언론/예술인
 					<option value="농,축,수산,광업인">농/축/수산/광업인
 					<option value="주부">주부
 					<option value="무직">무직
 					<option value="기타">기타
				  </select>
				  <script>
				  document.regForm.mem_job.value="<%=mem.getMem_job()%>";
				  </script>
				  </td>
            <td>직업을 선택 하세요.</td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="submit" value="수정완료"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="reset" value="다시쓰기">
             <input type="button" value="수정취소" onclick="history.back()">
            </td>
          </tr>
          <!-- 직접입력을 받는것이 아닌 경우에는 hidden객체로 전달 (반드시 form 안에 해야함)-->
          <input type="hidden" name="mem_id" value="<%=mem_id%>">
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
