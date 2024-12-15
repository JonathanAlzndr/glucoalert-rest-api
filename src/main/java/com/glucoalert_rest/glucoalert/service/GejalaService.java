package com.glucoalert_rest.glucoalert.service;

import com.glucoalert_rest.glucoalert.model.Gejala;
import com.glucoalert_rest.glucoalert.model.PredictRequest;
import com.glucoalert_rest.glucoalert.repository.GejalaRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GejalaService {

    @Autowired
    private GejalaRepository gejalaRepository;


    public double predict(PredictRequest request) {
        List<Gejala> gejalaList = gejalaRepository.findAll();
        double cf1 = gejalaList.get(0).getCfRule() * request.getSeringMakan();
        double cf2 = gejalaList.get(1).getCfRule() * request.getSeringHaus();
        double cf3 = gejalaList.get(2).getCfRule() * request.getSeringKencing();
        double cf4 = gejalaList.get(3).getCfRule() * request.getRiwayatDiabetes();
        double cf5 = gejalaList.get(4).getCfRule() * request.getJunkFood();
        double cf6 = gejalaList.get(5).getCfRule() * request.getJarangOlahraga();
        double cf7 = gejalaList.get(6).getCfRule() * request.getLukaSusahSembuh();
        double cf8 = gejalaList.get(7).getCfRule() * request.getObesitas();
        double cf9 = gejalaList.get(8).getCfRule() * request.getHipertensi();
        double cf10 = gejalaList.get(9).getCfRule() * request.getEtnis();

        double cfCombine1 = predictCfCombine(cf1, cf2);
        double cfCombine2 = predictCfCombine(cfCombine1, cf3);
        double cfCombine3 = predictCfCombine(cfCombine2, cf4);
        double cfCombine4 = predictCfCombine(cfCombine3, cf5);
        double cfCombine5 = predictCfCombine(cfCombine4, cf6);
        double cfCombine6 = predictCfCombine(cfCombine5, cf7);
        double cfCombine7 = predictCfCombine(cfCombine6, cf8);
        double cfCombine8 = predictCfCombine(cfCombine7, cf9);
        double cfCombine9 = predictCfCombine(cfCombine8, cf10);

        return cfCombine9 * 100;
    }

    private double predictCfCombine(double firstCf, double secondCf) {
        return firstCf + secondCf * (1 - firstCf);
    }

}
