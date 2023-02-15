package hewon;

//웹상에서 호출할 메서드를 작성 -> DB연결된후 호출(->has a 관계)
import java.sql.*; //DB연결
import java.util.*; //Vector, ArrayList~

public class MemberDAO {
	
//	1. 멤버변수에 연결할 클래스의 객체를 선언
	private DBConnectionMgr pool=null;
	//getConnection() => Connection필요, freeConnection() 메모리 해제
	
//	1-1)공통으로 접속할 경우 필요로하는 멤버변수
	private Connection con=null;
	private PreparedStatement pstmt=null;//sql 실행목적
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장 목적
//	1-2)
	
//	2. 생성자를 통해서 자동으로 객체를 얻어올 수 있도록 연결
	public MemberDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool => "+pool);
		}catch(Exception e) {
			System.out.println("DB연결실패=> "+e);//e.toString()
		}
	}
//	3. 요구분석에 따른 웹상에서 호출할  메서드를 작성
//	 1)회원로그인->id, passwd
//	    반환값=>int(1 or 0), d=boolean(true or false) => 메모리 적게 사용
//		SQL구문
//		select id,passwd from member where id='nup' and passwd='1234';
//		select구문 => 반환값 O where조건식 -> 매개변수 O
	public boolean loginCheck(String id, String passwd) {
//		1. DB연결
		boolean check=false;
//		2. SQL구문
		try {
			con=pool.getConnection();//만들어진 Connection 반환
			System.out.print("con => "+con);
			sql="select id,passwd from member where id=? and passwd=?";
			pstmt=con.prepareStatement(sql);//실행시킬 sql구문
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			//rs.next()->데이터가 존재하면 true or 없으면 false
			check=rs.next();
		}catch(Exception e) {
			System.out.println("loginCheck() 실행 에러유발 =>"+e);
		}finally {//3. 메모리 해제
			pool.freeConnection(con, pstmt, rs);//con.close(),pstmt.close(),rs.close()
		}
		return check;//LoginProc.jsp에서 받아서 페이지 이동할 때 사용
	}
	
//	2)중복 id를 체크해주는 기능
	public boolean checkId(String id) {
//		1. DB연결
		boolean check=false; //지역변수
//		2. SQL구문
		//select id from member where id='kkk'
		try {
			con=pool.getConnection();//만들어진 Connection 반환
			sql="select id from member where id=?";
			pstmt=con.prepareStatement(sql);//실행시킬 sql구문
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();//select 구문을 호출할때 사용하는 메서드
			check=rs.next();//rs.next()->데이터가 존재하면 true or 없으면 false
		}catch(Exception e) {
			System.out.println("checkId() 실행 에러유발 =>"+e);
		}finally {//3. 메모리 해제
			pool.freeConnection(con, pstmt, rs);//con.close(),pstmt.close(),rs.close()
		}
		return check; //IdCheck.jsp에게 반환(선택해주는 역할)
	}
	
//	3)우편번호를 검색 -> 직접 테이블생성 -> 입력 => 찾는 방법 or OpenAPI
//	ZipCodeDAO (x)
//	sql-> select * from zipcode where area3 like '%미아2동%';
	public ArrayList<ZipcodeDTO> zipcodeRead(String area3){
//		레코드 한 개 이상 담을 변수(객체) 선언
		ArrayList<ZipcodeDTO> zipList = new ArrayList();//미리설정
		try {
			con=pool.getConnection();
			//sql="select * from zipcode where area3 like '%미아2동%'";
			sql="select * from zipcode where area3 like '"+area3+"%'";
			pstmt=con.prepareStatement(sql);//NullPointeException
			rs=pstmt.executeQuery();
//			검색된 레코드의 값을 필드 별로 담는 소스코드를 작성
//			찾은 레코드가 한 개 if(rs.next()) 한 개 이상 -> while(rs.next())
			System.out.println("검색된 sql구문 확인 => "+sql);
			while(rs.next()) {
				//저장된 데이터를 getter메서드로 불러오게 쉽게하기 위해서
				ZipcodeDTO tempZipcode = new ZipcodeDTO();
				tempZipcode.setZipcode(rs.getString("zipcode"));//"142-813"
				tempZipcode.setArea1(rs.getString("area1"));//<->getArea1()  "서울"
				tempZipcode.setArea2(rs.getString("area2"));//null
				tempZipcode.setArea3(rs.getString("area3"));//null
				tempZipcode.setArea4(rs.getString("area4"));//null
				//ArrayList에 담는 구문을 담는 구문을 작성 할 것.
				zipList.add(tempZipcode);//zipCheck.jsp(null)없으면
				////////////////////////////
			}
			
		}catch(Exception e) {
			System.out.println("zipcodeRead() 실행오류 =>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return zipList;//ZipCheck.jsp에서 메서드의 반환값 -> for 문
	}
	
//	4)회원 가입 -> insert into member values(?,?,?,?,,,)
//                    웹상에서 확인(반환값)=> 1 or 0
// (String id, String pw, String name,,,) => 8개 작성
//	DTO -> 1. 테이블의 필드별로 저장, 출력 2. 매개변수자료형, 반환형
	public boolean memberInsert(MemberDTO mem) {
		boolean check=false;//회원가입 성공유무
		try {
			con=pool.getConnection();
//			트렌젝션 => 오라클의 필수(410p~413p) => 자동으로 commit (X)
			con.setAutoCommit(false);//default -> true
			sql="insert into member values(?,?,?,?,?,?,?,?)";
//			-------------------------------------------------
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mem.getMem_id());//(1,'test')
			pstmt.setString(2,mem.getMem_passwd());
			pstmt.setString(3,mem.getMem_name());
			pstmt.setString(4,mem.getMem_email());
			pstmt.setString(5,mem.getMem_phone());
			pstmt.setString(6,mem.getMem_zipcode());
			pstmt.setString(7,mem.getMem_address());
			pstmt.setString(8,mem.getMem_job());
			
			int insert = pstmt.executeUpdate();//반환값 1 (성공), 0 (실패)
			con.commit();//메모리-> 실질적으로 테이블에 반영된다.
			System.out.println("insert(데이터 입력 유무) => "+insert);
			if(insert>0) {//if(insert==1)
				check=true; //데이터 성공 확인
			}
		}catch(Exception e){
			System.out.println("memberInsert() 실행오류 => "+e);
		}finally {
			pool.freeConnection(con, pstmt); //rs (X) (select가 아님) 
		}
		return check;//memberInsert.jsp에서 메서드의 반환값
	}
	
//	5)회원 수정 -> 특정 회원을 찾기(nup,kkk,kktest,,)
//	select * from member where id='nup'
	public MemberDTO getMember(String mem_id) {
		MemberDTO mem=null;//id값에 해당되는 레코드 한 개를 저장
		try {
			con=pool.getConnection();//만들어진 Connection 반환
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mem_id);
			rs=pstmt.executeQuery();
//			id값에 해당되는 레코드 한 개를 if(rs.next())->한개이상 while(rs.next())
			if(rs.next()) {
//				찾은값 => Setter메서드의 매개변수로 저장 -> 웹에 출력(Getter)
				mem=new MemberDTO();
				mem.setMem_id(rs.getString("id"));
				mem.setMem_passwd(rs.getString("passwd"));
				mem.setMem_name(rs.getString("name"));
				mem.setMem_phone(rs.getString("phone"));
				mem.setMem_zipcode(rs.getString("zipcode"));
				mem.setMem_address(rs.getString("address"));
				mem.setMem_email(rs.getString("e_mail"));
//				오라클에서 필드 이름이 e_mail로 되어 있음
				mem.setMem_job(rs.getString("job"));
			}
		}catch(Exception e) {
			System.out.println("getMember() 실행 에러유발 =>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mem;//MemberUpdate.jsp에게 반환
	}
	
//	6)찾은 회원을 수정 => 회원가입해주는 메서드와 동일(sql 구문이 다르다.)
	public boolean memberUpdate(MemberDTO mem) {
		boolean check=false;//회원수정 성공유무
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);//default -> true
			sql="update member set passwd=?,name=?,e_mail=?,"
				+" phone=?,zipcode=?,address=?,job=? where id=?";

			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mem.getMem_passwd());
			pstmt.setString(2,mem.getMem_name());
			pstmt.setString(3,mem.getMem_email());
			pstmt.setString(4,mem.getMem_phone());
			pstmt.setString(5,mem.getMem_zipcode());
			pstmt.setString(6,mem.getMem_address());
			pstmt.setString(7,mem.getMem_job());
			pstmt.setString(8,mem.getMem_id());
			
			int update = pstmt.executeUpdate();//반환값 1 (성공), 0 (실패)
			con.commit();//메모리-> 실질적으로 테이블에 반영된다.
			System.out.println("update(데이터 수정 유무) => "+update);
			if(update>0) {//if(update==1)
				check=true; //데이터수정 성공 확인
			}
		}catch(Exception e){
			System.out.println("memberUpdate() 실행오류 => "+e);
		}finally {
			pool.freeConnection(con, pstmt); //rs (X) (select가 아님) 
		}
		return check;//MemberUpdate.jsp에서 메서드의 반환값
	}
	
//	7)회원 탈퇴
//	select passwd from member where id='nup'
//	delete from member where id='nup'
	public int memberDelete(String id, String passwd) { //1.입력X  2.입력 O
		String dbpasswd="";//DB상에서 찾은 암호를 저장
		int x=-1;//회원탈퇴 유무
		
		try {
			con=pool.getConnection();//이미 만들어진 연결객체 얻어옴
			con.setAutoCommit(false); //트렌젝션 처리
//			인증처리
			sql="select passwd from member where id=?";
			pstmt=con.prepareStatement(sql);//NullPointException
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
//			암호를 찾았다면 
			if (rs.next()) {
				dbpasswd=rs.getString("passwd");
				System.out.println("dbpasswd => "+dbpasswd);
//				dbpasswd(DB상 암호)==passwd(웹상에서 입력한 값)
				if(dbpasswd.equals(passwd)) {
					sql="delete from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					int delete=pstmt.executeUpdate();
					System.out.println("delete(회원탈퇴 성공유무) => "+ delete);
					con.commit(); //트렌젝션 처리 끝
					x=1;//회원 탈퇴 성공(중요!)
				}else {//암호가 틀린 경우
					x=0;//회원탈퇴 실패
				}
			}else {//암호가 존재하지 않는다면
				x=-1;//이미 디폴트로 되어 있음
			}
		}catch(Exception e) {
			System.out.println("memberDelete() 실행오류 => "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
//	8)회원리스트 -> 과제(게시판의 글목록보기) => 관리자
	
	
}
