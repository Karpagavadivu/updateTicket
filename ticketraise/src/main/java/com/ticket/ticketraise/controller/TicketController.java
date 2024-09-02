package com.ticket.ticketraise.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.ticketraise.entity.Ticket;
import com.ticket.ticketraise.service.TicketService;

@RestController
@RequestMapping("/api/support_tickets")
public class TicketController {
    @Autowired
    private TicketService Ticketservice;

    @PostMapping("/raise")
    public ResponseEntity<Ticket> raiseTicket(@RequestBody Ticket ticket) {
        Ticketservice.getInfoFromCustomerInfo();
        return ResponseEntity.ok(Ticketservice.raiseTicket(ticket));
    }

    @GetMapping("/view-by-priority/{priority}")
    public ResponseEntity<List<Ticket>> viewTicketsByPriority(@PathVariable String priority) {
        List<Ticket> tickets = Ticketservice.viewTicketsByPriority(priority);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/view/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId) {
        Optional<Ticket> ticket=Ticketservice.getTicketById(ticketId);
        if (ticket.isPresent()) {
            return ResponseEntity.ok(ticket.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{ticketId}")
    public Ticket resolveTicket(@PathVariable Long ticketId) {
        return Ticketservice.resolveTicket(ticketId);
    }
}