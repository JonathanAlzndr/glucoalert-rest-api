package com.glucoalert_rest.glucoalert.repository;

import com.glucoalert_rest.glucoalert.model.Gejala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GejalaRepository extends JpaRepository<Gejala, Integer> {
}
