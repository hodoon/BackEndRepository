<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>calendar</title>
  <link rel="stylesheet" href="../css/cal.css">
  <script src="${pageContext.request.contextPath}/dynamic/cal.js" defer></script>
</head>
<body>
<div class="layout">
  <!-- 헤더 파일 포함 -->
  <%@ include file="/module/header.jsp" %>
  <!-- 캘린더 파일 포함 -->
  <%@ include file="/calendar/calPage.jsp" %>
</div>
</body>
</html>
