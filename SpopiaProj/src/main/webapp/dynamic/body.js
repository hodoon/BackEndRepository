let currentStartDate = new Date(); // 슬라이드 시작 날짜를 현재 날짜로 설정

// 날짜 생성 함수 (슬라이드)
function generateWeekDates(startDate) {
    const dateWrap = document.getElementById("dateWrap");
    dateWrap.innerHTML = ""; // 기존 날짜 초기화

    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    const maxDays = 7; // 슬라이드에서 보여줄 날짜 수

    for (let i = 0; i < maxDays; i++) {
        const futureDate = new Date(startDate);
        futureDate.setDate(startDate.getDate() + i);

        const day = futureDate.getDate();
        const dayOfWeek = daysOfWeek[futureDate.getDay()];

        const li = document.createElement("li");
        li.className = "dataList";
        li.dataset.date = futureDate.toISOString().split("T")[0]; // 날짜 정보 저장

        const p = document.createElement("p");
        p.textContent = day;

        const span = document.createElement("span");
        span.textContent = dayOfWeek;

        li.appendChild(p);
        li.appendChild(span);

        // 날짜 클릭 이벤트 추가
        li.addEventListener("click", () => {
            const selectedDate = li.dataset.date;
            console.log(`선택된 날짜: ${selectedDate}`);
            renderEventsByDate(selectedDate); // 선택한 날짜의 이벤트 렌더링
        });

        dateWrap.appendChild(li);
    }
}

// XML 데이터를 JSON으로 변환
function xmlToJson(xml) {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(xml, "application/xml");
    const items = xmlDoc.getElementsByTagName("row");
    const result = [];

    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        result.push({
            time: item.getElementsByTagName("RESVE_BGN_TIME")[0]?.textContent || "시간 미정",
            location: item.getElementsByTagName("MINCLASSNM")[0]?.textContent || "장소 미정",
            gender: "남녀모두", // 성별은 기본값
        });
    }

    return result;
}

// API 데이터 가져오기
async function fetchEventsFromAPI(region = null) {
    const apiUrl =
        "http://openapi.seoul.go.kr:8088/517474446f68726f31313165546a7562/xml/ListPublicReservationSport/1/5/%ED%92%8B%EC%82%B4%EC%9E%A5";
    try {
        const response = await fetch(apiUrl);
        const xml = await response.text(); // XML 형식으로 받아오기
        const data = xmlToJson(xml); // XML을 JSON으로 변환

        // 지역별 필터링
        if (region) {
            return data.filter((event) => event.location.includes(region));
        }
        return data;
    } catch (error) {
        console.error("API 데이터를 불러오는 데 실패했습니다:", error);
        return [];
    }
}

// 이벤트 생성
function createEventItem({ time, location, gender, isFull }) {
    const eventItem = document.createElement("div");
    eventItem.className = "listItem";

    // 신청 가능 여부에 따라 버튼 텍스트 설정
    const availableButton = isFull ?
        `<button class="closed">마감</button>` :
        `<button class="available">신청 가능</button>`;

    eventItem.innerHTML = `
    <p>${time}</p>
    <h3>${location}</h3>
    <p>${gender}</p>
    <div class="btn-group">
      ${availableButton}
    </div>
  `;

    const button = eventItem.querySelector("button");
    if (button && !isFull) {
        button.addEventListener("click", () => {
            window.location.href = "../apply.jsp"; // apply.jsp로 리다이렉트
        });
    }

    return eventItem;
}

// 날짜에 따른 이벤트 렌더링 함수
async function renderEventsByDate(selectedDate) {
    const listWrap = document.querySelector(".listWrap");
    listWrap.innerHTML = ""; // 기존 이벤트 목록 초기화

    try {
        const events = await fetchEventsFromAPI();

        const filteredEvents = events.filter((event) =>
            event.time.startsWith(selectedDate)
        );
        if (filteredEvents.length === 0) {
            listWrap.innerHTML = "<p>선택한 날짜에 이벤트가 없습니다.</p>";
            return;
        }

        filteredEvents.forEach((event) => {
            const eventItem = createEventItem(event);
            listWrap.appendChild(eventItem);
        });
    } catch (error) {
        listWrap.innerHTML = "<p>데이터를 불러오는 데 실패했습니다. 다시 시도해 주세요.</p>";
    }
}

// 지역별 이벤트 렌더링 함수
async function renderEvents(region = null) {
    const listWrap = document.querySelector(".listWrap");
    listWrap.innerHTML = ""; // 기존 이벤트 목록 초기화

    try {
        const events = await fetchEventsFromAPI(region);

        if (events.length === 0) {
            listWrap.innerHTML = "<p>이벤트가 없습니다.</p>";
            return;
        }

        events.forEach((event) => {
            const eventItem = createEventItem(event);
            listWrap.appendChild(eventItem);
        });
    } catch (error) {
        listWrap.innerHTML = "<p>데이터를 불러오는 데 실패했습니다. 다시 시도해 주세요.</p>";
    }
}

// 초기화
document.addEventListener("DOMContentLoaded", () => {
    currentStartDate = new Date(); // 현재 날짜로 초기화
    generateWeekDates(currentStartDate); // 초기 날짜 생성
    renderEvents(); // 초기 이벤트 렌더링

    // 슬라이드 버튼 이벤트 추가
    document.querySelector(".prevBtn").addEventListener("click", () => {
        const newStartDate = new Date(currentStartDate);
        newStartDate.setDate(currentStartDate.getDate() -1); // 하루 이전으로 이동
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        if (newStartDate < today) return; // 오늘 이전으로 이동 불가

        currentStartDate = newStartDate;
        generateWeekDates(currentStartDate);
    });

    document.querySelector(".nextBtn").addEventListener("click", () => {
        const newStartDate = new Date(currentStartDate);
        newStartDate.setDate(currentStartDate.getDate() + 1); // 하루 이후로 이동
        const maxDate = new Date();
        maxDate.setDate(new Date().getDate() + 7); // 오늘부터 7일 뒤까지 제한

        if (newStartDate > maxDate) return; // 1주 뒤를 넘어가면 동작하지 않음

        currentStartDate = newStartDate;
        generateWeekDates(currentStartDate);
    });
});

// 지역 선택 변경 이벤트
document.querySelector("[name='region']").addEventListener("change", function () {
    const selectedRegion = this.value; // 선택한 지역
    renderEvents(selectedRegion); // 해당 지역으로 필터링하여 이벤트 렌더링
});
