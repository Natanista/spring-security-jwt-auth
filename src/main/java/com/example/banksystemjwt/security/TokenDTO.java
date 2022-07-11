package com.example.banksystemjwt.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class TokenDTO {
    private String token;
}
