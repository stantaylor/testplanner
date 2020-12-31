package com.aphids.testplanner.controller;

import com.aphids.testplanner.exception.ResourceNotFoundException;
import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestcaseController {

    @Autowired
    TestcaseService testcaseService;

    // Get all testcases
    @GetMapping("/testcases")
    public List<Testcase> getAllTestcases() {

        return testcaseService.findAll();

    }

    // Create a new testcase
    @PostMapping("/testcases")
    public Testcase createTestcase(@Valid @RequestBody Testcase testcase) {

        return testcaseService.add(testcase);

    }

    // Get a single testcase
    @GetMapping("/testcases/{id}")
    public Testcase getTestcaseById(@PathVariable(value = "id") Long testcaseId) {

        return testcaseService.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));

    }

    // Update a testcase
    @PutMapping("/testcases/{id}")
    public Testcase updateTestcase(@PathVariable(value = "id") Long testcaseId, @Valid @RequestBody Testcase testcaseDetails) {

        Testcase testcase = testcaseService.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));

        testcase.setName(testcaseDetails.getName());
        testcase.setDescription(testcaseDetails.getDescription());
        return testcaseService.update(testcase);

    }

    // Delete a testcase
    @DeleteMapping("/testcases/{id}")
    public ResponseEntity<?> deleteTestcase(@PathVariable(value = "id") Long testcaseId) {

        Testcase testcase = testcaseService.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));
        testcaseService.delete(testcase);
        return ResponseEntity.ok().build();

    }
}
