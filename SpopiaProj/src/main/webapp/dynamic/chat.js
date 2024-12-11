const socket = new WebSocket("ws://localhost:8080/ChatingServer");
const username = "<%= username %>"; // 로그인한 사용자 이름

socket.onopen = function () {
    console.log("WebSocket 연결 성공!");
    addMessage("시스템", "WebSocket 연결됨.", "system");
};

socket.onmessage = function (event) {
    const data = JSON.parse(event.data);
    const sender = data.sender;
    const message = data.message;

    // 내가 보낸 메시지인지 확인
    const messageType = sender === username ? "mine" : "other";

    addMessage(sender, message, messageType);
};

socket.onclose = function () {
    console.log("WebSocket 연결 종료.");
    addMessage("시스템", "WebSocket 연결이 종료되었습니다.", "system");
};

socket.onerror = function (error) {
    console.error("WebSocket 에러:", error);
    addMessage("시스템", "WebSocket 에러가 발생했습니다.", "system");
};

// 메시지 전송
document.getElementById("message-form").addEventListener("submit", function (event) {
    event.preventDefault();
    const input = document.getElementById("message-input");
    const message = input.value;
    const payload = {
        sender: username,
        message: message,
    };
    socket.send(JSON.stringify(payload));
    addMessage("나", message, "mine");
    input.value = "";
});

// 메시지 추가 함수
function addMessage(sender, message, type) {
    const messages = document.getElementById("messages");
    const messageElement = document.createElement("div");
    messageElement.classList.add("message", type);

    const senderElement = document.createElement("div");
    senderElement.textContent = sender;
    senderElement.style.fontSize = "0.8rem";
    senderElement.style.color = "#888";

    const contentElement = document.createElement("div");
    contentElement.textContent = message;

    if (type === "other") {
        messageElement.appendChild(senderElement); // 발신자 이름 추가
    }
    messageElement.appendChild(contentElement);

    messages.appendChild(messageElement);
    messages.scrollTop = messages.scrollHeight; // 자동 스크롤
}
