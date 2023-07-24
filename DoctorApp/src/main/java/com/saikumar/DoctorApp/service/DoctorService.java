package com.saikumar.DoctorApp.service;

import com.saikumar.DoctorApp.model.Doctor;
import com.saikumar.DoctorApp.repository.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorRepo doctorRepo;

    public String createDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return "doctor created!!!";
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }
}
