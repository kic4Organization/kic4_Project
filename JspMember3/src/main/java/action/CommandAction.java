package action;

//기능은 다르지만 요청을 받아서 처리해주는 메서드를 공통의 메서드로 
//작성하기 위해서 만든 인터페이스

import javax.servlet.http.*;//HttpServletRequest.~Response

public interface CommandAction {//ListAction,Write~
	
	//요청을 받아서 처리하는 메서드를 만들기
	//요청을 하면 어떤 메서드를 통해 어느 페이지로 이동하라.를 설정해야한다.
	//이동할 페이지의 경로와 페이지명이 필요하다. => 반환값이 모델2에서는 거의 String이다. <---> 스프링에서는 ModelAndView를 씀
	//요청을 받아서 이 메서드를 언제든지 불러야하니까 접근지정자는 public
	//반환값은 어떤 페이지로 이동하라는게 들어가야하므로 String
	//요청을 받아서 처리해주는 메서드는 매개변수로 받을 2개가 필요하다. 하나는 HttpServletRequest의 내장객체인 request객체이고, 응답객체인 response객체이다.
	public String requestPro(HttpServletRequest request, 
					HttpServletResponse response) throws Throwable;
	//모든 클래스가 이 메서드를 무조건 상속받아서 만들도록 설계하면, 기능은 달라도 메서드 이름은 통일시켜서 편리하다.
	
	
	
	
}
