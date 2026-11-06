package com.klef.soa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.klef.soa.entity.Hospital;
@Component
public interface HospitalService
{
    Hospital addHospital(Hospital h);

    Hospital updateHospital(Hospital h);

    List<Hospital> displayAllHospitals();

    Hospital displayHospitalById(Long id);

    String deleteHospitalById(Long id);

    List<Hospital> displayHospitalsByName(String name);

    List<Hospital> displayHospitalsByType(String type);
}