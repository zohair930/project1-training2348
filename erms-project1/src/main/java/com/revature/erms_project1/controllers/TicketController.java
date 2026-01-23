package com.revature.erms_project1.controllers;

import com.revature.erms_project1.dto.TicketCreateDTO;
import com.revature.erms_project1.entities.Ticket;
import com.revature.erms_project1.entities.TicketStatus;
import com.revature.erms_project1.exceptions.AccountNotFoundException;
import com.revature.erms_project1.exceptions.TicketNotFoundException;
import com.revature.erms_project1.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    // Employee submit
    @PostMapping("/accounts/{accountId}/tickets")
    public ResponseEntity<Ticket> submitTicket(@PathVariable Long accountId,
                                               @RequestBody TicketCreateDTO dto) throws AccountNotFoundException {

        Ticket created = ticketService.submitTicket(accountId, dto);
        System.out.println("Created" + created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Employee view all tickets
    @GetMapping("/accounts/{accountId}/tickets")
    public ResponseEntity<List<Ticket>> getMyTickets(@PathVariable Long accountId) {
        List<Ticket> tickets = ticketService.getTicketsForAccount(accountId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    // Employee view pending tickets
    @GetMapping("/accounts/{accountId}/tickets/pending")
    public ResponseEntity<List<Ticket>> getMyPendingTickets(@PathVariable Long accountId) {
        List<Ticket> tickets = ticketService.getPendingTicketsForAccount(accountId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    // Manager view all pending
    @GetMapping("/tickets/pending")
    public ResponseEntity<List<Ticket>> getAllPending() {
        return new ResponseEntity<>(ticketService.getAllPendingTickets(), HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }

    // Manager approve/deny
    @PatchMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> decide(@PathVariable Long ticketId,
                                         @RequestParam TicketStatus status) throws TicketNotFoundException {
        Ticket updated = ticketService.decideTicket(ticketId, status);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}