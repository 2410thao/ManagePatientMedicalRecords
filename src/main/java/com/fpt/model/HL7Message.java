package com.fpt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "hl7_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HL7Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_id")
    private int patientId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "received_at", columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime receivedAt = LocalDateTime.now();

}
