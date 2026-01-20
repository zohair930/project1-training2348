package com.revature.erms_project1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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

    @OneToOne(mappedBy = "account")
    private User user;

    //an account can have many tickets
    @OneToMany(targetEntity=Ticket.class, cascade=CascadeType.ALL)
    @JoinColumn(name="ticket_fk")
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", status=" + status +
                ", balance=" + balance +
                ", tickets=" + tickets.toString() +
                '}';
    }
}
