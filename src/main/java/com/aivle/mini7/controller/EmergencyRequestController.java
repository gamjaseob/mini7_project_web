package com.aivle.mini7.controller;

import com.aivle.mini7.domain.EmergencyRequest;
import com.aivle.mini7.service.EmergencyRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmergencyRequestController {

    private final EmergencyRequestService service;

    @GetMapping("/admintest")
    public ModelAndView getAllRequests() {
        // 서비스에서 데이터 가져오기
        List<EmergencyRequest> requests = service.getAllRequests();
        // ModelAndView 객체 생성
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admintest"); // 반환할 HTML 템플릿 이름
        modelAndView.addObject("requests", requests); // 모델에 데이터 추가
        return modelAndView;
    }
    @GetMapping("/admintest/search")
    public ModelAndView searchByTimeRange(@RequestParam("start") String start,
                                          @RequestParam("end") String end) {
        List<EmergencyRequest> filteredRequests = service.getRequestsByTimeRange(start, end);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admintest");
        modelAndView.addObject("requests", filteredRequests);
        return modelAndView;
    }
}
