package com.revature.erms_project1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Enumerated(EnumType.STRING)@NonNull
    private UserType userType;

    //
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //
    @JsonIgnore
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

   // @Override
//    public String toString() {
//        return "User{" +
//                "id=" + userId +
//                ", username=" + username +
//                ", password=" + password +
//                ", userType=" + userType +
//                ", account=" + userAccount.toString() +
//                '}';
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username=" + username +
                ", userType=" + userType +
                '}';
    }

}
