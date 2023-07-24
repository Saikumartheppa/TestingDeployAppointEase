package com.saikumar.DoctorApp.controller;

import com.saikumar.DoctorApp.dto.SignInInput;
import com.saikumar.DoctorApp.dto.SignUpOutput;
import com.saikumar.DoctorApp.model.Appointment;
import com.saikumar.DoctorApp.model.AuthenticationToken;
import com.saikumar.DoctorApp.model.Patient;
import com.saikumar.DoctorApp.service.AuthenticationTokenService;
import com.saikumar.DoctorApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @PostMapping("patient/signUp")
    public SignUpOutput signUpPatient(@RequestBody Patient patient){

        return  patientService.signUpPatient(patient);
    }
    @PostMapping("patient/signIn")
    public String  signInPatient(@RequestBody SignInInput signInInput){
        return patientService.signInPatient(signInInput);
    }
    @DeleteMapping("patient/signOut")
    public String signOutPatient(String email,String token){
       if(authenticationTokenService.authenticate(email,token))
          return patientService.signOutPatient(email);
       return "Sign out not allowed for non authenticated user.";
    }
    @PostMapping("patient")
    public String createPatient(@RequestBody Patient patient){
        return  patientService.createPatient(patient);
    }
    @GetMapping("patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }
    @PostMapping("appointment/schedule")
    public String scheduleAppointment(@RequestBody Appointment appointment,String email,String token){
        if(authenticationTokenService.authenticate(email,token)){
            boolean status = patientService.scheduleAppointment(appointment);
            return status ? "Appointment Scheduled" : "error occurred during scheduling appointment";
        }
        return "Scheduling of appointment failed because Invalid appointment !!";
    }
    @DeleteMapping("appointment/schedule")
    public String cancelAppointment(String email,String token) {
        if (authenticationTokenService.authenticate(email, token)) {
            patientService.cancelAppointment(email);
            return "Appointment Cancelled";
        }
        return "error occurred during cancelling appointment";
    }
}
