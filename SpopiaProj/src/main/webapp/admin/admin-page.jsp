<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page - User Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
</head>
<body>
<header class="layout">
    <%@ include file="/module/header.jsp" %>
</header>
<main class="layout">
    <div class="admin-container">
        <h1>관리자 페이지 - 사용자 관리</h1>
        <!-- 사용자 목록 -->
        <section class="user-list">
            <h2>사용자 목록</h2>
            <table>
                <thead>
                <tr>
                    <th>이메일</th>
                    <th>이름</th>
                    <th>닉네임</th>
                    <th>성별</th>
                    <th>생년월일</th>
                    <th>전화번호</th>
                    <th>관리</th>
                </tr>
                </thead>
                <tbody>
                <!-- 사용자 데이터 반복 출력 -->
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.nickname}</td>
                        <td>${user.sex}</td>
                        <td>${user.birthDate}</td>
                        <td>${user.phone}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/editUser" method="GET" style="display: inline;">
                                <input type="hidden" name="userEmail" value="${user.email}">
                                <button type="submit" onclick="return confirm('사용자를 수정하시겠습니까?')">수정</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/deleteUser" method="GET" style="display:inline;">
                                <input type="hidden" name="userEmail" value="${user.email}">
                                <button type="submit" onclick="return confirm('사용자를 삭제하시겠습니까?')">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- 사용자 추가 -->
        <section class="add-user">
            <h2>사용자 추가</h2>
            <form id="addUserForm" action="${pageContext.request.contextPath}/admin/addUser" method="POST">
                <label for="userEmail">이메일:</label>
                <input type="email" id="userEmail" name="userEmail" required>

                <label for="userPassword">비밀번호:</label>
                <input type="password" id="userPassword" name="userPassword" required>

                <label for="userName">이름:</label>
                <input type="text" id="userName" name="userName" required>

                <label for="userNickname">닉네임:</label>
                <input type="text" id="userNickname" name="userNickname" required>

                <label for="userSex">성별:</label>
                <select id="userSex" name="userSex" required>
                    <option value="M">남성</option>
                    <option value="F">여성</option>
                </select>

                <label for="userBirth">생년월일:</label>
                <input type="date" id="userBirth" name="userBirth" required>

                <label for="userTel">전화번호:</label>
                <input type="text" id="userTel" name="userTel" required>

                <button type="submit">추가</button>
            </form>
        </section>
    </div>
</main>
</body>
</html>