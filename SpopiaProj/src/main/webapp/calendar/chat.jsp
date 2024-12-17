<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<style>
    body {
        margin: 7px;
        width: 330px;
        height: 445px;
    }

    /* 대화창 스타일 */
    #chatWindow {
        border: 1px solid #e5e5e5;
        width: 100%;
        height: 92%;
        overflow-y: scroll;
        background-color: #f7f7f7;
        margin-bottom: 5px;
    }

    #inputSection {
        height: 8%;
        display: flex;
    }

    /* 메시지 입력창 스타일 */
    #chatMessage {
        border: 1px solid #e5e5e5;
        border-radius: 4px;
        padding: 5px;
        margin-right: 5px;
        font-size: 14px;
        width: 70%;
    }

    /* 전송 버튼 스타일 */
    #sendBtn {
        padding: 0 15px;
        border: none;
        border-radius: 4px;
        background-color: #0070c9;
        color: #fff;
        font-size: 14px;
        cursor: pointer;
        width: 30%;
    }

    /* 사용자가 보낸 메시지 스타일 */
    .myMsg {
        text-align: right;
        background-color: #e5e5e5;
        color: #000;
        padding: 8px;
        margin-bottom: 10px;
        border-radius: 4px;
        font-size: 14px;
        margin-left: 8px;
    }

    /* 상담원이 보낸 메시지 스타일 */
    .otherMsg {
        text-align: left;
        background-color: #808080;
        color: #fff;
        padding: 8px;
        margin-bottom: 10px;
        border-radius: 4px;
        font-size: 14px;
        margin-left: 8px;
    }
    /* 스크롤바 디자인 변경 */
    #chatWindow::-webkit-scrollbar {
        width: 8px;
    }

    #chatWindow::-webkit-scrollbar-track {
        background-color: #f7f7f7;
    }

    #chatWindow::-webkit-scrollbar-thumb {
        background-color: #888;
        border-radius: 4px;
    }

    #chatWindow::-webkit-scrollbar-thumb:hover {
        background-color: #555;
    }
</style>
<head>
    <title>채팅</title>
    <script src="${pageContext.request.contextPath}/dynamic/chat.js" defer></script>
</head>

<body>  <!-- 대화창 UI 구조 정의 -->
<div id="chatWindow"></div>
<div id="inputSection">
    <input type="text" id="chatMessage" onkeyup="enterKey();">
    <button id="sendBtn" onclick="sendMessage();">전송</button>
</div>
</body>
</html>