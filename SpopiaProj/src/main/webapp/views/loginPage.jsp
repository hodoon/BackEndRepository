<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
<div class="layout">
  <%@ include file="header.jsp"%>
</div>
<div class="layout">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/loginPage.css">
  <div class="auth-login_divider"></div>
  <div id="LoginApp" class="content" style="height: 100%; max-width: 760px">
    <div class="auth-login">
      <div class="auth-login_container">
        <form method="POST" class="auth-login_body" action="/auth/login">
          <input type="hidden">
          <fieldset style="width: 100%;">
            <div class="auth-login_form">
              <div class="auth-login_input">
                <label class="auth-login_input-title">아이디</label>
                <div class="auth-login_input-box">
                <label for="id_username"></label><input type="text" name="username" autofocus="autofocus" required="required" id="id_username" placeholder="아이디를 입력하세요"
                class="auth-login_input-txt"/>
                </div>
              </div>
              <div class="auth-login_input">
                <label class="auth-login_input-title">비밀번호</label>
                <div class="auth-login_input-box">
                <label for="id_password"></label><input type="password" name="password" required="required" id="id_password" placeholder="비밀번호를 입력하세요"
                class="auth-login_input-txt"/>
                </div>
            </div>
              <input type="checkbox" name="saveID" id="saveID">
              <label for="saveID">
                <p class="auth-login_save-id">
                아이디 저장
                </p>
              </label>
              <!---->
              <button type="submit" class="auth-login_button-login" >로그인</button>
              <div class="auth-login_button--extra">
                <a>아이디/비밀번호 찾기</a>
                <a href="register.jsp">회원 가입</a>
              </div>
            </div>
          </fieldset>
          <div class="auth-login_divider"></div>
        </form>
      </div>
    </div>
  </div>
</div>

</body>
</html>
