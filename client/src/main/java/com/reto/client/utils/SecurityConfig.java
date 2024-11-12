package com.reto.client.utils;

public class SecurityConfig {
    private static final String DEFAULT_ENCRYPTION_ALGORITHM = "AES";
    private static final int DEFAULT_KEY_SIZE = 256;

    public static String getEncryptionAlgorithm() {
        return DEFAULT_ENCRYPTION_ALGORITHM;
    }

    public static int getKeySize() {
        return DEFAULT_KEY_SIZE;
    }

}
