package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.model.LabTest;
import com.fpt.service.LabTestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lab-tests")
public class LabTestController {
    @Autowired
    private LabTestService labTestService;

    @GetMapping
    public List<LabTest> getAllLabTests() {
        return labTestService.getAllLabTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabTest> getLabTestById(@PathVariable Long id) {
        return labTestService.getLabTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabTest createLabTest(@RequestBody LabTest labTest) {
        return labTestService.saveLabTest(labTest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabTest(@PathVariable Long id) {
        labTestService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}

