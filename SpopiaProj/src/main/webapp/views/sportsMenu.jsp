<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/header.css">

<!-- 스포츠 메뉴가 들어가는 두 번째 줄 -->
<div class="sports-menu">
    <span class="menu-item">농구</span>
    <span class="menu-item">축구</span>
    <span class="menu-item">배드민턴</span>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const menuItems = document.querySelectorAll(".sports-menu .menu-item");

        // 메뉴 항목 클릭 시 bold 유지
        menuItems.forEach(item => {
            item.addEventListener("click", function() {
                // 기존 active 클래스 제거
                menuItems.forEach(i => i.classList.remove("active"));
                // 클릭한 항목에 active 클래스 추가
                item.classList.add("active");
            });
        });
    });
</script>
