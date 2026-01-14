package com.revature.erms_project1.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

    @Override
    public String convertToDatabaseColumn(UserType userType) {
        return userType != null ? userType.getCode() : null;
    }

    @Override
    public UserType convertToEntityAttribute(String code) {
        return code != null ? UserType.fromCode(code) : null;
    }
}