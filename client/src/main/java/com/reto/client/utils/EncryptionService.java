package com.reto.client.utils;

public class EncryptionService {
    private static final String SALT = "X9f#mK2$pL";

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                int shift = SALT.charAt(i % SALT.length()) % 16;
                c = (char) (c + shift);
            }
            encrypted.append(c);
        }
        return encrypted.toString();
    }

    public String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                int shift = SALT.charAt(i % SALT.length()) % 16;
                c = (char) (c - shift);
            }
            decrypted.append(c);
        }
        return decrypted.toString();
    }
}
