package com.saikumar.DoctorApp.repository;

import com.saikumar.DoctorApp.model.Admin;
import com.saikumar.DoctorApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends JpaRepository<Patient,Long> {
   Patient  findFirstByPatientEmail(String newEmail);
}
