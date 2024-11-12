<!-- /pages/header.jsp -->
<!-- 헤더 시작 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
  <link rel="stylesheet" href="static/css/styles.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <div class="layout">
  <div class="main-menu-container">
  <!-- 메뉴 아이콘 (왼쪽) -->
  <div class="menu-left">
    <div class="menu-icon">
      <img src="static/images/ic_hamburger.png" alt="Menu">
    </div>
  <!-- 로고 (메뉴 아이콘 오른쪽) -->
    <div class="logo">
      <img src="static/images/ic_spopiaLogo.png" alt="Logo">
    </div>
  </div>
  <!-- 검색바 (맨 오른쪽) -->
  <div class="menu-right">
    <div class="search-box">
      <form action="." method="post">
        <input class="search-txt" type="text" placeholder="Search">
        <button class="search-btn" type="submit">
          <i class="fas fa-search"></i>
        </button>
      </form>
    </div>
  <!-- 마이 페이지 아이콘 (검색바 오른쪽) -->
    <div class="my-page-icon">
      <img src="static/images/ic_my.png" alt="My Page">
    </div>
  </div>
  </div>
  <!-- 스포츠 메뉴가 들어가는 두 번째 줄 -->
  <div class="sports-menu">
    <span>농구</span>
    <span>축구</span>
    <span>배드민턴</span>
  </div>
    <!-- 하위 메뉴 -->
    <div class="sub-menu">
      <span>소셜 매치</span>
      <span>팀</span>
      <span>구장 예약</span>
    </div>
  </div>
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      const sportsMenuItems = document.querySelectorAll(".sports-menu span");
      const subMenuItems = document.querySelectorAll(".sub-menu span");

      // 각 sports-menu 항목 클릭 시 bold 유지
      sportsMenuItems.forEach(item => {
        item.addEventListener("click", function() {
          sportsMenuItems.forEach(i => i.classList.remove("active"));
          item.classList.add("active");
        });
      });

      // 각 sub-menu 항목 클릭 시 bold 유지
      subMenuItems.forEach(item => {
        item.addEventListener("click", function() {
          subMenuItems.forEach(i => i.classList.remove("active"));
          item.classList.add("active");
        });
      });
    });
  </script>
</header>
<!-- 헤더 끝 -->