<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Oracle Connection Test</title>

</head>

<body>



<%@ page import="java.sql.*" %>



<h2> 데이타베이스 드라이버와 DB Oracle 연결 점검 프로그램 </h2>



<%

	try{

		String driverName="oracle.jdbc.driver.OracleDriver";

		//String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";

		

		Class.forName(driverName);

		Connection conn = DriverManager.getConnection(dbURL,"kic4","4444");

		

		out.println("Oracle Database 에 접속 했습니다.");

		

		conn.close();

		

	}catch(Exception e){

		out.println("Oracle Database 에 접속에 문제가 발생 했습니다.");

		out.println(e.getMessage());

		e.printStackTrace();

	}

%>

<hr size = "5" color ="red">



</body>

</html>