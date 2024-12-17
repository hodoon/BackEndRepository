package com.example.spopiaproj.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class SeoulApiService {
    // API 데이터를 가져오는 메서드
    public String fetchApiData(String apiUrl) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();

        if (responseCode == 200) { // HTTP OK
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } else {
            throw new IOException("API 요청 실패: " + responseCode);
        }
        return result.toString();
    }

    // XML 데이터를 JSON으로 변환
    public JSONArray parseXmlToJson(String xmlData, String region) throws Exception {
        JSONArray eventsArray = new JSONArray();

        // XML 파서 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream xmlStream = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
        Document document = builder.parse(xmlStream);

        NodeList rowList = document.getElementsByTagName("row");

        for (int i = 0; i < rowList.getLength(); i++) {
            Node row = rowList.item(i);
            if (row.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) row;

                // 이벤트 데이터 추출
                String time = getTagValue("RESVE_BGN_TIME", element, "시간 미정");
                String location = getTagValue("MINCLASSNM", element, "장소 미정");

                // 지역 필터링 적용
                if (region == null || location.contains(region)) {
                    JSONObject event = new JSONObject();
                    event.put("time", time);
                    event.put("location", location);
                    event.put("gender", "남녀모두"); // 기본값 설정
                    eventsArray.put(event);
                }
            }
        }
        return eventsArray;
    }

    // XML 태그 값 가져오기
    public String getTagValue(String tagName, Element element, String defaultValue) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return defaultValue;
    }
}