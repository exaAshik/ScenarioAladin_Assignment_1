package org.example.util;

import java.security.MessageDigest;

public class PasswordHashing {

    public static String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexStr = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexStr.append('0');
                hexStr.append(hex);
            }

            return hexStr.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
