package com.aphids.testplanner.service;

import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return testcaseRepository.findById(testcaseId).get();
    }

    @Override
    public Testcase createTestcase(Testcase testcase) {
        return testcaseRepository.save(testcase);
    }

    @Override
    public Testcase updateTestcase(Testcase testcase) {
        return testcaseRepository.saveAndFlush(testcase);
    }

    @Override
    public void deleteTestcase(Long testcaseId) {

        testcaseRepository.deleteById(testcaseId);

    }


}
