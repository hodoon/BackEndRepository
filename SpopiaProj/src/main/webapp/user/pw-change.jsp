<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <title>비밀번호 변경</title>
</head>
<style>
    .element-container {
        height: 75%;
    }

    .password-change-container {
        width: 400px;
        margin: 200px auto;
        padding: 20px;
    }
    .password-change-title {
        font-weight: bold;
        margin-block-start: 0.67em;
        margin-block-end: 0.67em;
        margin-inline-start: 0;
        margin-inline-end: 0;
    }
    .password-change-form {
        margin-bottom: 80px;
    }
    .password-change-form label {
        display: block;
        margin-bottom: 5px;
    }
    .password-change-form input[type="password"] {
        width: 93%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .password-change-button {
        background-color: #999999;
        color: white;
        padding: 10px 85px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        font-size: 13px;
        margin-top: 5px;
    }
    .form-group {
        margin-bottom: 15px;
    }
</style>
<script>
    const compareAndSubmit = () => {
        const newPw = document.getElementById('new-password').value;
        const confirmPw = document.getElementById('confirm-password').value;
        if(confirm('정말 변경하시겠습니까?')) {
            if (newPw === confirmPw) {
                const form = document.getElementById('submitForm');
                form.submit();
            } else {
                alert('새로운 비밀번호와 확인 비밀번호가 일치하지 않습니다!');
            }
        }
    };
</script>
<body>
<%@ include file="../module/header.jsp"%>
<div class="element-container">
    <div class="password-change-container">
        <h1 class="password-change-title">비밀번호 수정</h1>
        <form action="${pageContext.request.contextPath}/user/pw-change-proc" class="password-change-form" method="post" id="submitForm">
            <div class="form-group">
                <label for="current-password"></label>
                <input type="password" id="current-password" name="currentPw" placeholder="현재 비밀번호" required>
            </div>
            <div class="form-group">
                <label for="new-password"></label>
                <input type="password" id="new-password" name="newPw" placeholder="새로운 비밀번호" required>
            </div>
            <div class="form-group">
                <label for="confirm-password"></label>
                <input type="password" id="confirm-password" name="confirmPw" placeholder="새로운 비밀번호 확인" required>
            </div>
            <button class="password-change-button" onclick="compareAndSubmit()" type="button">변경</button>
            <button class="password-change-button" onclick="location.href='/'" type="button">취소</button>
        </form>
    </div>
</div>
</body>
</html>