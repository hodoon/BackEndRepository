<!-- /pages/header.jsp -->
<!-- 헤더 시작 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/header.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <div class="layout">
    <div class="main-menu-container">
      <!-- 메뉴 아이콘 (왼쪽) -->
      <div class="menu-left">
        <!-- 로고 (메뉴 아이콘 오른쪽) -->
        <div class="logo">
          <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/static/images/ic_spopiaLogo.svg" alt="Logo">
          </a>
        </div>
      </div>
      <!-- 검색바 (맨 오른쪽) -->
      <div class="menu-right">
        <div class="search-box">
          <form action=".." method="post">
            <button class="search-btn" type="submit">
              <i class="fas fa-search"></i>
            </button>
            <input class="search-txt" type="text" placeholder="검색">
          </form>
        </div>
        <div class="calendar-icon">
          <a href="${pageContext.request.contextPath}/calendar">
            <img src="${pageContext.request.contextPath}/static/images/ic_calendar.svg" alt="Calendar">
          </a>
        </div>
        <!-- 마이 페이지 아이콘 (검색바 오른쪽) -->
        <div class="my-page-icon">
          <a href="${pageContext.request.contextPath}/checkLogin">
            <img src="${pageContext.request.contextPath}/static/images/ic_my.svg" alt="My Page">
          </a>
        </div>
      </div>
    </div>
    </div>
  <script>

  </script>
</header>
<!-- 헤더 끝 -->