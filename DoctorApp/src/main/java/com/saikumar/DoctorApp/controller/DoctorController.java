package com.saikumar.DoctorApp.controller;

import com.saikumar.DoctorApp.model.Doctor;
import com.saikumar.DoctorApp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("doctor")
    public String createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();

    }
}
