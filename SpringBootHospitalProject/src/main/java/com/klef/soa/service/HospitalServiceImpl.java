package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Hospital;
import com.klef.soa.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService
{
    @Autowired
    HospitalRepository repo;

    @Override
    public Hospital addHospital(Hospital h)
    {
        return repo.save(h);
    }

    @Override
    public Hospital updateHospital(Hospital h)
    {
        Optional<Hospital> optional = repo.findById(h.getHospitalId());

        if(optional.isPresent())
        {
            Hospital hospital = optional.get();

            hospital.setName(h.getName());
            hospital.setType(h.getType());
            hospital.setStatus(h.getStatus());
            hospital.setLocation(h.getLocation());

            return repo.save(hospital);
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Hospital> displayAllHospitals()
    {
        return repo.findAll();
    }

    @Override
    public Hospital displayHospitalById(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    @Override
    public String deleteHospitalById(Long id)
    {
        if(repo.existsById(id))
        {
            repo.deleteById(id);
            return "Hospital Deleted Successfully";
        }
        else
        {
            return "Hospital ID Not Found";
        }
    }
    @Override
    public List<Hospital> displayHospitalsByName(String name)
    {
        return repo.findByName(name);
    }

    @Override
    public List<Hospital> displayHospitalsByType(String type)
    {
        return repo.findByType(type);
    }
}