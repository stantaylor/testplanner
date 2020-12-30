package com.aphids.testplanner.controller;

//@RestController
//@RequestMapping("/api")
//public class NoteController {
//
//    @Autowired
//    NoteRepository noteRepository;
//
//    // Get All Notes
//
//    // Create a new Note
//
//    // Get a Single Note
//
//    // Update a Note
//
//    // Delete a Note
//}

import com.aphids.testplanner.exception.ResourceNotFoundException;
import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestcaseController {

    @Autowired
    TestcaseRepository testcaseRepository;

    // Get all testcases
    @GetMapping("/testcases")
    public List<Testcase> getAllTestcases() {

        return testcaseRepository.findAll();

    }

    // Create a new testcase
    @PostMapping("/testcases")
    public Testcase createTestcase(@Valid @RequestBody Testcase testcase) {

        return testcaseRepository.save(testcase);

    }

    // Get a single testcase
    @GetMapping("/testcases/{id}")
    public Testcase getTestcaseById(@PathVariable(value = "id") Long testcaseId) {

        return testcaseRepository.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));

    }

    // Update a testcase
    @PutMapping("/testcases/{id}")
    public Testcase updateTestcase(@PathVariable(value = "id") Long testcaseId,
                           @Valid @RequestBody Testcase testcaseDetails) {

        Testcase testcase = testcaseRepository.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));
        testcase.setName(testcaseDetails.getName());
        testcase.setDescription(testcaseDetails.getDescription());
        Testcase updatedTestcase = testcaseRepository.save(testcase);
        return updatedTestcase;

    }

    // Delete a testcase
    @DeleteMapping("/testcases/{id}")
    public ResponseEntity<?> deleteTestcase(@PathVariable(value = "id") Long testcaseId) {

        Testcase testcase = testcaseRepository.findById(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Testcase", "id", testcaseId));
        testcaseRepository.delete(testcase);
        return ResponseEntity.ok().build();

    }

}
