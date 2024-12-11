<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>calendar</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cal.css">
  <script src="${pageContext.request.contextPath}/dynamic/cal.js" defer></script>
</head>
<body>
<div class="layout"
<%@ include file="/views/header.jsp" %>
<div class="calPage">
  <div class="pageLeft">
    <div class="calContent">
      <div class="calHeader">
        <button type="button" class="prevBtn">&lt;</button>
        <div class="dateInfo"></div>
        <button type="button" class="nextBtn">&gt;</button>
      </div>
      <table class="calTable">
        <colgroup>
          <col class="sun">
          <col class="mon">
          <col class="tue">
          <col class="wed">
          <col class="thu">
          <col class="fri">
          <col class="sat">
        </colgroup>
        <thead>
        <tr>
          <th class="sun">일</th>
          <th class="mon">월</th>
          <th class="tue">화</th>
          <th class="wed">수</th>
          <th class="thu">목</th>
          <th class="fri">금</th>
          <th class="sat">토</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </div>
  <div class="pageRight">
    <div class="matchInfo">
      <a>매치정보</a>
    </div>
    <div class="userChat">
      <a>채팅</a>
    </div>
  </div>
</div>
</div>
</body>
</html>
