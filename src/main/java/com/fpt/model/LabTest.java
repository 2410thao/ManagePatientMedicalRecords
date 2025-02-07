package com.fpt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lab_tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore  // 🔥 Ngăn vòng lặp khi serialize JSON
    private Patient patient;

    private String testName;
    private String testDate;
    private String status;

    @OneToMany(mappedBy = "labTest", cascade = CascadeType.ALL)
    private List<LabResult> labResults;
}

