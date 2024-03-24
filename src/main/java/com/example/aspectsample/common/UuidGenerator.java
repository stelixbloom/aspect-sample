package com.example.aspectsample.common;

import java.util.UUID;

public class UuidGenerator {

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
