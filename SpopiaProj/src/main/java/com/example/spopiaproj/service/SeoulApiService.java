package com.example.spopiaproj.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.spopiaproj.model.FutsalCourtDto;
import com.example.spopiaproj.utils.DBConn;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                // 풋살장 데이터 추출
                String svcId = getTagValue("SVCID", element, "");
                String maxClassNm = getTagValue("MAXCLASSNM", element, "");
                String minClassNm = getTagValue("MINCLASSNM", element, "");
                String svcStatNm = getTagValue("SVCSTATNM", element, "");
                String svcNm = getTagValue("SVCNM", element, "");
                String payAtNm = getTagValue("PAYATNM", element, "");
                String placeNm = getTagValue("PLACENM", element, "");
                String useTgtInfo = getTagValue("USETGTINFO", element, "");
                String svcUrl = getTagValue("SVCURL", element, "");
                double xCoord = Double.parseDouble(getTagValue("X", element, "0.0"));
                double yCoord = Double.parseDouble(getTagValue("Y", element, "0.0"));
                String svcOpnBgndt = getTagValue("SVCOPNBGNDT", element, "");
                String svcOpnEnddt = getTagValue("SVCOPNENDDT", element, "");
                String rcptBgnDt = getTagValue("RCPTBGNDT", element, "");
                String rcptEndDt = getTagValue("RCPTENDDT", element, "");
                String areaNm = getTagValue("AREANM", element, "");
                String telNo = getTagValue("TELNO", element, "");
                String detailContent = getTagValue("DTLCONT", element, "");

                // 지역 필터링 적용
                if (region == null || placeNm.contains(region)) {
                    JSONObject event = new JSONObject();
                    event.put("SVCID", svcId);
                    event.put("MAXCLASSNM", maxClassNm);
                    event.put("MINCLASSNM", minClassNm);
                    event.put("SVCSTATNM", svcStatNm);
                    event.put("SVCNM", svcNm);
                    event.put("PAYATNM", payAtNm);
                    event.put("PLACENM", placeNm);
                    event.put("USETGTINFO", useTgtInfo);
                    event.put("SVCURL", svcUrl);
                    event.put("X", xCoord);
                    event.put("Y", yCoord);
                    event.put("SVCOPNBGNDT", svcOpnBgndt);
                    event.put("SVCOPNENDDT", svcOpnEnddt);
                    event.put("RCPTBGNDT", rcptBgnDt);
                    event.put("RCPTENDDT", rcptEndDt);
                    event.put("AREANM", areaNm);
                    event.put("TELNO", telNo);
                    event.put("DTLCONT", detailContent);

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

    public void saveFutsalCourtData(FutsalCourtDto futsalCourtDto) {
        // 풋살장 데이터 저장
        try (Connection conn = DBConn.getDBConn()){
            String query = "INSERT INTO futsal_courts (svc_id, max_class_name, min_class_name, svc_stat_nm, " +
                    "svc_nm, pay_at_nm, place_nm, use_tgt_info, svc_url, x_coord, " +
                    "y_coord, svc_opn_bgndt, svc_opn_enddt, rcpt_bgn_dt, rcpt_end_dt, area_nm, tel_no, detail_content) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, futsalCourtDto.getSvcId());
                pstmt.setString(2, futsalCourtDto.getMaxClassName());
                pstmt.setString(3, futsalCourtDto.getMinClassName());
                pstmt.setString(4, futsalCourtDto.getSvcStatNm());
                pstmt.setString(5, futsalCourtDto.getSvcNm());
                pstmt.setString(6, futsalCourtDto.getPayAtNm());
                pstmt.setString(7, futsalCourtDto.getPlaceNm());
                pstmt.setString(8, futsalCourtDto.getUseTgtInfo());
                pstmt.setString(9, futsalCourtDto.getSvcUrl());
                pstmt.setDouble(10, futsalCourtDto.getXCoord());
                pstmt.setDouble(11, futsalCourtDto.getYCoord());
                pstmt.setString(12, futsalCourtDto.getSvcOpnBgndt());
                pstmt.setString(13, futsalCourtDto.getSvcOpnEnddt());
                pstmt.setString(14, futsalCourtDto.getRcptBgnDt());
                pstmt.setString(15, futsalCourtDto.getRcptEndDt());
                pstmt.setString(16, futsalCourtDto.getAreaNm());
                pstmt.setString(17, futsalCourtDto.getTelNo());
                pstmt.setString(18, futsalCourtDto.getDetailContent());
                pstmt.executeUpdate();

                System.out.println("데이터베이스에 풋살장 정보 저장 완료: " + futsalCourtDto.getSvcNm());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public JSONArray getSavedFutsalCourtData() {
        JSONArray eventsArray = new JSONArray();

        // 데이터베이스에서 풋살장 정보 조회
        try (Connection conn = DBConn.getDBConn()) {
            String query = "SELECT * FROM futsal_courts";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                // 결과를 JSON 배열로 변환
                while (rs.next()) {
                    JSONObject event = new JSONObject();
                    event.put("svcId", rs.getString("svc_id"));
                    event.put("maxClassName", rs.getString("max_class_name"));
                    event.put("minClassName", rs.getString("min_class_name"));
                    event.put("svcStatNm", rs.getString("svc_stat_nm"));
                    event.put("svcNm", rs.getString("svc_nm"));
                    event.put("payAtNm", rs.getString("pay_at_nm"));
                    event.put("placeNm", rs.getString("place_nm"));
                    event.put("useTgtInfo", rs.getString("use_tgt_info"));
                    event.put("svcUrl", rs.getString("svc_url"));
                    event.put("xCoord", rs.getDouble("x_coord"));
                    event.put("yCoord", rs.getDouble("y_coord"));
                    event.put("svcOpnBgndt", rs.getString("svc_opn_bgndt"));
                    event.put("svcOpnEnddt", rs.getString("svc_opn_enddt"));
                    event.put("rcptBgnDt", rs.getString("rcpt_bgn_dt"));
                    event.put("rcptEndDt", rs.getString("rcpt_end_dt"));
                    event.put("areaNm", rs.getString("area_nm"));
                    event.put("telNo", rs.getString("tel_no"));
                    event.put("detailContent", rs.getString("detail_content"));

                    eventsArray.put(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("데이터 조회 중 오류 발생", e);
        }
        return eventsArray;
    }
}