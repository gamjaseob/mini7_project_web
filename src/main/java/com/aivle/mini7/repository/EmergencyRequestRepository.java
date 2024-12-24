package com.aivle.mini7.repository;


import com.aivle.mini7.domain.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, Integer> {
}