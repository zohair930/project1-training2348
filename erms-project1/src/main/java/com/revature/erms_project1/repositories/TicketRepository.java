package com.revature.erms_project1.repositories;

import com.revature.erms_project1.entities.Ticket;
import com.revature.erms_project1.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByAccount_Id(Long accountId);

    Ticket findTicketById(Long id);

    List<Ticket> findByAccount_IdAndStatus(Long accountId, TicketStatus ticketStatus);
}

