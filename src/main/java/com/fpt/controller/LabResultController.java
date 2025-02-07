package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.model.LabResult;
import com.fpt.service.LabResultService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lab-results")
public class LabResultController {
    @Autowired
    private LabResultService labResultService;

    @GetMapping
    public List<LabResult> getAllLabResults() {
        return labResultService.getAllLabResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabResult> getLabResultById(@PathVariable Long id) {
        return labResultService.getLabResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LabResult createLabResult(@RequestBody LabResult labResult) {
        return labResultService.saveLabResult(labResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabResult(@PathVariable Long id) {
        labResultService.deleteLabResult(id);
        return ResponseEntity.noContent().build();
    }
}
