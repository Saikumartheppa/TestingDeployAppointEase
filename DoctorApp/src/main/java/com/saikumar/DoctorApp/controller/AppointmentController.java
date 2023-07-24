package com.saikumar.DoctorApp.controller;

import com.saikumar.DoctorApp.model.Appointment;
import com.saikumar.DoctorApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("appointments")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }
}
