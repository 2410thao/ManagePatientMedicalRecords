package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fpt.model.Patient;
import com.fpt.repository.PatientRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getPatientsById(Integer id, Pageable pageable) {
        return patientRepository.findByPatientId(id, pageable);
    }

    public Page<Patient> getPatientsByName(String name, Pageable pageable) {
        return patientRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public Page<Patient> getPatientsByDateOfBirth(String dateOfBirth, Pageable pageable) {
        try {
            Date parsedDate = dateFormatter.parse(dateOfBirth);
            return patientRepository.findByDateOfBirth(parsedDate, pageable);
        } catch (ParseException e) {
            return Page.empty(); // Trả về danh sách rỗng nếu sai định dạng
        }
    }
    
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }
    
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatientById(Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with ID: " + id);
        }
    }
    
    public Page<Patient> getPatientsByYearOfBirth(Integer yearOfBirth, Pageable pageable) {
        return patientRepository.findByYearOfBirth(yearOfBirth, pageable); 
    }
    
    public Page<Patient> getPatientsByMonthOfBirth(Integer monthOfBirth, Pageable pageable) {
        return patientRepository.findByMonthOfBirth(monthOfBirth, pageable); 
    }
    
    public Page<Patient> getPatientsByDayOfBirth(Integer dayOfBirth, Pageable pageable) {
        return patientRepository.findByDayOfBirth(dayOfBirth, pageable); 
    }
}

