package action;

import javax.servlet.http.*;
import member.MemberDBMgr;

public class LoginPageProcAction implements CommandAction {

	//@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//LoginPage.shop에 의한 LoginPage.jsp에서 값이 넘어옴.
		String memid=request.getParameter("memberid");
		String pwd=request.getParameter("memberpw");
		
		System.out.println("memid=>"+memid);
		System.out.println("pwd=>"+pwd);//경로확인
		MemberDBMgr memMgr = new MemberDBMgr();
		boolean loginCheck = memMgr.loginCheck(memid,pwd);
		
		request.setAttribute("loginCheck", new Boolean(loginCheck)); 
		request.setAttribute("memid", new String(memid));
		
		return "/LoginPageProc.jsp";
	}

}
