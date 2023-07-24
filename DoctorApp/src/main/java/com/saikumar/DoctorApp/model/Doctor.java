package com.saikumar.DoctorApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.saikumar.DoctorApp.model.enums.Qualification;
import com.saikumar.DoctorApp.model.enums.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Doctor.class,property="doctorId")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization doctorspecialization;
    private String doctorContactNumber;
    @Enumerated(EnumType.STRING)
    private Qualification doctorQualification;
    @Min(value = 200)
    @Max(value = 2000)
    private Double doctorConsultantFee;

    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointments;
}
