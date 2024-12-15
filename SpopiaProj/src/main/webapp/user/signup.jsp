<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<style>
    .section-container {
        height: 75%;
    }
</style>
<script>
    // 아이디 input에 영어만 입력되도록
    const typeOnlyEng = (target) => {
        target.value = target.value .replace(/[^\\!-z]/gi,"");
    }
    const confirmSignup = () => {
        if (confirm('정말 가입하시겠습니까?')) {
            const form = document.getElementById('submitForm');
            form.submit();
        }
    }
</script>
<body>
<%@ include file="../module/header.jsp"%>
<div class="section-container">
    <section class="signup-form">
        <form action="${pageContext.request.contextPath}/user/signupProc" method="post" id="submitForm">
            <div class="input-group">
                <label for="userEmail"></label>
                <c:choose>
                    <c:when test="${not empty signupUser}">
                        <input type="email" id="userEmail" name="userEmail" value="${signupUser.userEmail}" readonly>
                    </c:when>
                    <c:otherwise>
                        <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${empty signupUser}">
                <div class="input-group">
                    <label for="userPw"></label>
                    <input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요" required>
                </div>
            </c:if>
            <div class="input-group">
                <label for="userName"></label>
                <c:if test="${not empty signupUser}">
                    <input type="text" id="userName" name="userName" value="${signupUser.userName}" placeholder="이름을 입력하세요" required>
                </c:if>
                <c:if test="${empty signupUser}">
                    <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요" required>
                </c:if>
            </div>
            <div class="input-group">
                <label for="userNick"></label>
                <input type="text" id="userNick" name="userNick" placeholder="닉네임을 입력하세요" required>

            </div>
            <div class="input-group">
                <label for="userDob"></label>
                <input type="date" id="userDob" name="userDob"  placeholder="생년원일을 입력하세요" required>
            </div>
            <div class="select-group">
                <label for="userGender"></label>
                <select id="userGender" name="userGender" required>
                    <option value="M">남성</option>
                    <option value="F">여성</option>
                </select>
            </div>
            <div class="input-group">
                <label for="userContact"></label>
                <input type="text" id="userContact" name="userContact" placeholder="연락처를 입력하세요" required>
            </div>
            <div class="button-group">
                <button type="button" onclick="confirmSignup()">회원가입</button>
                <button onclick="location.href='/'" type="button">취소</button>
                <button type="reset">초기화</button>
            </div>
        </form>
    </section>
</div>
<%@ include file="../module/footer.jsp"%>
</body>
</html>
