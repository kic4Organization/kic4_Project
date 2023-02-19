//비지니스메서드를 구현(업무)

package member;

import java.sql.*;//DB작업
import java.util.*;//Vector,Hashtable,list

public class MemberDBMgr 
{
    //pool객체를 선언
	private DBConnectionMgr pool = null;//풀객체

	public MemberDBMgr(){
     //DBConnectionMgr의 객체를 얻어오는 구문
	  try
	  {
		pool = DBConnectionMgr.getInstance();
		System.out.println("pool="+pool);
	  }
	  catch (Exception e)
	  {
		System.out.println
		("Error:커넥션불러오기 실패"+e);
	  }
	}

	//비지니스메서드 구현
	//1.회원인지를 체크해주는 메서드(인증)
public boolean loginCheck(String memid,
	                      String pwd){
    //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
    
	boolean check = false;//로그인성공유무
	//DB작업(select)

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
     String sql="select memid,pwd from member "+
		        "where memid = ? and pwd = ?";
	 pstmt = con.prepareStatement(sql);
     pstmt.setString(1,memid);
	 pstmt.setString(2,pwd);
	 rs = pstmt.executeQuery();
     check = rs.next();
	}
	catch (Exception ex)
	{
      System.out.println("=loginCheck()에러=");
	  System.out.println("==에러라인 41==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt,rs);
	}
   return check;
}
    //2.중복ID를 체크하는 메서드
 public boolean checkId(String memid){
  
    //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
    
	boolean check = false;//중복ID유무
	//DB작업(select)

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
	  System.out.println("con="+con);
      String sql="select memid from member "+
		         " where memid = ?";
      pstmt = con.prepareStatement(sql);
	  pstmt.setString(1,memid);
	  rs = pstmt.executeQuery();
	  check = rs.next();//true

	}
	catch (Exception ex)
	{
      System.out.println("=checkId()에러=");
	  System.out.println("==에러라인 75==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt,rs);
	}
   return check;
 }
    //3.우편번호를 검색->회원전체를 검색(메서드)

 public Vector zipcodeRead(String area3){
  
  //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
    Vector vecList = new Vector();//담을객체

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
      String sql="select * from zipcode where area3 like '"+area3+"%'";
      pstmt = con.prepareStatement(sql);
	  rs = pstmt.executeQuery();
	  
	  //검색한 데이터를 찾아서 벡터에 담는코딩
	   while(rs.next()){//찾은 데이터가 있다면
        ZipcodeDTO tempZipcode = new ZipcodeDTO();
		tempZipcode.setZipcode(rs.getString("zipcode"));
		tempZipcode.setArea1(rs.getString("area1"));
		tempZipcode.setArea2(rs.getString("area2"));
		tempZipcode.setArea3(rs.getString("area3"));
		tempZipcode.setArea4(rs.getString("area4"));
		//벡터에 담기->화면에 출력하기위해
		vecList.add(tempZipcode);
	   }
	}
	catch (Exception ex)
	{
      System.out.println("=zipcodeRead()에러=");
	  System.out.println("==에러라인 119==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt,rs);
	}
   return vecList;
 }
	//4.회원가입->화면에 출력(빈즈데이터)->DB
 public boolean memberInsert(RegisterDTO regBean){
   
   //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;    
	boolean check = false;//회원가입성공유무

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
	  //트랜잭션처리
	  con.setAutoCommit(false);//DB작업의시작

     String sql="insert into member "+
		        "values(?,?,?,?,?,?,?,?,?,?,?,?)";

	 pstmt = con.prepareStatement(sql);
     pstmt.setString(1,regBean.getMemid());
	 pstmt.setString(2,regBean.getGrade());
	 pstmt.setString(3,regBean.getMemname());
	 pstmt.setString(4,regBean.getBirthday());
	 pstmt.setString(5,regBean.getEmail());
	 pstmt.setString(6,regBean.getMphone());
	 pstmt.setString(7,regBean.getNickname());
	 pstmt.setString(8,regBean.getEnrolldate());
	 pstmt.setString(9,regBean.getDeletedate());
	 pstmt.setString(10,regBean.getDelflag());
	 pstmt.setString(11,regBean.getPwd());
	 pstmt.setString(12,regBean.getAddr());
     //1->insert갯수,0->삽입실패
	 int count = pstmt.executeUpdate();//insert
     System.out.println("count="+count);
	 con.commit();//오라클의 경우

	 if(count > 0){
		 check = true;//데이터입력 성공
	  }
	}
	catch (Exception ex)
	{
      System.out.println("=memberInsert()에러=");
	  System.out.println("==에러라인 152==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt);
	}
   return check;
 }
	//5.회원정보를 출력(id에 해당)->select
  //1건->RegisterDTO, 많은수->Vector,list

public RegisterDTO getMember(String memid){

    //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	//1건의 데이터만 담을 객체선언
    RegisterDTO regBean = null;

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
	  String sql="select * from member where memid = ?";
      pstmt = con.prepareStatement(sql);
	  pstmt.setString(1,memid);
	  rs = pstmt.executeQuery();
	  
	  //검색한 데이터를 찾아서 벡터에 담는코딩
	   if(rs.next()){//찾은 데이터가 있다면
		   regBean = new RegisterDTO();
		   regBean.setMemid(rs.getString("memid"));
		   regBean.setGrade(rs.getString("grade"));
		   regBean.setMemname(rs.getString("memname"));
		   regBean.setBirthday(rs.getString("birthday"));
		   regBean.setEmail(rs.getString("email"));
		   regBean.setMphone(rs.getString("mphone"));
		   regBean.setNickname(rs.getString("nickname"));
		   regBean.setEnrolldate(rs.getString("enrolldate"));
		   regBean.setDeletedate(rs.getString("deletedate"));
		   regBean.setDelflag(rs.getString("delflag"));
		   regBean.setPwd(rs.getString("pwd"));
		   regBean.setAddr(rs.getString("addr"));

	   }
	}
	catch (Exception ex)
	{
      System.out.println("=getMember()에러=");
	  System.out.println("==에러라인 202==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt,rs);
	}
   return regBean;
  }
	//6.회원수정->insert구문와 비슷
  public boolean memberUpdate
	                (RegisterDTO regBean){
    //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;    
	boolean flag = false;//회원수정성공유무

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
	  //트랜잭션처리
	  con.setAutoCommit(false);//DB작업의시작

     String sql="update member set memname =?, "+
		      "birthday = ?,email = ?,mphone = ?, "+
              "nickname = ?,pwd = ?, addr = ? where memid = ?";
     /* 제외
      * address = ?
     pstmt.setString(8,regBean.getGrade());        //등급
     pstmt.setString(9,regBean.getEnrolldate());  //회원가입일
	 pstmt.setString(10,regBean.getDeletedate()); //회원탈퇴일
     pstmt.setString(11,regBean.getDelflag());     //회원탈퇴여부
      */
     
	 pstmt = con.prepareStatement(sql);
	 pstmt.setString(1,regBean.getMemname());
	 pstmt.setString(2,regBean.getBirthday());
	 pstmt.setString(3,regBean.getEmail());
	 pstmt.setString(4,regBean.getMphone());
	 pstmt.setString(5,regBean.getNickname());
     pstmt.setString(6,regBean.getPwd());
     pstmt.setString(7,regBean.getAddr());
     pstmt.setString(8,regBean.getMemid()); //12
     

	 //1->update갯수,0->수정실패
	 int upcheck = pstmt.executeUpdate();//update
     System.out.println("upcheck="+upcheck);
	 con.commit();//오라클의 경우

	 if(upcheck ==1){
		 flag = true;//데이터수정 성공
	  }
	}
	catch (Exception ex)
	{
      System.out.println("=memberUpdate()에러=");
	  System.out.println("==에러라인 254==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt);
	}
    return flag;
  }
	//7.회원탈퇴->memid,pwd->select->delete
  public int deleteMember(String memid,
	                      String pwd){
   //DB접속
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
    String dbpasswd="";//테이블에 저장된 암호
	int x = -1;//회원삭제유무
	//DB작업(select)

	try
	{
	  //DB접속구문
	  con = pool.getConnection();
	  con.setAutoCommit(false);//시작점

      String sql="select pwd from member "+
		         " where memid = ?";
      pstmt = con.prepareStatement(sql);
	  pstmt.setString(1,memid);
	  rs = pstmt.executeQuery();
	  
       if(rs.next()){ //id에 해당하는 암호존재
         dbpasswd = rs.getString("pwd");
           //테이블의 암호==웹상의 암호
		   if(dbpasswd.equals(pwd)){
	String sql2="delete from member where memid = ?";
      pstmt=con.prepareStatement(sql2);
	  pstmt.setString(1,memid);
	  int delete = pstmt.executeUpdate();
	  con.commit();
      System.out.println("삭제유무="+delete);
	           x=1;//회원탈퇴성공
		   }else{
               x=0;//비밀번호가 틀림
		   }//else
	   }//if(rs.next())
	}//try
	catch (Exception ex)
	{
      System.out.println("=deleteMember()에러=");
	  System.out.println("==에러라인 302==");
      System.out.println(ex);
	}finally{	//DB객체를 해제
      pool.freeConnection(con,pstmt,rs);
	}
   return x;
  }
  //관리자->전체회원을 모두 열람(검색)
}
