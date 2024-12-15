<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<head>
  <meta charset="UTF-8">
  <title>Layout Example</title>
  <link rel="stylesheet" href="./css/body.css">
  <script src="./dynamic/body.js"></script>
</head>
<body>
<div class="container">
  <div class="dateSlider">
    <button class="prevBtn">&lt;</button>
    <div class="dateWrap" id="dateWrap"></div>
    <button class="nextBtn">&gt;</button>
  </div>
  <div class="cateWrap">
    <li class="cateList">
      <select name="region">
        <option>지역</option>
        <option value="gangnam">강남구</option>
        <option value="gangdong">강동구</option>
        <option value="gangbuk">강북구</option>
        <option value="gangseo">강서구</option>
        <option value="gwanak">관악구</option>
        <option value="gwangjin">광진구</option>
        <option value="guro">구로구</option>
        <option value="geumcheon">금천구</option>
        <option value="nowon">노원구</option>
        <option value="dobong">도봉구</option>
        <option value="ddm">동대문구</option>
        <option value="gongjak">동작구</option>
        <option value="mapo">마포구</option>
        <option value="sdm">서대문구</option>
        <option value="seocho">서초구</option>
        <option value="sd">성동구</option>
        <option value="sb">성북구</option>
        <option value="songpa">송파구</option>
        <option value="yangcheon">양천구</option>
        <option value="ydp">영등포구</option>
        <option value="yongsan">용산구</option>
        <option value="ep">은평구</option>
        <option value="jongno">종로구</option>
        <option value="junggu">중구</option>
        <option value="jungnang">중랑구</option>
      </select>
    </li>
    <li class="cateList">
      <select name="gender">
        <option>성별</option>
        <option value="male">남성</option>
        <option value="female">여성</option>
      </select>
    </li>
    <li class="cateList">
      <select name="age">
        <option>나이</option>
        <option value="10s">10대</option>
        <option value="20s">20대</option>
        <option value="30s">30대</option>
        <option value="40s">40대</option>
        <option value="50s">50대 이상</option>
      </select>
    </li>
  </div>
  <div class="listWrap"></div>
</div>
</body>