package com.example.spopiaproj.controller;

import com.example.spopiaproj.service.SeoulApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import java.io.*;

@WebServlet("/")
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
        try {
            // 서울시 API 호출
            String xmlData = seoulApiService.fetchApiData(API_URL);
            // 서울시 API 결과를 JSON으로 변환
            JSONArray eventData = seoulApiService.parseXmlToJson(xmlData, region);
            // JSON 응답
            resp.getWriter().write(eventData.toString());
            System.out.println("API response: " + eventData.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("API 요청 실패");
        }
    }
}
