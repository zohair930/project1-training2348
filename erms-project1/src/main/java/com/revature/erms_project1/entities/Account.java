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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long id;

    private String status;

    private BigDecimal balance;

    @OneToOne(fetch=FetchType.LAZY, optional=false)
    // here, we reference the unique column that we defined above
    // also specify that the values for this column are unique (otherwise, we could have multiple matching records and it wouldn't be 1-to-1)
    @JoinColumn(name = "account_user_id", nullable=false, unique=true)
    private User user;

    //an account can have many tickets
    @OneToMany(targetEntity=Ticket.class, cascade=CascadeType.ALL)
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
