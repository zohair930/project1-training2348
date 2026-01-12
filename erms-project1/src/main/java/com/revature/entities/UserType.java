package com.revature.entities;

import lombok.Getter;

public enum UserType {
    EMPLOYEE,
    MANAGER;

    @Getter
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