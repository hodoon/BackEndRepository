<%@ page import="com.example.spopiaproj.model.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css">
    <style>
        body, input, select, button {
            font-family: 'Noto Sans KR', sans-serif;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 15px 20px;
        }

        label {
            align-self: center;
            font-weight: bold;
        }

        input, select, button {
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button[type="submit"] {
            background-color: #28a745;
            color: white;
        }

        button[type="submit"]:hover {
            background-color: #218838;
        }

        button[type="button"] {
            background-color: #dc3545;
            color: white;
        }

        button[type="button"]:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
<header class="layout">
    <%@ include file="/module/header.jsp" %>
</header>
<main class="layout">
    <div class="admin-container">
        <h1>사용자 수정</h1>

        <%
            System.out.println("sex: " +((UserDto) request.getAttribute("user")).getSex());
        %>

        <section class="edit-user">
            <form id="editUserForm" action="${pageContext.request.contextPath}/admin/editUser" method="POST">
                <!-- Hidden input to hold userEmail for identification -->
                <input type="hidden" id="userEmail" name="userEmail" value="${user.email}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <label for="userName">이름:</label>
                <input type="text" id="userName" name="userName" value="${user.name}" required>

                <label for="userNickname">닉네임:</label>
                <input type="text" id="userNickname" name="userNickname" value="${user.nickname}" required>

                <label for="userBirth">생년월일:</label>
                <input type="date" id="userBirth" name="userBirth" value="${user.birthDate}" required>

                <label for="userTel">전화번호:</label>
                <input type="text" id="userTel" name="userTel" value="${user.phone}" required pattern="^\d{3}-\d{3,4}-\d{4}$" title="전화번호 형식은 000-0000-0000이어야 합니다.">

                <label for="userSex">성별:</label>
                <select id="userSex" name="userSex" required>
                    <option value="M" <c:if test="${user.sex.equals(\"M\")}">selected</c:if>>남성</option>
                    <option value="F" <c:if test="${user.sex == 'F'}">selected</c:if>>여성</option>
                </select>

                <button type="submit">수정 완료</button>
                <button type="button" onclick="cancelEdit()">취소</button>
            </form>
        </section>
    </div>
</main>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    function cancelEdit() {
        window.location.href = `${contextPath}/admin/userManagement`;
    }
</script>
</body>
</html>
