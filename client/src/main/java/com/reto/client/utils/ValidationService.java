package com.reto.client.utils;

import jakarta.persistence.Embeddable;

@Embeddable
public class ValidationService {
    public String generateHash(String input) {
        return String.valueOf(input.hashCode());
    }

    public boolean validateSecureId(String secureId) {
        try {
            String[] parts = secureId.split("_");
            if (parts.length != 3) return false;

            String originalPart = parts[0];
            String storedHash = parts[1];
            String calculatedHash = generateHash(originalPart);

            return storedHash.equals(calculatedHash);
        } catch (Exception e) {
            return false;
        }
    }


}
