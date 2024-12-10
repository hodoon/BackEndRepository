<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>카카오톡 클론</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/chat.css">
</head>
<body>
<div id="chat-container">
    <div id="messages"></div>
    <form id="message-form">
        <input id="message-input" type="text" placeholder="메시지를 입력하세요..." required>
        <button id="send-button" type="submit">전송</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/dynamic/chat.js"></script>
</body>
</html>
