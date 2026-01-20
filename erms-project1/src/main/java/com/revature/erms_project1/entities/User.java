package com.revature.erms_project1.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(unique=true, nullable=false)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)@NonNull
    private UserType userType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account userAccount;

    public User() {
        this.userType = UserType.EMPLOYEE;
    }

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", userType=" + userType +
                ", account=" + userAccount.toString() +
                '}';
    }
}
