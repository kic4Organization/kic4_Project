package action;

//추가->web상에 import
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.RegisterDTO;
import member.ZipcodeDTO;

public class U_RegisterProcAction implements CommandAction
{
	//요청한 명령어에 따른 페이지로 이동시켜주는 메서드
	public String requestPro(HttpServletRequest request,
		                     HttpServletResponse response)
		                     throws Throwable{
		
		 //한글처리
		   request.setCharacterEncoding("utf-8");
		 
		 //빈즈객체를 생성 ->확인용 
	    RegisterDTO regDTO = new RegisterDTO();
	    System.out.println("regDTO="+regDTO);
	    ZipcodeDTO zipDTO= new ZipcodeDTO();
	    System.out.println("zipDTO="+zipDTO);
	    
	    regDTO.setMemid(request.getParameter("memid"));
	    regDTO.setGrade(request.getParameter("grade"));
	    regDTO.setMemname(request.getParameter("memname"));
	    regDTO.setBirthday(request.getParameter("birthday"));
	    regDTO.setEmail(request.getParameter("email"));
	    regDTO.setMphone(request.getParameter("mphone"));
	    regDTO.setNickname(request.getParameter("nickname"));
	    regDTO.setEnrolldate(request.getParameter("enrolldate"));
	    regDTO.setDeletedate(request.getParameter("deletedate"));
	    regDTO.setDelflag(request.getParameter("delflag"));
	    regDTO.setPwd(request.getParameter("pwd"));
	    regDTO.setAddr(request.getParameter("addr"));
	    zipDTO.setZipcode(request.getParameter("zipcode"));
	    
	    request.setAttribute("regDTO", regDTO);
    return "/U_RegisterProc.jsp";
	}
}
