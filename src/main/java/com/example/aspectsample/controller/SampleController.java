package com.example.aspectsample.controller;

import com.example.aspectsample.Entity.SampleEntity;
import com.example.aspectsample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/{bizCode:[0-9]{3}}")
    public SampleEntity greetingController(@PathVariable("bizCode") String bizCode) {
        String greeting;
        switch (bizCode) {
            case "001":
                greeting = "Good Morning!";
                break;
            case "002":
                greeting = "Good Afternoon!";
                break;
            case "003":
                greeting = "Good Evening!";
                break;
            default:
                greeting = "XXX";
        }
        return sampleService.setSampleEntity(greeting, bizCode);
    }
}
