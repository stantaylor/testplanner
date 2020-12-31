package com.aphids.testplanner.service;

import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.repository.TestcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TestcaseService {

    @Autowired
    TestcaseRepository testcaseRepository;

    public Testcase add(Testcase testcase) {

        return testcaseRepository.save(testcase);

    }

    public void delete(Testcase testcase) {

        testcaseRepository.delete(testcase);

    }

    public Testcase update(Testcase testcase) {

        return testcaseRepository.save(testcase);

    }

    public List<Testcase> findAll() {

        return testcaseRepository.findAll();

    }

    public Optional<Testcase> findById(Long id) {

        return testcaseRepository.findById(id);

    }

    public Testcase findByName(String name) {

        return testcaseRepository.findByName(name);

    }

}
