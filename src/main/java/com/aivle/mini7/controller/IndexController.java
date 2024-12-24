package com.aivle.mini7.controller;

import com.aivle.mini7.client.api.FastApiClient;
import com.aivle.mini7.client.dto.HospitalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final FastApiClient fastApiClient;

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
        ModelAndView mv = new ModelAndView();
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


