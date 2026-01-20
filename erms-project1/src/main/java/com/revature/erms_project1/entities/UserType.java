package com.revature.erms_project1.entities;

import lombok.Getter;
import lombok.Setter;

public enum UserType {
    EMPLOYEE,
    MANAGER;

    @Getter
    @Setter
    private String code = "";

    UserType() {
        this.code = code;
    }

    public static UserType fromCode(String code) {
        for (UserType s : values()) {
            if (s.code.equalsIgnoreCase(code)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}