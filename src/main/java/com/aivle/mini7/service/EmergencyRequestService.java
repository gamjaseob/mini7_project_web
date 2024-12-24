package com.aivle.mini7.service;

import com.aivle.mini7.domain.EmergencyRequest;
import com.aivle.mini7.repository.EmergencyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyRequestService {

    private final EmergencyRequestRepository repository;

    // 모든 데이터 가져오기
    public List<EmergencyRequest> getAllRequests() {
        return repository.findAll();
    }
}