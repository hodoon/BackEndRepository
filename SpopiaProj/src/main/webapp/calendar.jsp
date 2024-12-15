<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>calendar</title>
  <link rel="stylesheet" href="static/css/calendar.css">
</head>
<body>
<div class="calPage">
  <div class="pageLeft">
    <div class="calContent">
      <div class="calHeader">
        <button type="button" class="prevBtn">&lt;</button>
        <div class="dateInfo">2024년 12월</div>
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
        <tr>
          <td class="weekSun"><span>1</span></td>
          <td class="weekMon"><span>2</span></td>
          <td class="weekTue"><span>3</span></td>
          <td class="weekWed"><span>4</span></td>
          <td class="weekThu"><span>5</span></td>
          <td class="weekFri"><span>6</span></td>
          <td class="weekSat"><span>7</span></td>
        </tr>
        <tr>
          <td class="weekSun"><span>8</span></td>
          <td class="weekMon"><span>9</span></td>
          <td class="weekTue"><span>10</span></td>
          <td class="weekWed"><span>11</span></td>
          <td class="weekThu"><span>12</span></td>
          <td class="weekFri"><span>13</span></td>
          <td class="weekSat"><span>14</span></td>
        </tr>
        <tr>
          <td class="weekSun"><span>15</span></td>
          <td class="weekMon"><span>16</span></td>
          <td class="weekTue"><span>17</span></td>
          <td class="weekWed"><span>18</span></td>
          <td class="weekThu"><span>19</span></td>
          <td class="weekFri"><span>20</span></td>
          <td class="weekSat"><span>21</span></td>
        </tr>
        <tr>
          <td class="weekSun"><span>22</span></td>
          <td class="weekMon"><span>23</span></td>
          <td class="weekTue"><span>24</span></td>
          <td class="weekWed"><span>25</span></td>
          <td class="weekThu"><span>26</span></td>
          <td class="weekFri"><span>27</span></td>
          <td class="weekSat"><span>28</span></td>
        </tr>
        <tr>
          <td class="weekSun"><span>29</span></td>
          <td class="weekMon"><span>30</span></td>
          <td class="weekTue"><span>31</span></td>
          <td class="weekWed"><span>1</span></td>
          <td class="weekThu"><span>2</span></td>
          <td class="weekFri"><span>3</span></td>
          <td class="weekSat"><span>4</span></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="pageRight">
    <div class="matchInfo">
      <a>매치정보</a>
    </div>
    <div class="userChat">
      <a>채팅</a>
    </div>
  </div>
</div>
</body>
</html>
