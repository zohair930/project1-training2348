package com.revature.repositories;

import com.revature.entities.Ticket;
import com.revature.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByAccount_Id(Long accountId);

    List<Ticket> findByAccount_IdAndStatus(Long accountId, TicketStatus ticketStatus);
}

