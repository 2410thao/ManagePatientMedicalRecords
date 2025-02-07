package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.model.LabResult;
import com.fpt.repository.LabResultRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LabResultService {
    @Autowired
    private LabResultRepository labResultRepository;

    public List<LabResult> getAllLabResults() {
        return labResultRepository.findAll();
    }

    public Optional<LabResult> getLabResultById(Long id) {
        return labResultRepository.findById(id);
    }

    public LabResult saveLabResult(LabResult labResult) {
        return labResultRepository.save(labResult);
    }

    public void deleteLabResult(Long id) {
        labResultRepository.deleteById(id);
    }
}
