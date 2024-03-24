package com.example.aspectsample.service;

import com.example.aspectsample.Entity.SampleEntity;
import com.example.aspectsample.annotation.AspectAnnotation;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @AspectAnnotation
    public SampleEntity setSampleEntity(String greeting, String resultCode) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setGreeting(greeting);
        sampleEntity.setResultCode(resultCode);
        return sampleEntity;
    }

}
