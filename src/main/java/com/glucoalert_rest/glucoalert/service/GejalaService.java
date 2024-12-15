package com.glucoalert_rest.glucoalert.service;

import com.glucoalert_rest.glucoalert.model.Gejala;
import com.glucoalert_rest.glucoalert.model.PredictRequest;
import com.glucoalert_rest.glucoalert.model.PredictResponse;
import com.glucoalert_rest.glucoalert.repository.GejalaRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GejalaService {
    @Autowired
    private GejalaRepository gejalaRepository;

    public PredictResponse predict(PredictRequest request) {
        List<Gejala> gejalaList = gejalaRepository.findAll();
        
        // Calculate individual CFs
        double cf1 = calculateCF(gejalaList.get(0), request.getSeringMakan(), "Sering Makan", calculations);
        double cf2 = calculateCF(gejalaList.get(1), request.getSeringHaus(), "Sering Haus", calculations);
        double cf3 = calculateCF(gejalaList.get(2), request.getSeringKencing(), "Sering Kencing", calculations);
        double cf4 = calculateCF(gejalaList.get(3), request.getRiwayatDiabetes(), "Riwayat Diabetes", calculations);
        double cf5 = calculateCF(gejalaList.get(4), request.getJunkFood(), "Junk Food", calculations);
        double cf6 = calculateCF(gejalaList.get(5), request.getJarangOlahraga(), "Jarang Olahraga", calculations);
        double cf7 = calculateCF(gejalaList.get(6), request.getLukaSusahSembuh(), "Luka Susah Sembuh", calculations);
        double cf8 = calculateCF(gejalaList.get(7), request.getObesitas(), "Obesitas", calculations);
        double cf9 = calculateCF(gejalaList.get(8), request.getHipertensi(), "Hipertensi", calculations);
        double cf10 = calculateCF(gejalaList.get(9), request.getEtnis(), "Etnis Asia", calculations);

        double cfCombine1 = combineCF(cf1, cf2, "CF1 (Sering Makan)", "CF2 (Sering Haus)", calculations);
        double cfCombine2 = combineCF(cfCombine1, cf3, "CF old", "CF3 (Sering Kencing)", calculations);
        double cfCombine3 = combineCF(cfCombine2, cf4, "CF old", "CF4 (Riwayat Diabetes)", calculations);
        double cfCombine4 = combineCF(cfCombine3, cf5, "CF old", "CF5 (Junk Food)", calculations);
        double cfCombine5 = combineCF(cfCombine4, cf6, "CF old", "CF6 (Jarang Olahraga)", calculations);
        double cfCombine6 = combineCF(cfCombine5, cf7, "CF old", "CF7 (Luka Susah Sembuh)", calculations);
        double cfCombine7 = combineCF(cfCombine6, cf8, "CF old", "CF8 (Obesitas)", calculations);
        double cfCombine8 = combineCF(cfCombine7, cf9, "CF old", "CF9 (Hipertensi)", calculations);
        double cfCombine9 = combineCF(cfCombine8, cf10, "CF old", "CF10 (Etnis Asia)", calculations);

        val calculations = "";

    
        return PredictResponse.builder()
                .calculationDetails(calculations.toString())
                .explanation(explanation)
                .finalResult(cfCombine9 * 100)
                .build();
    }

    private double calculateCF(Gejala gejala, double userCF, String symptomName, StringBuilder calculations) {
        double result = gejala.getCfRule() * userCF;
        calculations.append(String.format("%s:\nCF Pakar (%.2f) x CF User (%.2f) = %.4f\n\n", 
            symptomName, gejala.getCfRule(), userCF, result));
        return result;
    }
    
    private double combineCF(double cf1, double cf2, String cf1Name, String cf2Name, StringBuilder calculations) {
        double result = cf1 + cf2 * (1 - cf1);
        calculations.append(String.format("Kombinasi %s (%.4f) dengan %s (%.4f):\n", cf1Name, cf1, cf2Name, cf2));
        calculations.append(String.format("%.4f + %.4f * (1 - %.4f) = %.4f\n\n",
            cf1, cf2, cf1, result));
        return result;
    }


    private String buildExplanation(double result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Berdasarkan perhitungan Certainty Factor, ");
        sb.append(String.format("tingkat resiko diabetes Anda adalah %.2f%%.\n\n", result));
        
        if (result >= 80) {
            sb.append("Resiko SANGAT TINGGI. Segera konsultasi dengan dokter.");
        } else if (result >= 60) {
            sb.append("Resiko TINGGI. Disarankan untuk melakukan pemeriksaan.");
        } else if (result >= 40) {
            sb.append("Resiko SEDANG. Perlu perhatian khusus pada pola hidup.");
        } else {
            sb.append("Resiko RENDAH. Tetap jaga pola hidup sehat.");
        }
        
        return sb.toString();
    }

}
