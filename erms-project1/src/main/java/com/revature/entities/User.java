package com.revature.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique=true, nullable=false)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)@NonNull
    private UserType userType;

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", userType=" + userType +
                '}';
    }
}
