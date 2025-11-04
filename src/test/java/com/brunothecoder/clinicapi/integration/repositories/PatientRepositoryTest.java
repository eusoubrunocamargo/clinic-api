package com.brunothecoder.clinicapi.integration.repositories;


import com.brunothecoder.clinicapi.domain.repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = "spring.flyway.enabled=false")
@Sql(scripts = "/sql/create-table-patients.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/insert-patients.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository repository;

    @Test
    void shouldFindActivePatientInsertedViaSql() {
        UUID activeId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        boolean exists = repository.existsByIdAndActiveTrue(activeId);
        assertTrue(exists);
    }

    @Test
    void shouldNotFindInactivePatientAsActive() {
        UUID inactiveId = UUID.fromString("22222222-2222-2222-2222-222222222222");
        boolean exists = repository.existsByIdAndActiveTrue(inactiveId);
        assertFalse(exists);
    }
}
