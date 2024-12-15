package com.glucoalert_rest.glucoalert.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PredictRequest {

    @NotNull
    private double seringMakan;

    @NotNull
    private double seringHaus;

    @NotNull
    private double seringKencing;

    @NotNull
    private double riwayatDiabetes;

    @NotNull
    private double junkFood;

    @NotNull
    private double jarangOlahraga;

    @NotNull
    private double lukaSusahSembuh;

    @NotNull
    private double obesitas;

    @NotNull
    private double hipertensi;

    @NotNull
    private double etnis;
}
