package com.revature.erms_project1.services;

import com.revature.erms_project1.dto.TicketCreateDTO;
import com.revature.erms_project1.entities.Account;
import com.revature.erms_project1.entities.Ticket;
import com.revature.erms_project1.entities.TicketStatus;
import com.revature.erms_project1.exceptions.AccountNotFoundException;
import com.revature.erms_project1.exceptions.TicketNotFoundException;
import com.revature.erms_project1.repositories.AccountRepository;
import com.revature.erms_project1.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Employee submits ticket
    public Ticket submitTicket(Long accountId, TicketCreateDTO dto) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        Ticket ticket = new Ticket();
        ticket.setDescription(dto.getDescription());
        ticket.setPrice(dto.getPrice());
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setAccount(account);

        return ticketRepository.save(ticket);
    }

    // Employee views all tickets for their account
    public List<Ticket> getTicketsForAccount(Long accountId) {
        return ticketRepository.findByAccount_Id(accountId);
    }

    // Employee views pending tickets for their account
    public List<Ticket> getPendingTicketsForAccount(Long accountId) {
        return ticketRepository.findByAccount_IdAndStatus(accountId, TicketStatus.PENDING);
    }

    // Manager views all pending requests
    public List<Ticket> getAllPendingTickets() {
        return ticketRepository.findByStatus(TicketStatus.PENDING);
    }

    // Manager approves/denies
    public Ticket decideTicket(Long ticketId, TicketStatus newStatus) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);

        if (newStatus != TicketStatus.APPROVED && newStatus != TicketStatus.DENIED) {

            newStatus = TicketStatus.PENDING;
        }

        ticket.setStatus(newStatus);
        return ticketRepository.save(ticket);
    }
}