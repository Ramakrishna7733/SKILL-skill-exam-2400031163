package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import com.klef.soa.entity.Hospital;
import com.klef.soa.repository.HospitalRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HospitalRepositoryTest {

    @Autowired
    private HospitalRepository repository;

    // Test 1: Find all hospitals
    @Test
    void testFindAllHospitals() {
        List<Hospital> hospitals = repository.findAll();

        assertNotNull(hospitals);
        assertFalse(hospitals.isEmpty());

        System.out.println("Total Hospitals: " + hospitals.size());
    }

    // Test 2: Find hospital by ID
    @Test
    void testFindHospitalById() {
        List<Hospital> hospitals = repository.findAll();

        assertFalse(hospitals.isEmpty());

        Long id = hospitals.get(0).getHospitalId();
        Optional<Hospital> hospital = repository.findById(id);

        assertTrue(hospital.isPresent());
        assertEquals(id, hospital.get().getHospitalId());

        System.out.println("Hospital ID: " + hospital.get().getHospitalId());
        System.out.println("Hospital Name: " + hospital.get().getName());
    }

    // Test 3: Find hospital by name
    @Test
    void testFindHospitalByName() {
        Hospital hospital = new Hospital();
        hospital.setName("Test Hospital");
        hospital.setType("Multispeciality");
        hospital.setStatus(true);
        hospital.setLocation("Guntur");

        repository.save(hospital);

        List<Hospital> result = repository.findByName("Test Hospital");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Hospital", result.get(0).getName());

        System.out.println("Hospital Name: " + result.get(0).getName());
    }

    // Test 4: Count hospitals
    @Test
    void testTotalHospitalCount() {
        long count = repository.count();

        assertTrue(count >= 0);

        System.out.println("Total Hospitals in Database: " + count);
    }

    // Test 5: Verify hospital properties
    @Test
    void testHospitalDetails() {
        Hospital hospital = new Hospital();
        hospital.setName("Medicover");
        hospital.setType("Multispeciality");
        hospital.setStatus(true);
        hospital.setLocation("Guntur");

        Hospital saved = repository.save(hospital);

        assertNotNull(saved.getHospitalId());
        assertNotNull(saved.getName());
        assertNotNull(saved.getType());
        assertNotNull(saved.getLocation());

        System.out.println("Hospital ID: " + saved.getHospitalId());
        System.out.println("Name: " + saved.getName());
        System.out.println("Type: " + saved.getType());
        System.out.println("Location: " + saved.getLocation());
    }
}