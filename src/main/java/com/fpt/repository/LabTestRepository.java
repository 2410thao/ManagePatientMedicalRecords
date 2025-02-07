package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.model.LabTest;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
