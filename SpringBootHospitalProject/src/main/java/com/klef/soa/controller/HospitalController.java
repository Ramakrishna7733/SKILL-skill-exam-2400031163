package com.klef.soa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Hospital;
import com.klef.soa.service.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
	
	@Autowired
 	HospitalService service;
	
	
    @GetMapping("/home")
    public String home() {
        return "Spring Boot Project";
    }
    //Add hospital
    @PostMapping("/add")
    public ResponseEntity<Hospital>addHospital(@RequestBody Hospital h)
    {
    	Hospital hospital =service.addHospital(h);
    	return ResponseEntity.status(201).body(hospital);
    }
    //Display All Hospitals
    @GetMapping("/displayall")
    public ResponseEntity<List<Hospital>>displayAllHospitals()
    {
    	List<Hospital>hospitals=service.displayAllHospitals();
    	return ResponseEntity.status(200).body(hospitals);
    }
    //Display hospital by ID
    @GetMapping("/displaybyid")
    public ResponseEntity<?>displayHospitalById(@RequestParam Long id)
    {
    	Hospital hospital=service.displayHospitalById(id);
         if(hospital!=null)
         {
        	 return ResponseEntity.status(200).body(hospital);
         }
         else
         {
        	 return ResponseEntity.status(404).body("Hospital Id not Found");
         }
    }
      // Display Hospitals By Name
         @GetMapping("/displaybyname/{name}")
         public ResponseEntity<List<Hospital>> displayHospitalsByName(@PathVariable String name)
         {
             List<Hospital> hospitals = service.displayHospitalsByName(name);
             return ResponseEntity.ok(hospitals);
         }

         // Display Hospitals By Type
         @GetMapping("/displaybytype/{type}")
         public ResponseEntity<List<Hospital>> displayHospitalsByType(@PathVariable String type)
         {
             List<Hospital> hospitals = service.displayHospitalsByType(type);
             return ResponseEntity.ok(hospitals);
         }
    
    //update hospital
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHospital(@PathVariable Long id, @RequestBody Hospital hospital)
    {
        hospital.setHospitalId(id);

        Hospital h = service.updateHospital(hospital);

        if (h != null)
        {
            return ResponseEntity.ok(h);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital ID Not Found");
        }
    }
    //delete Hospital By Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHospitalById(@PathVariable Long id)
    {
        String message = service.deleteHospitalById(id);

        if(message.equals("Hospital Deleted Successfully"))
        {
            return ResponseEntity.ok(message);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
