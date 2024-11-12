<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body>

<div class="date-selection">
    <h2>날짜 선택</h2>
    <div class="date-list">
        <!-- 날짜 리스트 -->
        <c:forEach var="date" items="${dates}">
            <span class="date-item" onclick="selectDate('${date}')">${date}</span>
        </c:forEach>
    </div>
</div>

<!-- 경기 목록 컴포넌트 영역 -->
<div id="match-list">
    <%@ include file="match-list.jsp"%>
</div>

    <script>
        // 날짜 선택 시 해당 날짜에 맞는 경기를 가져오기
        function selectDate(date) {
            // 'date'를 선택된 상태로 변경
            document.querySelectorAll('.date-item').forEach(item => item.classList.remove('active'));
            document.querySelector(`[onclick="selectDate('${date}')"]`).classList.add('active');

            // 서버에서 경기 데이터를 가져와서 표시
            fetch(`getMatchesForDate?date=${date}`)
                .then(response => response.text())
                .then(html => {
                    document.getElementById('match-list').innerHTML = html;
                });
        }
    </script>
</body>