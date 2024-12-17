package com.example.spopiaproj.controller;

import com.example.spopiaproj.model.FutsalCourtDto;
import com.example.spopiaproj.service.SeoulApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

@WebServlet("/seoul/api")
public class SeoulApiController extends HttpServlet {
    SeoulApiService seoulApiService = new SeoulApiService();
    // 서울시 API URL
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/517474446f68726f31313165546a7562/xml/ListPublicReservationSport/1/5/%ED%92%8B%EC%82%B4%EC%9E%A5";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 요청된 지역 필터링 파라미터 가져오기
        String region = req.getParameter("region");
        if(seoulApiService.getSavedFutsalCourtData().isEmpty()) {
            try {
                // 서울시 API 호출
                String xmlData = seoulApiService.fetchApiData(API_URL);
                // 서울시 API 결과를 JSON으로 변환
                JSONArray eventData = seoulApiService.parseXmlToJson(xmlData, region);
                // JSON 응답
                for (int i = 0; i < eventData.length(); i++) {
                    JSONObject event = eventData.getJSONObject(i);
                    FutsalCourtDto futsalCourtDto = new FutsalCourtDto();
                    futsalCourtDto.setSvcId(event.getString("SVCID"));
                    futsalCourtDto.setMaxClassName(event.getString("MAXCLASSNM"));
                    futsalCourtDto.setMinClassName(event.getString("MINCLASSNM"));
                    futsalCourtDto.setSvcStatNm(event.getString("SVCSTATNM"));
                    futsalCourtDto.setSvcNm(event.getString("SVCNM"));
                    futsalCourtDto.setPayAtNm(event.getString("PAYATNM"));
                    futsalCourtDto.setPlaceNm(event.getString("PLACENM"));
                    futsalCourtDto.setUseTgtInfo(event.getString("USETGTINFO"));
                    futsalCourtDto.setSvcUrl(event.getString("SVCURL"));
                    futsalCourtDto.setXCoord(event.getDouble("X"));
                    futsalCourtDto.setYCoord(event.getDouble("Y"));
                    futsalCourtDto.setSvcOpnBgndt(event.getString("SVCOPNBGNDT"));
                    futsalCourtDto.setSvcOpnEnddt(event.getString("SVCOPNENDDT"));
                    futsalCourtDto.setRcptBgnDt(event.getString("RCPTBGNDT"));
                    futsalCourtDto.setRcptEndDt(event.getString("RCPTENDDT"));
                    futsalCourtDto.setAreaNm(event.getString("AREANM"));
                    futsalCourtDto.setTelNo(event.getString("TELNO"));
                    futsalCourtDto.setDetailContent(event.getString("DTLCONT"));


                    seoulApiService.saveFutsalCourtData(futsalCourtDto);
                }

                resp.getWriter().write(eventData.toString());
                System.out.println("API response: " + eventData.toString());
            } catch (Exception e) {
                e.printStackTrace();
                resp.getWriter().write("API 요청 실패");
            }
        }else {
            // 데이터베이스에 이미 풋살장 정보가 있으면 해당 데이터를 조회하여 응답
            JSONArray eventData = seoulApiService.getSavedFutsalCourtData(); // 데이터베이스에서 저장된 데이터 조회
            resp.getWriter().write(eventData.toString());
            System.out.println("저장된 데이터 응답: " + eventData.toString());
        }
    }
}