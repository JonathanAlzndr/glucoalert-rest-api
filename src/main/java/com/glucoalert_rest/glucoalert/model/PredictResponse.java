package com.glucoalert_rest.glucoalert.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PredictResponse {
    private String calculationDetails;
    private String explanation;
    private double finalResult;
}