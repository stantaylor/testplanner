package com.aphids.testplanner.utilities;

import com.aphids.testplanner.model.Testcase;
import org.apache.commons.lang3.RandomStringUtils;

public class TestcaseUtilities {

    public static Testcase generateRandomTestcaseWithId() {
        Testcase testcase = new Testcase();
        testcase.setName(RandomStringUtils.randomAlphabetic(10));
        testcase.setId((long) Math.floor(Math.random() * (1000 - 10 + 1) + 10));
        System.out.println("Generating random test case:" + testcase.toString());

        return testcase;
    }
}
