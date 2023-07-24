package com.saikumar.DoctorApp.repository;

import com.saikumar.DoctorApp.model.Appointment;
import com.saikumar.DoctorApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {

    Appointment findFirstByPatient(Patient patient);
}
