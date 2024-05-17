package org.example.util;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;
    private String userPassword;
}
