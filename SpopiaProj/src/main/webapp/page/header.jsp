<!-- /pages/header.jsp -->
<!-- 헤더 시작 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
  <link rel="stylesheet" href="static/css/styles.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
      <form action=".." method="post">
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
</header>
<!-- 헤더 끝 -->