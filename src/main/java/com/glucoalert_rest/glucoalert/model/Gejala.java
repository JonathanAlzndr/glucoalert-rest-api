package com.glucoalert_rest.glucoalert.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gejala")
@Setter
@Getter
public class Gejala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_gejala")
    private String namaGejala;

    @Column(name = "cf_rule")
    private double cfRule;

}
