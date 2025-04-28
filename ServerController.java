package com.snhu.sslserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@RestController
public class ServerController {

    @RequestMapping("/hash")
    public String getHash() {
        String data = "Hello World Check Sum!";
        String checksum = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            checksum = bytesToHex(hash);
        } catch (Exception e) {
            return "Error computing hash: " + e.getMessage();
        }

        return "<p>Data: " + data + "</p>" +
               "<p>Checksum (SHA-256): " + checksum + "</p>";
    }

    // Converts bytes to hexadecimal string
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
