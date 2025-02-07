package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.model.HL7Message;
import com.fpt.repository.HL7MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HL7MessageService {
    
    @Autowired
    private HL7MessageRepository hL7MessageRepository;

    public List<HL7Message> getAllMessages() {
        return hL7MessageRepository.findAll();
    }

    public Optional<HL7Message> getMessageById(Long id) {
        return hL7MessageRepository.findById(id);
    }

    public List<HL7Message> getMessagesByPatientId(Long patientId) {
        return hL7MessageRepository.findByPatientId(patientId);
    }

    public HL7Message saveMessage(HL7Message message) {
        return hL7MessageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        hL7MessageRepository.deleteById(id);
    }
}

