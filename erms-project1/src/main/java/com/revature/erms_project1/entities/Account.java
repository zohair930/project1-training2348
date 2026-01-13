package com.revature.erms_project1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long id;

    private String status;

    private BigDecimal balance;

    @JoinColumn(name = "ticket_owner")
    private Ticket ticketOwner;

    @OneToOne(mappedBy = "accountOwner")
    private User user;


}
