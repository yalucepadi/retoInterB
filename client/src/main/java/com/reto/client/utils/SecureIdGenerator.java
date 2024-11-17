package com.reto.client.utils;

import jakarta.persistence.Embeddable;

@Embeddable
public class SecureIdGenerator {
    private final EncryptionService encryptionService;
    private final ValidationService validationService;
    private final TimestampService timestampService;

    public SecureIdGenerator() {
        this.encryptionService = new EncryptionService();
        this.validationService = new ValidationService();
        this.timestampService = new TimestampService();
    }

    public String generateSecureId() {
        String baseUUID = java.util.UUID.randomUUID().toString();
        String encrypted = encryptionService.encrypt(baseUUID);
        String hash = validationService.generateHash(encrypted);
        String timestamp = timestampService.getCurrentTimestamp();

        return String.format("%s_%s_%s", encrypted, hash, timestamp);
    }

    public static String generateSecureId(EncryptionService encryptionService,
                                          ValidationService validationService,
                                          TimestampService timestampService) {

        String baseUUID = java.util.UUID.randomUUID().toString();


        String encrypted = encryptionService.encrypt(baseUUID);


        String hash = validationService.generateHash(encrypted);


        String timestamp = timestampService.getCurrentTimestamp();


        return String.format("%s_%s_%s", encrypted, hash, timestamp);
    }


    public boolean validateId(String secureId) {
        return validationService.validateSecureId(secureId);
    }
}
