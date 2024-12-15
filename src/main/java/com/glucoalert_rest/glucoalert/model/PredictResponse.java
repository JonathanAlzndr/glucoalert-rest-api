package com.glucoalert_rest.glucoalert.model;


import lombok.Builder;
import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PredictResponse {
    private String calculationDetails;
    private String explanation;
    private Double finalResult;
}

