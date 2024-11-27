<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String userName = (String) session.getAttribute("userName");
  String userRole = (String) session.getAttribute("userRole");

  if (userName == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>JSP - Hello World</title>
  <link rel="stylesheet" href="static/css/styles.css">
</head>
<body>
<div class="layout">
  <!-- 헤더 파일 포함 -->
  <%@ include file="views/header.jsp" %>

  <!-- 스포츠 메뉴 파일 포함 -->
  <%@ include file="views/sportsMenu.jsp" %>

  <!-- 본문 파일 포함 -->
  <%@ include file="views/body.jsp" %>

  <!-- 푸터 파일 포함 -->
  <%@ include file="views/footer.jsp" %>
</div>
</body>
</html>
