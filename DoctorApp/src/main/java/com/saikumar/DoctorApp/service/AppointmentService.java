package com.saikumar.DoctorApp.service;

import com.saikumar.DoctorApp.model.Appointment;
import com.saikumar.DoctorApp.model.Patient;
import com.saikumar.DoctorApp.repository.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    IAppointmentRepo appointmentRepo;

    public String createAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
        return "appoinment created!!";
    }

    public List<Appointment> getAllAppointments() {
      return   appointmentRepo.findAll();
    }

    public void scheduleAppointment(Appointment appointment) {
        appointment.setAppointmentCreationTime(LocalDateTime.now());
        appointmentRepo.save(appointment);
    }

    public Appointment getAppointmentOfPatient(Patient patient) {
     return   appointmentRepo.findFirstByPatient(patient);
    }
    public void cancelAppointment(Appointment appointment) {
        appointmentRepo.delete(appointment);
    }
}
