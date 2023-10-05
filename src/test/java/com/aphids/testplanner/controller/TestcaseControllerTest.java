package com.aphids.testplanner.controller;

import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.repository.TestcaseRepository;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestcaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestcaseRepository testcaseRepository;
    @Test
    public void whenGetRequestToGetAllTestcases_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/testplanner/v1/testcases/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect((jsonPath("$").isNotEmpty()));
    }

    @Test
    public void whenPostRequestToCreateTestcase_thenCorrectResponse() throws Exception {

        String name = RandomStringUtils.randomAlphabetic(10);
        Testcase tc = new Testcase();
        tc.setName(name);
        Gson gson = new Gson();
        String requestBody = gson.toJson(tc);
        System.out.println(requestBody);

        mockMvc.perform(post("/testplanner/v1/testcases/")
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(name));

        verify(testcaseRepository, times(0).save(any(Testcase.class)));

    }
}
