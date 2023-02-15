/**
 * 기능 : 중복 id를 체크해주는 기능을 Ajax로 요청해서 처리해주는 자바스크립트용 파일이다. 
 * 작성날짜 : 2023.01.20
 * 작성자 : 강홍구
 */
 
 var xhrObject; //xhr객체를 전역변수로 선언
 
 //1. xhr객체를 생성해주는 함수
 function createXHR(){
	if(window.XMLHttpRequest){
//		var을 생략한 이유 -> 위에 xhrObject과 중복선언되기 때문이다.
		xhrObject=new XMLHttpRequest(); //객체생성
		alert(xhrObject);//[xhr Object] => 주소값 표시
	}
}
 
 //2. 중복id를 입력
 function idCheck(id) {
	if(id=="") {//id를 입력을 하지 않은 경우
//		var mem_id=document.getElementById("ducheck");
		var mem_id=$("ducheck"); //prototype.js
//		alert(mem_id);
		$("ducheck").innerHTML="<font color='red'><b>아이디를 먼저 입력하세요</b></font>";
//		document.regForm.mem_id.focus(); //document.폼객체명.입력양식.focus();
		$("mem_id").focus();
		return false;
	}
//	입력했을 경우 Ajax요청
// 1) xhr객체 얻어오기	
	createXHR()
	var url="http://localhost:8090/JspMember/IdCheck.jsp?"+getParameterValues();
// 2) 콜백함수를 지정 -> 서버의 결과를 받아서 처리해 주기 위함이다.
	xhrObject.onreadystatechange=resultProcess;
// 3)open함수를 이용해서 서버에 요청 준비
	xhrObject.open("Get",url,true);
// 4)서버에 요청
	xhrObject.send(null);		
}
 
 //3. 파라미터 값을 처리해주는 함수 => 서버의 메모리 캐시를 제거시키는 역할
 function getParameterValues() {
	var mem_id=$("mem_id").value; //document.regForm.mem_id.value
//	서버캐시에 요청메모리에 저장시키지 않는 방법 
//  -> 항상 동일한 요청을 하지 않기 위해서 -> 시간은 항상 같지가 않기 때문에
	return "mem_id="+mem_id+"&timestamp="+new Date().getTime();
}

//4. 콜백함수
function resultProcess() {
	if(xhrObject.readyState==4){ //서버가 요청을 다 받았다면
		if(xhrObject.status==200) { //서버로부터 정상적으로 결과를 받았다면
			var result=xhrObject.responseText; //태그 + 문자열
			$("ducheck").innerHTML=result;
		}
	}
}