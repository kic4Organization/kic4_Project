<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" 
          uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js?ver=3.1"></script>
</head>
<body bgcolor="#996600">
<br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
	<table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="U_MemberInsert.shop">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF"><b> 
              <c:out value="${regDTO.memname}"/>
              회원님이 작성하신 내용입니다. 확인해 주세요</b></font> </td>
          </tr>
          <tr> 
            <td>아이디</td>
            <td>
	<input type="text" name="memid" 
				value="<c:out value="${regDTO.memid}"/>"></td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td><input type="text" name="pwd" 
	value="<c:out value="${regDTO.pwd}"/>"></td>
          </tr>
          <tr> 
            <td>이름</td>
            <td><input type="text" name="memname" 
			value="<c:out value="${regDTO.memname}"/>"></td>
          </tr>
          <tr> 
            <td>이메일</td>
           <td><input type="text" name="email"  size="30" 
		   value="<c:out value="${regDTO.email}"/>"></td>
          </tr>
          <tr> 
            <td>전화번호</td>
            <td><input type="text" name="mphone" 
			 value="<c:out value="${regDTO.mphone}"/>"></td>
          </tr>
		  <tr> 
            <td>우편번호</td>
            <td><input type="text" name="zipcode" 
			value="<c:out value="${regDTO.zipcode}"/>"></td>
          </tr>
		  <tr> 
            <td>주소</td>
           <td><input type="text" name="addr" size="50" 
		    value="<c:out value="${regDTO.addr}"/>"></td>
          </tr>
          <tr> 
            <td colspan="2" align="center"><input type="submit" value="확인완료"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
            <input type="button" value="다시쓰기" onClick="history.back()"> 
            </td>
          </tr>
        </form>
      </table>
 </td>
  </tr>
</table>
</body>
</html>
