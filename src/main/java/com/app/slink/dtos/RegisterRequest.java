package com.app.slink.dtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RegisterRequest {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
