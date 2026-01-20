package com.revature.erms_project1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    // Data Transfer Object, useful for when we need to send data in a request that isn't
    // already an entity
    private String username;
    private String password;
}
