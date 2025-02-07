package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.model.LabResult;

@Repository
public interface LabResultRepository extends JpaRepository<LabResult, Long> {
}
