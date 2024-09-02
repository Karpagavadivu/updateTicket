package com.ticket.ticketraise.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticket.dto.customerInfo;
import com.ticket.ticketraise.entity.Ticket;
import com.ticket.ticketraise.repository.TicketRepo;

@Service
public class TicketService {
    @Autowired
    private TicketRepo Ticketrepo;

    @Autowired
    private RestTemplate restTemplate;

   // private static final String customer_URL=" http://locahost:9595/api/customers";

    

    public Ticket raiseTicket(Ticket ticket) {
        return Ticketrepo.save(ticket);
    }

    public List<Ticket> viewTicketsByPriority(String priority) {
        return Ticketrepo.findByPriority(priority);
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return Ticketrepo.findById(ticketId);
    }

    public Ticket resolveTicket(Long ticketId) {
        Ticket ticket = Ticketrepo.findById(ticketId).orElseThrow();
        ticket.setStatus("raised");
        ticket.setResolvedAt(LocalDateTime.now());
        return Ticketrepo.save(ticket);
    }

    public void getInfoFromCustomerInfo(Long customerId,Long ticketId) {
        String customerServiceUrl = "http://locahost:9595/api/customers" + customerId;
        customerInfo customer = restTemplate.getForObject(customerServiceUrl, customerInfo.class);

        String name = "CustomerInfo Updated.Customer first name is " + customer.getFirstName();
        String info = "The customer email is '" + customer.getEmail() + "' Customer phone Number is" + customer.getPhoneNumber();
        String address="The customer address is"+ customer.getAddress();

        
    }
    

}