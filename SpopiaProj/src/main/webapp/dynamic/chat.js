// WebSocket 연결 설정
const socket = new WebSocket("ws://localhost:8080/ChatingServer");

// 연결 성공 시
socket.onopen = function() {
    console.log("WebSocket 연결 성공!");
    addMessage("시스템", "WebSocket 연결됨.");
};

// 메시지 수신 시
socket.onmessage = function(event) {
    const message = event.data;
    addMessage("상대방", message);
};

// 연결 종료 시
socket.onclose = function() {
    console.log("WebSocket 연결 종료.");
    addMessage("시스템", "WebSocket 연결이 종료되었습니다.");
};

// 에러 발생 시
socket.onerror = function(error) {
    console.error("WebSocket 에러:", error);
    addMessage("시스템", "WebSocket 에러가 발생했습니다.");
};

// 메시지 전송
document.getElementById("message-form").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼 기본 동작 방지
    const input = document.getElementById("message-input");
    const message = input.value;
    socket.send(message); // WebSocket으로 메시지 전송
    addMessage("나", message); // 채팅창에 표시
    input.value = ""; // 입력창 초기화
});

// 메시지를 화면에 추가
function addMessage(sender, message) {
    const messages = document.getElementById("messages");
    const messageElement = document.createElement("div");
    messageElement.textContent = `${sender}: ${message}`;
    messages.appendChild(messageElement);
    messages.scrollTop = messages.scrollHeight; // 자동 스크롤
}
