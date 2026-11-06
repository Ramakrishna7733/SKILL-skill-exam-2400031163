package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Hospital;

@Repository
@Component
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
List<Hospital>findByName(String name);
List<Hospital>findByType(String type);
}
