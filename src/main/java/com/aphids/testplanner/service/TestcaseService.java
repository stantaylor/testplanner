package com.aphids.testplanner.service;

import com.aphids.testplanner.model.Testcase;

import java.util.List;

public interface TestcaseService {

    List<Testcase> getAllTestcases();

    Testcase getTestcase(Long testcaseId);

    Testcase createTestcase(Testcase testcase);

    Testcase updateTestcase(Testcase testcase);

    void deleteTestcase(Long testcaseId);
}
