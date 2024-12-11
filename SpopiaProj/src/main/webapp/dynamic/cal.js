// calendar.js

// 캘린더 초기화 함수
const calendar = {
    currentDate: new Date(),

    render: function () {
        const dateInfo = document.querySelector('.dateInfo');
        const tbody = document.querySelector('.calTable tbody');

        // 현재 연도와 월 설정
        const year = this.currentDate.getFullYear();
        const month = this.currentDate.getMonth();
        dateInfo.textContent = `${year}년 ${month + 1}월`;

        // 이전/다음 월 계산
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        // 테이블 초기화
        tbody.innerHTML = '';

        let row = document.createElement('tr');

        // 첫 번째 주 공백 추가
        for (let i = 0; i < firstDay; i++) {
            const td = document.createElement('td');
            row.appendChild(td);
        }

        // 날짜 채우기
        for (let date = 1; date <= lastDate; date++) {
            if (row.children.length === 7) {
                tbody.appendChild(row);
                row = document.createElement('tr');
            }
            const td = document.createElement('td');
            td.innerHTML = `<span>${date}</span>`;
            td.className = this.getDayClass(row.children.length);
            row.appendChild(td);
        }

        // 마지막 줄 추가
        if (row.children.length > 0) {
            tbody.appendChild(row);
        }
    },

    // 요일별 클래스 추가 (일요일: red, 토요일: blue)
    getDayClass: function (dayIndex) {
        if (dayIndex === 0) return 'weekSun';
        if (dayIndex === 6) return 'weekSat';
        return '';
    },

    // 이전 월로 이동
    prevMonth: function () {
        this.currentDate.setMonth(this.currentDate.getMonth() - 1);
        this.render();
    },

    // 다음 월로 이동
    nextMonth: function () {
        this.currentDate.setMonth(this.currentDate.getMonth() + 1);
        this.render();
    },

    init: function () {
        document.querySelector('.prevBtn').addEventListener('click', () => this.prevMonth());
        document.querySelector('.nextBtn').addEventListener('click', () => this.nextMonth());
        this.render();
    }
};

// DOM 로드 후 초기화
document.addEventListener('DOMContentLoaded', () => {
    calendar.init();
});