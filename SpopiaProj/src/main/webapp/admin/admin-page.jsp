<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/styles.css">
    <title>관리자 페이지</title>
    <style>
        /*-----관리자 페이지 ------*/
        .admin-btn {
            background-color: #999999;
            color: white;
            border: none;
            display: inline-block;
            cursor: pointer;
            border-radius: 5px;
            width: 160px;
            height: 38px;
            padding: 10px;
        }

        .admin-btn:active {
            background-color: #333; /* 버튼을 클릭했을 때 배경색을 진한 회색으로 설정 */
            transform: scale(1.1); /* 버튼을 클릭했을 때 버튼의 크기를 10% 증가시킴 */
        }
        .admin-container {
            font-family: 'Roboto', sans-serif; /* 폰트 설정 */
        }

        .admin-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        .admin-table th, .admin-table td {
            text-align: left;
            padding: 10px;
            border-bottom: 1px solid #ddd; /* 테두리 설정 */
        }

        .admin-table th {
            background-color: #f6f6f6; /* 헤더 배경색 설정 */
            color: #333; /* 헤더 글자색 설정 */
            white-space: nowrap; /* 긴 글자를 한 줄로 유지 */
        }

        .admin-table tr:nth-child(even) {
            background-color: #f2f2f2; /* 짝수 행 배경색 설정 */
        }

        .admin-section {
            margin-bottom: 30px;
        }

        .admin-section-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .admin-action-button {
            white-space: nowrap; /* 긴 글자를 한 줄로 유지 */
            background-color: #007aff; /* 버튼 배경색 설정 */
            color: white; /* 버튼 글자색 설정 */
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            font-size: 14px;
            cursor: pointer;
        }
        /*-----관리자 페이지 ------*/
    </style>
</head>
<script>
    const chatWinOpen = (chatId) => {
        window.open("../chatclient/ChatWindow.jsp?chatId=" + chatId, "", "width=344, height=463");
    }
    const ifNotAdmin = () => {
        alert('접근 실패! 권한을 확인하세요.');
        window.location.href = '/';
    }
    const confirmLeave = (leaveEmail) => {
        if (confirm('정말로 탈퇴 처리하시겠습니까?')) {
            window.location.href = '/admin/user-leave?leaveUser=' + leaveEmail;
        }
    }
    <c:if test="${empty sessionScope.isAdmin}">
    ifNotAdmin();
    </c:if>
</script>
<body>
<%@ include file="../module/header.jsp"%>

<div class="admin-container">
    <h1 class="admin-title">관리자 페이지</h1> <!-- 회원 관리 섹션 -->
    <div class="admin-section">
        <h2 class="admin-section-title">회원 관리</h2>
        <table class="admin-table">
            <thead>
            <tr>
                <th>회원 번호</th>
                <th>이메일</th>
                <th>닉네임</th>
                <th>이름</th>
                <th>상태</th>
                <th>조작</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userNo}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userNick}</td>
                    <td>${user.userName}</td>
                    <td>${user.userRole}</td>
                    <c:if var="userState" test="${user.userState eq 'ACTIVE'}">
                        <td>활성</td>
                    </c:if>
                    <c:if var="userState" test="${user.userState eq 'INACTIVE'}">
                        <td>탈퇴</td>
                    </c:if>
                    <td>
                        <c:if var="isActive" test="${user.userState eq 'ACTIVE'}">
                            <div class="admin-actions">
                                <button type="button" class="admin-action-button" onclick="confirmLeave('${user.userEmail}')">탈퇴</button>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <!-- 다른 회원들의 정보도 동일한 형식으로 추가 -->
            </tbody>
        </table>
    </div>
</div>
</body>
</html>