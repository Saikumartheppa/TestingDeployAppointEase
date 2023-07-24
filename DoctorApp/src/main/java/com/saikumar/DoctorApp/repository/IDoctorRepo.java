package com.saikumar.DoctorApp.repository;

import com.saikumar.DoctorApp.model.Admin;
import com.saikumar.DoctorApp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
}
