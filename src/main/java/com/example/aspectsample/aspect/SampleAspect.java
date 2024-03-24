package com.example.aspectsample.aspect;

import com.example.aspectsample.service.RedisService;
import com.example.aspectsample.common.UuidGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

    public RedisService redisService;

    private final Logger logger = LoggerFactory.getLogger(SampleAspect.class);

    @Autowired
    public SampleAspect(RedisService redisService) {
        this.redisService = redisService;
    }

    @Around("@annotation(com.example.aspectsample.annotation.AspectAnnotation)")
    public Object sampleAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        String greeting = (String) args[0];
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            // ターゲットメソッドが失敗した場合、エラーコードをセットする
            redisService.set(UuidGenerator.generateUuid(), greeting, RedisService.ResultCode.STATUS_FAILURE.getResultCode());
            logger.error("エラーが発生しました。 {}", e.getMessage());
            throw new Exception("エラーだよ。", e);
        }
        redisService.set(UuidGenerator.generateUuid(), greeting, RedisService.ResultCode.STATUS_SUCCESS.getResultCode());
        logger.info("処理に成功しました。");
        return result;
    }

}
