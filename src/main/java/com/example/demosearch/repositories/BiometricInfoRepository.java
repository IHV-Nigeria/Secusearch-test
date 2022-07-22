package com.example.demosearch.repositories;

import com.example.demosearch.model.BiometricInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometricInfoRepository extends JpaRepository<BiometricInfo, Long> {
}
