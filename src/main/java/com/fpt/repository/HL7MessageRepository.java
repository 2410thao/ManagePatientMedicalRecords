package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.model.HL7Message;

import java.util.List;

@Repository
public interface HL7MessageRepository extends JpaRepository<HL7Message, Long> {
    List<HL7Message> findByPatientId(Long patientId);
}
