<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<head>
  <meta charset="UTF-8">
  <title>Layout Example</title>
  <link rel="stylesheet" href="static/css/body.css">
  <style>
    body {
      margin: 0;
      padding: 0;
    }
    .container {
      display: flex;
      flex-direction: column;
      width: 100%;
    }
    .dateWrap {
      display: flex;
      align-items: center;
      padding: 10px;

    }

    .dataList {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-right: 30px;
      /*border: 1px solid;*/
      width: 150px;
      border-radius: 30px;
      padding: 10px;
      margin-top: 20px;
    }

    .dataList:hover {
      background-color: blue;
      color: white;

    }

    .cateWrap {
      display: flex;
      align-items: center;
      padding: 10px;
    }
    .cateList {
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-right: 30px;

    }
    .listWrap {
      /*padding: 20px;*/
      display: flex;
      flex-direction: column;
      gap: 15px;


    }
    .listItem {
      padding: 15px;
      border: 1px solid #ddd;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .listItem h3 {
      margin: 0 0 10px 0;
      font-size: 1.2em;
    }
    .listItem p {
      margin: 0;
      color: #666;
    }
    .btn-group{
      justify-items: right;
      margin-left: 800px;
      margin-top:-50px
    }
  </style>
</head>
<body>
<div class="container">
  <div class="dateWrap">
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">12</p><span>화</span></li>
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">13</p><span>수</span></li>
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">14</p><span>목</span></li>
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">15</p><span>금</span></li>
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">16</p><span>토</span></li>
    <li class="dataList"><p style="font-size: 18px;font-weight: bold;">17</p><span>일</span></li>
  </div>
  <div class="cateWrap">
    <li class="cateList"><p>지역</p></li>
    <li class="cateList"><p>성별</p></li>
    <li class="cateList"><p>나이</p></li>
    <li class="cateList"><p>종목</p></li>
  </div>
  <div class="listWrap">
    <div class="listItem">
      <p>15:00</p>
      <h3>서울 은평 롯데몰 B구장</h3>
      <p>남녀모두</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
    <div class="listItem">
      <p>16:00</p>
      <h3>서울 도봉 루다 풋살장</h3>
      <p>남자</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
    <div class="listItem">
      <p>18:00</p>
      <h3>서울 강서 KBS 스포츠월드 야외 *주차 3자리*</h3>
      <p>여자</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
    <div class="listItem">
      <p>15:00</p>
      <h3>서울 은평 롯데몰 B구장</h3>
      <p>남녀모두</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
    <div class="listItem">
      <p>15:00</p>
      <h3>서울 은평 롯데몰 B구장</h3>
      <p>남녀모두</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
    <div class="listItem">
      <p>15:00</p>
      <h3>서울 은평 롯데몰 B구장</h3>
      <p>남녀모두</p>
      <div class="btn-group">
        <button style="width: 100px;
        height: 50px;
        font-size: 20px;
        border: solid 1px blue;
        outline: none;
        border-radius: 20px;
        background-color: inherit ;
        cursor: pointer;">신청 가능</button>
      </div>
    </div>
  </div>
</div>
</body>