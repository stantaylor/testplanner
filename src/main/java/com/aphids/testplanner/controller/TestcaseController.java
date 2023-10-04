package com.aphids.testplanner.controller;

import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("testplanner/v1/testcase/")
public class TestcaseController {

    @Autowired
    TestcaseService testcaseService;

    @GetMapping("testplanner/v1/testcases/")
    public List<Testcase> getAllTestcases() {
        return testcaseService.getAllTestcases();
    }

    @GetMapping("testplanner/v1/testcases/{id}")
    public Testcase getTestcase(@PathVariable String id){
        return testcaseService.getTestcase(Long.parseLong(id));
    }

    @PostMapping("testplanner/v1/testcases/")
    public Testcase createTestcase(@RequestBody Testcase testcase) {
        return testcaseService.createTestcase(testcase);
    }

    @PutMapping("testplanner/v1/testcases/{id}")
    public Testcase updateTestcase(@RequestBody Testcase testcase, @PathVariable String id){
        return testcaseService.updateTestcase(testcase);
    }

    @DeleteMapping("testplanner/v1/testcases/{id}")
    public void deleteTestcase(@PathVariable String id) {
        testcaseService.deleteTestcase(Long.parseLong(id));
    }

}
