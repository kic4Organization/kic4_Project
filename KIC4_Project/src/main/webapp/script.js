function loginCheck(){
	if(document.login.mem_id.value==""){
		alert("아이디를를 입력해 주세요.");
		document.login.mem_id.focus();
		return;
	}
	if(document.login.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.login.mem_passwd.focus();
		return;
	}
	document.login.submit();
}
//회원가입
function memberReg(){
	//document.location="Register.jsp";
	//document.location="agreement.jsp";
	document.location="U_agreement.shop";
}

//회원가입을 할때 각 항목을 제대로 입력->체크
function inputCheck(){
	if(document.regForm.mem_id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regForm.mem_id.focus();
		return;
	}
	if(document.regForm.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.mem_passwd.focus();
		return;
	}
	if(document.regForm.mem_repasswd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_name.value==""){
		alert("이름을 입력해 주세요.");
		document.regForm.mem_name.focus();
		return;
	}
	if(document.regForm.mem_email.value==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mem_email.focus();
		return;
	}
	if(document.regForm.mem_phone.value==""){
		alert("연락처를 입력해 주세요.");
		document.regForm.mem_phone.focus();
		return;
	}
	
	if(document.regForm.mem_passwd.value != document.regForm.mem_repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.mem_repasswd.focus();
		return;
	}
	document.regForm.submit();
}
//Register.jsp->script.js(idCheck->IdCheck.jsp?
function idCheck(id){
    if(id==""){
          alert("아이디를 입력해 주세요!");
		  document.regForm.mem_id.focus();
	}else{
         url="IdCheck.jsp?mem_id="+id;
window.open(url,"post","width=300,height=150");
	}
}

//우편번호창을 열 수 있도록 함수호출
function zipCheck(){
 url="U_ZipCheck.jsp?check=y";
 window.open(url,"post","toolbar=no,width=500,height=300,directories=no,status=yes,scrollbars=yes,menubar=no");
}
