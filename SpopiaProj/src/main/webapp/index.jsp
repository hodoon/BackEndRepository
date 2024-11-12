<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>JSP - Hello World</title>
  <link rel="stylesheet" href="static/css/styles.css">
</head>
<body>
<div class="layout">
  <%@ include file="/header.jsp"%>
  <%@ include file="body.jsp"%>
    <div class="content">
    </div>
  <%@ include file="footer.jsp"%>

</div>
</body>
</html>