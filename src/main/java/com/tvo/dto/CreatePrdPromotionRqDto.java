package com.tvo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreatePrdPromotionRqDto {

    private String prd;

    private String proCode;

    private String tranType;

    private String typeId;

    private String status;

    private String ccy;

    private Integer percentage;

    private String createdBy;

}
