package com.fpt.repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	Page<Patient> findByPatientId(Integer id, Pageable pageable);
    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    @Query("SELECT p FROM Patient p WHERE DATE(p.dateOfBirth) = :dateOfBirth")
    Page<Patient> findByDateOfBirth(@Param("dateOfBirth") Date dateOfBirth, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE YEAR(dateOfBirth) = :yearOfBirth")
    Page<Patient> findByYearOfBirth(@Param("yearOfBirth") Integer yearOfBirth, Pageable pageable);
    
    @Query("SELECT p FROM Patient p WHERE Month(dateOfBirth) = :monthOfBirth")
    Page<Patient> findByMonthOfBirth(@Param("monthOfBirth") Integer monthOfBirth, Pageable pageable);
    
    @Query("SELECT p FROM Patient p WHERE DAY(dateOfBirth) = :dayOfBirth")
    Page<Patient> findByDayOfBirth(@Param("dayOfBirth") Integer dayOfBirth, Pageable pageable);

}

