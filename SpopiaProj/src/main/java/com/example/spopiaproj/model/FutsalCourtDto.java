package com.example.spopiaproj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FutsalCourtDto {
    private String svcId;
    private String maxClassName;
    private String minClassName;
    private String svcStatNm;
    private String svcNm;
    private String payAtNm;
    private String placeNm;
    private String useTgtInfo;
    private String svcUrl;
    private double xCoord;
    private double yCoord;
    private String svcOpnBgndt;
    private String svcOpnEnddt;
    private String rcptBgnDt;
    private String rcptEndDt;
    private String areaNm;
    private String telNo;
    private String detailContent;
}
