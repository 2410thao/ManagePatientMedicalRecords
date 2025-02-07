package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.model.HL7Message;
import com.fpt.service.HL7MessageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hl7-messages")
public class HL7MessageController {
    
    @Autowired
    private HL7MessageService hL7MessageService;

    @GetMapping
    public List<HL7Message> getAllMessages() {
        return hL7MessageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HL7Message> getMessageById(@PathVariable Long id) {
        return hL7MessageService.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public List<HL7Message> getMessagesByPatientId(@PathVariable Long patientId) {
        return hL7MessageService.getMessagesByPatientId(patientId);
    }

    @PostMapping
    public HL7Message createMessage(@RequestBody HL7Message hL7Message) {
        return hL7MessageService.saveMessage(hL7Message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        hL7MessageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}

