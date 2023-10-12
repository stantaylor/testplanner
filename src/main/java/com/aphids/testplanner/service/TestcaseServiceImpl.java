package com.aphids.testplanner.service;

import com.aphids.testplanner.exception.ObjectNotFoundException;
import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestcaseServiceImpl implements TestcaseService {

    @Autowired
    TestcaseRepository testcaseRepository;

    @Override
    public List<Testcase> getAllTestcases() {
        return testcaseRepository.findAll();
    }

    @Override
    public Testcase getTestcase(Long testcaseId) {

        Optional<Testcase> testcase = testcaseRepository.findById(testcaseId);
        if (testcase.isPresent()) {
            return testcaseRepository.findById(testcaseId).get();
        }
        throw new ObjectNotFoundException("Testcase not found: " + testcaseId);
    }

    @Override
    public Testcase createTestcase(Testcase testcase) {
        return testcaseRepository.save(testcase);
    }

    @Override
    public Testcase updateTestcase(Testcase testcase) {

        Optional<Testcase> testcaseOptional = testcaseRepository.findById(testcase.getId());
        if (testcaseOptional.isPresent()) {
            return testcaseRepository.saveAndFlush(testcase);
        }
        throw new ObjectNotFoundException("Testcase not found: " + testcase.getId());
    }

    @Override
    public void deleteTestcase(Long testcaseId) {
        Optional<Testcase> testcaseOptional = testcaseRepository.findById(testcaseId);
        if (testcaseOptional.isPresent()) {
            testcaseRepository.deleteById(testcaseId);
        } else {
            throw new ObjectNotFoundException("Testcase not found: " + testcaseId);
        }
    }

}
