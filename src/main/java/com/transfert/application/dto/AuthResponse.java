// application/dto/AuthResponse.java
package com.transfert.application.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private String role;
    private UUID etablissementId;
}