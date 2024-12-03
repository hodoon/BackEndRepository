<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/loginPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/join.css">
</head>
<body>
<div class="layout">
    <%@ include file="header.jsp"%>
    <div class="auth-login_divider"></div>
    <div class="auth-register">
        <div class="auth-register-container">
            <form method="POST" action="/auth/register" class="auth-register__body">
                <input type="hidden" name="csrfmiddlewaretoken" >
                <fieldset style="width: 100%;">
                    <div class="auth-register__body">
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">이메일</label>
                            <div class="join_input-box">
                                <input type="text" name="username" placeholder="이메일을 입력하세요" autocomplete="username" autofocus="autofocus" required="required" id="id_username" class="join_input-txt">
                            </div>
                            <div class="auth-register__input--message">
                            </div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">비밀번호</label>
                            <label for="id_password1"></label><input type="password" name="password1" placeholder="비밀번호를 입력하세요" autocomplete="new-password" required="required" id="id_password1" class="inputFull">
                            <div class="auth-register__input--message">

                            </div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">비밀번호 확인</label>
                            <input type="password" name="password2" placeholder="비밀번호를 한 번 더 입력하세요" autocomplete="new-password" required="required" id="id_password2" class="inputFull">
                            <div class="auth-register__input--message-wrap"></div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">닉네임</label>
                            <div class="join_input-box">
                                <input type="text" name="nickname" placeholder="닉네임을 입력하세요" required="required" id="nick_name" class="join_input-txt">
                            </div>
                            <div class="auth-register__input--message-wrap">
                                <p style="font-size: 12px; color: rgb(114, 127, 136);">2자 이상 10자 이하의 한글/영어로 설정해주세요</p>
                            </div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">이름</label>
                            <div class="join_input-box">
                            <input type="text" name="name" placeholder="이름을 입력하세요" required="required" id="id_name" class="join_input-txt">
                            </div>
                            <div class="auth-register__input--message-wrap">
                                <p style="font-size: 12px; color: rgb(114, 127, 136);">2자 이상 10자 이하의 한글/영어로 설정해주세요</p>
                            </div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">성별</label>
                            <select name="sex" type="number" id="id_sex" class="inputFull">
                                <option value="0">성별을 선택하세요</option>
                                <option value="M">남성</option>
                                <option value="F">여성</option>
                            </select>
                            <div class="auth-register__input--message-wrap">
                                <p></p>
                            </div>
                        </div>
                        <div class="auth-register__input">
                            <label class="auth-register__input--title">생년월일</label>
                            <div class="auth-register__input--birth-wrap">
                                <select name="birthday_year" required="required" id="id_birthday_year" type="text" style="width: 32%; display: inline-block;">
                                    <c:forEach var="year" begin="1954" end="2023">
                                        <option value="${year}" ${year == 1994 ? 'selected="selected"' : ''}>${year}년</option>
                                    </c:forEach>
                                </select>
                                <select name="birthday_month" required="required" id="id_birthday_month" type="text" style="width: 32%; display: inline-block;">
                                    <c:forEach var="month" begin="1" end="12">
                                        <option value="${month}" ${month == 1 ? 'selected="selected"' : ''}>${month}월</option>
                                    </c:forEach>
                                </select>
                                <select name="birthday_day" required="required" id="id_birthday_day" type="text" style="width: 32%; display: inline-block;">
                                    <c:forEach var="date" begin="1" end="31">
                                        <option value="${date}" ${date == 1 ? 'selected="selected"' : ''}>${date}일</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="auth-register__input--message-wrap">
                                <p></p>
                            </div>
                        </div>
                        <div class="auth-register__input">
                            <div class="auth-register__input-six-to-four">
                                <div class="auth-register__input-six-to-four--left">
                                    <label class="auth-register__input--title">휴대폰 번호</label>
                                    <div class="join_input-box">
                                        <input type="text" name="phone" placeholder="예) 01012345678" required="required" id="id_phone" class="join_input-txt">
                                    </div>
                                </div>
                                <div class="auth-register__input-six-to-four--right">
                                    <button type="button" id="sendCodeButton" class="auth-register__input--button">
                                        <p class="auth-register__input--button-text">인증 요청</p>
                                    </button>
                                </div>
                            </div>
                            <div class="auth-register__input--message-wrap">
                                <p></p>
                            </div>
                        </div>
                        <div class="auth-register__input" style="display: none;">
                            <div class="auth-register__input-six-to-four">
                                <div class="auth-register__input-six-to-four--left">
                                    <input id="verify_code_input" placeholder="3분 00초 남음" type="number" autocomplete="one-time-code">
                                </div>
                                <div class="auth-register__input-six-to-four--right">
                                    <button type="button" class="auth-register__input--button">
                                        <p class="auth-register__input--button-text">인증 확인</p>
                                    </button>
                                </div>
                            </div>
                            <div class="auth-register__input--message-wrap">
                                <p></p>
                            </div>
                        </div>
                    </div>
                    <div class="auth-register__footer">
                        <div class="auth-register__confirm-policy">
                            <input type="checkbox" name="all" id="all">
                            <label for="all">
                                <p class="auth-register__confirm-all--text">전체 동의</p>
                            </label>
                        </div>
                        <div class="auth-register__line">

                        </div>
                        <div class="auth-register__confirm-agreement">
                            <div class="auth-register__confirm-agreement--checkbox">
                                <input type="checkbox" name="guideline" id="guideline">
                                <label for="guideline">
                                    <p class="auth-register__confirm--text">이용약관 동의 (필수)</p>
                                </label>
                            </div>
                            <a href="https://policy.plabfootball.com/term/" target="_blank" class="auth-register__confirm-agreement--detail">
                                <p>자세히</p>
                            </a>
                        </div>
                        <div class="auth-register__confirm-agreement">
                            <div class="auth-register__confirm-agreement--checkbox">
                                <input type="checkbox" name="privacy_term" id="privacy" required="required" class="checkbox">
                                <label for="privacy">
                                    <p class="auth-register__confirm--text">개인정보 수집 및 이용 동의 (필수)</p>
                                </label>
                            </div>
                            <a href="https://policy.plabfootball.com/privacy" target="_blank" class="auth-register__confirm-agreement--detail">
                                <p>자세히</p>
                            </a>
                        </div>
                        <div class="auth-register__confirm-agreement">
                            <div class="auth-register__confirm-agreement--checkbox">
                                <input type="checkbox" name="agePolicy" id="agePolicy">
                                <label for="agePolicy">
                                    <p class="auth-register__confirm--text">만 14세 이상입니다 (필수)</p>
                                </label>
                            </div>
                        </div>
                        <div class="auth-register__confirm-agreement">
                            <div class="auth-register__confirm-agreement--checkbox">
                                <input type="checkbox" name="sms_term" id="sms_term" class="checkbox">
                                <label for="sms_term">
                                    <p class="auth-register__confirm--text">이벤트, 프로모션 등 SMS 맞춤 정보 받기 (선택)</p>
                                </label>
                            </div>
                            <a href="https://policy.plabfootball.com/privacy/plabfootball" target="_blank" class="auth-register__confirm-agreement--detail">
                                <p>자세히</p>
                            </a>
                        </div>
                        <div class="auth-register__confirm-agreement">
                            <div class="auth-register__confirm-agreement--checkbox">
                                <input type="checkbox" name="email_term" id="email_term" class="checkbox">
                                <label for="email_term">
                                    <p class="auth-register__confirm--text">이벤트, 프로모션 등 이메일 맞춤 정보 받기 (선택)</p>
                                </label>
                            </div>
                            <a href="https://policy.plabfootball.com/privacy/plabfootball" target="_blank" class="auth-register__confirm-agreement--detail">
                                <p>자세히</p>
                            </a>
                        </div>
                    </div>
                </fieldset>
                <div class="auth-register__button-wrap">
                    <button type="submit" class="auth-register__button">
                        <p class="auth-register__button--text">가입하기</p>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
