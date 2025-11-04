package com.brunothecoder.clinicapi.integration.repositories;

import com.brunothecoder.clinicapi.domain.repositories.DoctorRepository;
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
@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=none"
})
@Sql(scripts = "/sql/create-table-doctors.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/insert-doctors.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Test
    void shouldFindActiveDoctorInsertedViaSql() {
        UUID activeId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        boolean exists = repository.existsByIdAndActiveTrue(activeId);
        assertTrue(exists);
    }

    @Test
    void shouldNotFindInactiveDoctorAsActive() {
        UUID inactiveId = UUID.fromString("44444444-4444-4444-4444-444444444444");
        boolean exists = repository.existsByIdAndActiveTrue(inactiveId);
        assertFalse(exists);
    }
}
