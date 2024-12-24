package com.aivle.mini7.controller;

import com.aivle.mini7.client.api.FastApiClient;
import com.aivle.mini7.client.dto.HospitalResponse;
import com.aivle.mini7.domain.EmergencyRequest;
import com.aivle.mini7.service.EmergencyRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final FastApiClient fastApiClient;
    private final EmergencyRequestService emergencyRequestService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/recommend_hospital")
    public ModelAndView recommend_hospital(@RequestParam("request") String request,
                                           @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        System.out.println("request" + request);
        System.out.println("latitude" + latitude);
        System.out.println("longitude" + longitude);
        // FastApiClient 를 호출한다.
        List<HospitalResponse> hospitalList = fastApiClient.getHospital(request, latitude, longitude);
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ModelAndView mv = new ModelAndView();
        if (!hospitalList.isEmpty()) {
            EmergencyRequest emergencyRequest = new EmergencyRequest();
            emergencyRequest.setDatetime(formattedNow); // 임시 데이터
            emergencyRequest.setInputText(request);
            emergencyRequest.setInputLatitude(latitude);
            emergencyRequest.setInputLongitude(longitude);
            emergencyRequest.setEmclass(1); // 임시 데이터
            emergencyRequest.setHospital1(hospitalList.get(0).getHospitalName());
            emergencyRequest.setAddr1(hospitalList.get(0).getAddress());
            emergencyRequest.setTel1(hospitalList.get(0).getPhoneNumber1());
            emergencyRequest.setHospital2(hospitalList.get(1).getHospitalName());
            emergencyRequest.setAddr2(hospitalList.get(1).getAddress());
            emergencyRequest.setTel2(hospitalList.get(1).getPhoneNumber1());
            emergencyRequest.setHospital3(hospitalList.get(2).getHospitalName());
            emergencyRequest.setAddr3(hospitalList.get(2).getAddress());
            emergencyRequest.setTel3(hospitalList.get(2).getPhoneNumber1());
            emergencyRequestService.saveRequest(emergencyRequest);
        }
        if (hospitalList == null || hospitalList.isEmpty()) {
            mv.setViewName("personal");
            mv.addObject("message", "개인 건강관리");
        } else {
            mv.setViewName("recommend_hospital");
            mv.addObject("hospitalList", hospitalList);
        }
        return mv;
    }

}



