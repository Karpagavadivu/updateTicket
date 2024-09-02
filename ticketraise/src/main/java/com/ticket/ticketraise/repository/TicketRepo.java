package com.ticket.ticketraise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketraise.entity.Ticket;


@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{

    List<Ticket> findByPriority(String priority);
    

}
