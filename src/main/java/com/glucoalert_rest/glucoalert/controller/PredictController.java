package com.glucoalert_rest.glucoalert.controller;

import com.glucoalert_rest.glucoalert.model.PredictRequest;
import com.glucoalert_rest.glucoalert.model.PredictResponse;
import com.glucoalert_rest.glucoalert.service.GejalaService;
import com.glucoalert_rest.glucoalert.model.PredictResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PredictController {
    @Autowired
    private GejalaService gejalaService;

    @PostMapping(
            path = "/api/predict",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PredictResponse> predict(@RequestBody PredictRequest request) {
            PredictResponse predictValue = gejalaService.predict(request);
            return ResponseEntity.ok(predictValue);
    }
}
