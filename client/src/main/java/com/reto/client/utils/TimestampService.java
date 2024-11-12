package com.reto.client.utils;

public class TimestampService {
    public String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public long parseTimestamp(String timestamp) {
        return Long.parseLong(timestamp);
    }
}
