package com.brunothecoder.clinicapi.integration.controllers;

import com.brunothecoder.clinicapi.api.dto.doctor.CreateDoctorRequest;
import com.brunothecoder.clinicapi.domain.entities.Specialty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sql/create-table-doctors.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class DoctorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = {"/sql/insert-doctors.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldListAllDoctors() throws Exception {

        mockMvc.perform(get("/v1/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    void shouldCreateDoctorSuccessfully() throws Exception {

        CreateDoctorRequest request = new CreateDoctorRequest(
                "Dr. Controller",
                Specialty.CARDIOLOGY,
                "15000"
        );

        mockMvc.perform(post("/v1/doctors").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name").value("Dr. Controller"))
                .andExpect(jsonPath("$.specialty").value("CARDIOLOGY"))
                .andExpect(jsonPath("$.crm").value("15000"))
                .andExpect(jsonPath("$.active").value(true));


    }


}
