package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.model.LabTest;
import com.fpt.repository.LabTestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LabTestService {
    @Autowired
    private LabTestRepository labTestRepository;

    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }

    public Optional<LabTest> getLabTestById(Long id) {
        return labTestRepository.findById(id);
    }

    public LabTest saveLabTest(LabTest labTest) {
        return labTestRepository.save(labTest);
    }

    public void deleteLabTest(Long id) {
        labTestRepository.deleteById(id);
    }
}
