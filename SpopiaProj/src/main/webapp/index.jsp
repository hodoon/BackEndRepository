<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>JSP - Hello World</title>
  <style>
    .layout {
      display: flex;
      flex-direction: column;
      align-items: center;
      max-width: 1200px;  /* Set max width */
      margin: 0 auto;     /* Center the layout */
      width: 100%;        /* Ensure it takes up full available width */
    }
  </style>
</head>
<body>
<div class="layout">
  <!-- 헤더 파일 포함 -->
  <%@ include file="/module/header.jsp" %>

  <!-- 스포츠 메뉴 파일 포함 -->
  <%@ include file="sportsMenu.jsp" %>

  <!-- 본문 파일 포함 -->
  <%@ include file="/module/body.jsp" %>

  <!-- 푸터 파일 포함 -->
  <%@ include file="/module/footer.jsp" %>
</div>
</body>
</html>
