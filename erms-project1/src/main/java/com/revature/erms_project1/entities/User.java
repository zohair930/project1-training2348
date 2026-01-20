package com.revature.erms_project1.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
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

    @OneToOne(targetEntity=Account.class, cascade=CascadeType.ALL)
    @JoinColumn(name="user_account_id", referencedColumnName = "acc_id")
    private Account userAccount;

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
