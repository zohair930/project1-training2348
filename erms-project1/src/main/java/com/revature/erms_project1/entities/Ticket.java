package com.revature.erms_project1.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@Table(name = "tickets")
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
    @ManyToOne
    @JoinColumn(name = "account_fk")
    @JsonBackReference
    private Account account;

    public Ticket() {
        this.status = TicketStatus.PENDING;
    }

    public Ticket(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
        this.status = TicketStatus.PENDING;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", account=" + account +
                '}';
    }
}
