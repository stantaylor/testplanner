package com.aphids.testplanner.controller;

import com.aphids.testplanner.exception.ObjectNotFoundException;
import com.aphids.testplanner.model.Testcase;
import com.aphids.testplanner.service.TestcaseService;
import com.aphids.testplanner.utilities.TestcaseUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestcaseController.class)
public class TestcaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestcaseService testcaseService;

    @Test
    public void whenRequestToGetAllTestcases_thenCorrectResponse() throws Exception {

        Testcase tc1 = TestcaseUtilities.generateRandomTestcaseWithId();
        Testcase tc2 = TestcaseUtilities.generateRandomTestcaseWithId();

        List<Testcase> testcases = new ArrayList<Testcase>();
        testcases.add(tc1);
        testcases.add(tc2);

        when(testcaseService.getAllTestcases()).thenReturn(testcases);

        mockMvc.perform(get("/testplanner/v1/testcases/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(tc1.getId()))
                .andExpect(jsonPath("$.[0].name").value(tc1.getName()))
                .andExpect(jsonPath("$.[1].id").value(tc2.getId()))
                .andExpect(jsonPath("$.[1].name").value(tc2.getName()));

    }

    @Test
    public void whenRequestToGetTestcase_thenCorrectResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        when(testcaseService.getTestcase(testcase.getId())).thenReturn(testcase);

        mockMvc.perform(get("/testplanner/v1/testcases/" + testcase.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testcase.getId()))
                .andExpect(jsonPath("$.name").value(testcase.getName()));

    }

    @Test
    public void whenRequestToGetTestcase_thenTestcaseNotFoundResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        when(testcaseService.getTestcase(testcase.getId())).thenThrow(new ObjectNotFoundException("Testcase not found"));

        mockMvc.perform(get("/testplanner/v1/testcases/" + testcase.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }


    @Test
    public void whenPostRequestToCreateTestcase_thenCorrectResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        when(testcaseService.createTestcase(testcase)).thenReturn(testcase);

        mockMvc.perform(post("/testplanner/v1/testcases/")
                        .content(new ObjectMapper().writeValueAsString(testcase))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testcase.getId()))
                .andExpect(jsonPath("$.name").value(testcase.getName()));

    }

    @Test
    public void whenPutRequestToUpdateTestcase_thenCorrectResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        when(testcaseService.updateTestcase(testcase)).thenReturn(testcase);

        mockMvc.perform(put("/testplanner/v1/testcases/" + testcase.getId())
                        .content(new ObjectMapper().writeValueAsString(testcase))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testcase.getId()))
                .andExpect(jsonPath("$.name").value(testcase.getName()));

    }

    @Test
    public void whenPutRequestToUpdateTestcase_thenTestcaseNotFoundResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        when(testcaseService.updateTestcase(testcase)).thenThrow(new ObjectNotFoundException("Testcase not found"));

        mockMvc.perform(put("/testplanner/v1/testcases/" + testcase.getId())
                        .content(new ObjectMapper().writeValueAsString(testcase))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void whenDeleteRequestToDeleteTestcase_thenCorrectResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        doNothing().when(testcaseService).deleteTestcase(testcase.getId());

        mockMvc.perform(delete("/testplanner/v1/testcases/" + testcase.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    public void whenDeleteRequestToDeleteTestcase_thenTestcaseNotFoundResponse() throws Exception {

        Testcase testcase = TestcaseUtilities.generateRandomTestcaseWithId();
        doThrow(new ObjectNotFoundException("testcase not found")).when(testcaseService).deleteTestcase(testcase.getId());

        mockMvc.perform(delete("/testplanner/v1/testcases/" + testcase.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

}

