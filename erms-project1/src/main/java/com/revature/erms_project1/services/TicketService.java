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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.Optional;

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

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findTicketById(ticketId);
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
    @Transactional(readOnly = false, rollbackFor = AccountNotFoundException.class)
    public Ticket decideTicket(Long ticketId, Long accountId, Ticket ticket) throws AccountNotFoundException, TicketNotFoundException {
        Ticket sourceTicket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);
        TicketStatus newStatus = ticket.getStatus();
        if (newStatus != TicketStatus.APPROVED && newStatus != TicketStatus.DENIED) {

            newStatus = TicketStatus.PENDING;
        }
        if(newStatus == TicketStatus.APPROVED){
            addFunds(accountId, sourceTicket.getPrice());
        }
        sourceTicket.setStatus(newStatus);
        return ticketRepository.save(sourceTicket);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    // Set up 2 methods, one to add money to an account and one to withdraw money
    public Account addFunds(Long id, BigDecimal moneyToAdd) throws AccountNotFoundException {
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if(accountOptional.isPresent()) {
            // do logic of deducting money
            Account account = accountOptional.get();
            BigDecimal currentAmount = account.getBalance();
            int newAmount = currentAmount.intValue() + moneyToAdd.intValue();
            BigDecimal convert = BigDecimal.valueOf(newAmount);
            account.setBalance(convert);
            // persist changes back to the repository
            account = this.accountRepository.save(account);
            return account;
        }
        else {
            throw new AcceptPendingException();
        }
    }
}