package com.brunothecoder.clinicapi.integration.controllers;

import com.brunothecoder.clinicapi.api.dto.patient.CreatePatientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sql/create-table-patients.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = {"/sql/insert-patients.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldListAllPatients() throws Exception {

        mockMvc.perform(get("/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Maria"))
                .andExpect(jsonPath("$[1].name").value("João"));
    }

    @Test
    void shouldCreatePatientSuccessfully() throws Exception {

        CreatePatientRequest request = new CreatePatientRequest("Alice", "12345678000");

        mockMvc.perform(post("/v1/patients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.cpf").value("12345678000"))
                .andExpect(jsonPath("$.active").value(true));
    }
}
