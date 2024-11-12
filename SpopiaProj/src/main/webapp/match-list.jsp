<!-- /components/match-list.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 경기 목록 컴포넌트 -->
<div class="match-list-container">
  <link rel="stylesheet" href="static/css/styles.css">
  <h2>경기 일정</h2>
  <c:choose>
    <c:when test="${not empty matches}">
      <ul class="match-list">
        <c:forEach var="match" items="${matches}">
          <li class="match-item">
            <div class="match-time">${match.time}</div>
            <div class="match-info">
              <span class="match-title">${match.title}</span>
              <span class="match-location">${match.location}</span>
            </div>
          </li>
        </c:forEach>
      </ul>
    </c:when>
    <c:otherwise>
      <p class="no-matches">선택한 날짜에 경기가 없습니다.</p>
    </c:otherwise>
  </c:choose>
</div>