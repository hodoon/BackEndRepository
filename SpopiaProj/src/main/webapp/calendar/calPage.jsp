<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<link rel="stylesheet" href="../css/apply.css">
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
      <%@include file="macthInfo.jsp"%>
    </div>
    <div class="userChat">
      <a>채팅</a>
      <%-- <%@include file="chat.jsp"%> --%>
    </div>
  </div>
</div>