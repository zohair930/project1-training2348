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
@Table(name = "ticket")
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    // many tickets can belong to one account
    @ManyToOne(optional = false)
    @JoinColumn(name = "acc_id")
    private Account account;
}
