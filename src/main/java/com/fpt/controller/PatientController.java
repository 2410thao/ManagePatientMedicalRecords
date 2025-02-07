package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.model.Patient;
import com.fpt.service.PatientService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPatients(
            @RequestParam(value="patientId", required = false) Integer patientId,
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value="dateOfBirth", required = false) String dateOfBirth,
            @RequestParam(value = "yearOfBirth", required = false) Integer yearOfBirth,
            @RequestParam(value = "monthOfBirth", required = false) Integer monthOfBirth,
            @RequestParam(value = "dayOfBirth", required = false) Integer dayOfBirth,
            @RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="limit", defaultValue="10") int limit) {
    	
    	Pageable pageable = PageRequest.of(page, limit, Sort.by("name").ascending());
    	Page<Patient> patientsPage;
    	if (patientId != null) {
            patientsPage = patientService.getPatientsById(patientId, pageable);
        } else if (name != null) {
            patientsPage = patientService.getPatientsByName(name, pageable);
        } else if (dateOfBirth != null) {
            patientsPage = patientService.getPatientsByDateOfBirth(dateOfBirth, pageable);
        }else if (yearOfBirth != null) { 
            patientsPage = patientService.getPatientsByYearOfBirth(yearOfBirth, pageable);
        }else if (monthOfBirth != null) { 
            patientsPage = patientService.getPatientsByMonthOfBirth(monthOfBirth, pageable);
        }else if (dayOfBirth != null) { 
            patientsPage = patientService.getPatientsByDayOfBirth(dayOfBirth, pageable);
        } else {
            patientsPage = patientService.getAllPatients(pageable);
        }

        if (patientsPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("status", "error", "message", "No patients found")
            );
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", patientsPage.getContent());
        response.put("currentPage", patientsPage.getNumber());
        response.put("totalPages", patientsPage.getTotalPages());
        response.put("totalItems", patientsPage.getTotalElements());

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPatientById(@PathVariable("id") Integer id,
    		@RequestParam(value="page", defaultValue="0") int page,
            @RequestParam(value="limit", defaultValue="10") int limit) {
    	
    	 Pageable pageable = PageRequest.of(page, limit);
    	 Page<Patient> patientsPage = patientService.getPatientsById(id, pageable);
    	 
    	 if (patientsPage.isEmpty()) {
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    	                Map.of("status", "error", "message", "No patients found with ID: " + id)
    	        );
    	    }

    	    Map<String, Object> response = new HashMap<>();
    	    response.put("status", "success");
    	    response.put("data", patientsPage.getContent());
    	    response.put("currentPage", patientsPage.getNumber());
    	    response.put("totalPages", patientsPage.getTotalPages());
    	    response.put("totalItems", patientsPage.getTotalElements());

    	    return ResponseEntity.ok(response);
    }

    


    @PostMapping
    public ResponseEntity<Map<String, Object>> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);

        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Patient created successfully");
        response.put("data", savedPatient);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePatient(
            @PathVariable("id") Integer id,
            @RequestBody Patient updatedPatient) {

        Optional<Patient> existingPatient = patientService.getPatientById(id);

        if (existingPatient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("status", "error", "message", "Patient not found with ID: " + id)
            );
        }

        // Cập nhật thông tin bệnh nhân
        Patient patient = existingPatient.get();
        patient.setName(updatedPatient.getName());
        patient.setDateOfBirth(updatedPatient.getDateOfBirth());
        patient.setGender(updatedPatient.getGender());
        patient.setPhone(updatedPatient.getPhone());
        patient.setAddress(updatedPatient.getAddress());

        // Lưu vào DB
        Patient savedPatient = patientService.updatePatient(patient);

        // Trả về JSON chuẩn RESTful API
        return ResponseEntity.ok(
                Map.of("status", "success", "message", "Patient updated successfully", "data", savedPatient)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Integer id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok().body(Map.of("status", "success", "message", "Patient deleted successfully with ID: "+ id));
    }

}

