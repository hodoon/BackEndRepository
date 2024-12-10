<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<head>
  <meta charset="UTF-8">
  <title>apply</title>
  <link rel="stylesheet" href="static/css/apply.css">
<%--  <script src="dynamic/body.js"></script>--%>
</head>
<body>
<div class="matchData">
  <div class="matchHeader"><p>구장 사진</p></div>
  <div class="matchBody">
    <section class="section">
      <div class="matchinfo">
        <h1>매치 정보</h1>
        <ul>
          <li class="infoList"><p>종목</p></li>
          <li class="infoList"><p>성별</p></li>
          <li class="infoList"><p>인원</p></li>
        </ul>
      </div>
    </section>
    <section class="section">
      <div class="contentInfo">
        <div class="contentHead">
          <div class="matchTime">날짜 요일 시간</div>
          <div class="matchPlace">
            <h1>구장명</h1>
            <div class="wtgTool">
              <span class="stadiumAddress">구장 위치</span>
              <span class="addressCopy">주소 복사</span>
              <span class="mapInfo">지도 보기</span>
            </div>
            <div class="matchFee">
              <span class="feeMoney">요금</span>
            </div>
            <div class="matchApply">
              <div class="applyBtn">
                <button type="button" class="aBtn">
                  <p>신청하기</p>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</div>
</body>