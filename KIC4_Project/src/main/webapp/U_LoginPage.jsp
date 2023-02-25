<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="memid" value="${sessionScope.idKey}"/>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>로그인페이지</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>
  <script language="JavaScript" src="script.js?ver=2.0"></script>
</head>
<body>
  <!-- ====== Forms Section Start ====== -->
  <!-- mem_id의 상태에따라 로그인 처리 -->
 <c:if test="${!empty memid}"> <!-- 수정할 예정 -->
	 <b><c:out value="${memid}"/></b>님 환영합니다.<p>
	 		제한된 기능을 사용할 수 가 있습니다.<p>
	<a href="U_MemberUpdate.shop">회원수정</a>
	<a href="U_DelCheckForm.shop?memid=${memid}">회원탈퇴</a>
	<a href="U_Logout.shop">로그아웃</a>
</c:if>

<!-- 로그인 안된 상태 -->
<c:if test="${empty memid}">
<section class="bg-[#F4F7FF] py-20 lg:py-[117px]">
  <div class="container mx-auto">
    <div class="-mx-4 flex flex-wrap">
      <div class="w-full px-4">
        <div
          class="relative mx-auto max-w-[525px] overflow-hidden rounded-lg bg-white py-16 px-10 text-center sm:px-12 md:px-[60px]"
        >
          <div class="mb-10 text-center md:mb-16">
            <a
            href="javascript:void(0)"
            class="mx-auto inline-block max-w-[160px]"
          >
            <img src="./img/logo.png"/>
          </a>
          </div>
          <form name="login" method="post" action="U_LoginPageProc.shop">
            <div class="mb-5">
              <input
                type="text"
                name="memberid"
                placeholder="아이디"
                class="bordder-[#E9EDF4] w-full rounded-md border bg-[#FCFDFE] py-3 px-5 text-base text-body-color placeholder-[#ACB6BE] outline-none focus:border-primary focus-visible:shadow-none"
              />
            </div>
            <div class="mb-6">
              <input
                type="password"
                name="memberpw"
                placeholder="비밀번호"
                class="bordder-[#E9EDF4] w-full rounded-md border bg-[#FCFDFE] py-3 px-5 text-base text-body-color placeholder-[#ACB6BE] outline-none focus:border-primary focus-visible:shadow-none"
              />
            </div>
            <div class="mb-3">
              <input
              type="submit"
              value="로그인"
              class="bordder-primary w-full cursor-pointer rounded-md  bg-primary py-4 px-5 text-lg text-white transition hover:bg-opacity-90 bg-blue-600"
            />
            </div>
          </form>
          <ul class="-mx-2 mb-12">
            <li class="w-full px-2 py-1 mb-3">
              <a
                href="javascript:void(0)"
                class="flex h-11 items-center hover:opacity-80"
              >
                <img src="./kic/img/kakao_login.png"/>
              </a>
            </li>
            <li class="w-full px-2 py-1">
              <a
                href="javascript:void(0)"
                class="flex h-11 items-center hover:opacity-90"
              >
              <img src="./kic/img/naver_login.png"/>
              </a>
            </li>            
          </ul>
          <div>
            <a
              href="javascript:void(0)"
              class="mb-2 inline-block text-base text-[#adadad] hover:text-primary hover:underline"
            >
              아이디 찾기
            </a>
            <span>/</span>
            <a
            href="javascript:void(0)"
            class="mb-2 inline-block text-base text-[#adadad] hover:text-primary hover:underline"
          >
            비밀번호 찾기
          </a>
          </div>
          <p class="text-base text-[#adadad]">
            아직 회원이 아니신가요?
            <!-- <a href="javascript:void(0)" class="text-primary text-blue-600 hover:text-blue-400 hover:underline">
              가입하기
            </a> -->
          </p>
         <input type="button" value="가입하기" onclick="memberReg()">
        </div>
      </div>
    </div>
  </div>
</section>
<!-- ====== Forms Section End -->
</c:if>
</body>
</html>