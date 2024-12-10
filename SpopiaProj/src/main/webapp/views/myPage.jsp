<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
</head>
<body>
<h1>My Page</h1>
<link rel="stylesheet" type="text/css" href="../static/css/myPage.css">
<p>안녕하세요, <strong><%= session.getAttribute("userName") %></strong>님!</p>
<!-- 상단 메뉴 -->
<div class="menu">
    <a href="?section=applications">신청 내역</a>
    <a href="?section=friends">친구</a>
    <a href="/auth/logout">로그아웃</a>
</div>

<!-- 메인 섹션 -->
<div class="section">
    <h2>신청 내역</h2>
    <table>
        <thead>
        <tr>
            <th>경기장</th>
            <th>경기일</th>
            <th>시작시간</th>
            <th>관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="application" items="${applications}">
            <tr>
                <td>${application.stadium}</td>
                <td>${application.date}</td>
                <td>${application.startTime}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/application/cancel" method="POST" style="display:inline;">
                        <input type="hidden" name="applicationId" value="${application.id}">
                        <button type="submit" onclick="return confirm('신청을 취소하시겠습니까?')">취소</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <section class="friend-list">
        <h2>친구 목록</h2>
        <table>
            <thead>
            <tr>
                <th>이름</th>
                <th>닉네임</th>
                <th>관리</th>
            </tr>
            </thead>
            <tbody>
            <!-- 사용자 데이터 반복 출력 -->
            <c:forEach var="friend" items="${friendList}">
                <tr>
                    <td>${friend.name}</td>
                    <td>${friend.nickname}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/deleteUser" method="GET" style="display:inline;">
                            <input type="hidden" name="userEmail" value="${friend.email}">
                            <button type="submit" onclick="return confirm('사용자를 삭제하시겠습니까?')">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    <form action="${pageContext.request.contextPath}/admin/editUser" method="GET" style="display: inline;">
        <input type="hidden" name="userEmail" value="<%= session.getAttribute("userEmail") %>">
        <button type="submit">수정</button>
    </form>
    <form action="${pageContext.request.contextPath}/admin/deleteUser" method="GET" style="display:inline;">
        <input type="hidden" name="userEmail" value="<%= session.getAttribute("userEmail") %>">
        <button type="submit" onclick="return confirm('정말 탈퇴 하시겠습니까?')">탈퇴</button>
    </form>
</div>
</body>
</html>