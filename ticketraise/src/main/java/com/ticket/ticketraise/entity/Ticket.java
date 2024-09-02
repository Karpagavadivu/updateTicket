package com.ticket.ticketraise.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "support_ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue
    private Long ticketId;

    @Column(name = "customer_id")
    private Long customerId;


    private String issueType;
    private String priority;
    private String status;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    @PrePersist
    public void setPriorityBasedOnIssueType() {
        if (issueType != null) {
            switch (issueType.toLowerCase()) {
                case "technical":
                    this.priority = "High";
                    break;
                case "billing":
                    this.priority = "Medium";
                    break;
                default:
                    this.priority = "Low";
                    break;
            }
        }
        this.createdAt = LocalDateTime.now();
        this.status="raised";
    }

}
