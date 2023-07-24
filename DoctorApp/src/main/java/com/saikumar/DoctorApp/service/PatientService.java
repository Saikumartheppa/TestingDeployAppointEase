package com.saikumar.DoctorApp.service;

import com.saikumar.DoctorApp.dto.SignInInput;
import com.saikumar.DoctorApp.dto.SignUpOutput;
import com.saikumar.DoctorApp.model.Appointment;
import com.saikumar.DoctorApp.model.AuthenticationToken;
import com.saikumar.DoctorApp.model.Doctor;
import com.saikumar.DoctorApp.model.Patient;
import com.saikumar.DoctorApp.repository.IAuthenticationTokenRepo;
import com.saikumar.DoctorApp.repository.IPatientRepo;
import com.saikumar.DoctorApp.service.emailUtility.EmailHandler;
import com.saikumar.DoctorApp.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepo patientRepo;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;
    public SignUpOutput signUpPatient(Patient patient) {
    boolean signUpStatus = true;
    String signUpStatusMessage = null;
    String signUpEmail = patient.getPatientEmail();
     // check if patient email is  null
        if(signUpEmail == null){
            signUpStatus = false;
            signUpStatusMessage = "patient E-mail should not be null !!!";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    //   check if patient email already exists ??
       Patient existingPatient = patientRepo.findFirstByPatientEmail(signUpEmail);
        if(existingPatient != null){
            signUpStatus = false;
            signUpStatusMessage = "patient Already exists!!!";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        // encrypt the password by hashing;
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(patient.getPatientPassword());
            patient.setPatientPassword(encryptedPassword);
            patientRepo.save(patient);
            return new SignUpOutput(signUpStatus,"Patient registered successfully");
        }catch(Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }
    public String createPatient(Patient patient) {
        patientRepo.save(patient);
        return "patient created!!!";
    }

    public List<Patient> getAllPatients() {
       return patientRepo.findAll();
    }


    public String signInPatient(SignInInput signInInput) {
     String signInInputMessage = null;
     String signInEmail = signInInput.getEmail();

     // check if the email is null
      if(signInEmail == null){

         signInInputMessage =   "patient E-mail should not be null !!!";
         return signInInputMessage;
     }
     // check if email  is already exists ????
        Patient existingPatient = patientRepo.findFirstByPatientEmail(signInEmail);
       // System.out.print(existingPatient);
     if(existingPatient == null){
         signInInputMessage =   "Email not yet Registered.Please signup and try again!!!";
         return signInInputMessage;
     }
     // match passwords
        // encrypt the password and compare with existing password
     try{
         String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());

         if(existingPatient.getPatientPassword().equals(encryptedPassword)){

             //session should be created since password matched and user id is valid
             AuthenticationToken authentication = new AuthenticationToken(existingPatient);
             authenticationTokenService.saveToken(authentication);
            // authenticationTokenRepo.save(authentication);

             // sending otp or token value to email
             EmailHandler.sendEmail(signInEmail, "testing email", authentication.getTokenValue());
             return "Token sent to your email";
         }else {
             signInInputMessage = "invalid credentials";
             return signInInputMessage;
         }
     }catch (Exception e){
         signInInputMessage = "Internal error occurred during sign in";
         return signInInputMessage;
     }
    }

    public String signOutPatient(String email) {
        Patient patient =  patientRepo.findFirstByPatientEmail(email);
        authenticationTokenService.signOutPatient(patient);
        return "Patient signed out successfully !!";
    }

    public boolean scheduleAppointment(Appointment appointment) {
        Patient patient = appointment.getPatient();
       boolean isPatientValid =  patientRepo.existsById(patient.getPatientId());
        Doctor doctor = appointment.getDoctor();
        boolean isDoctorValid = patientRepo.existsById(doctor.getDoctorId());
        if(isPatientValid && isDoctorValid){
            appointmentService.scheduleAppointment(appointment);
            return true;
        }
        return false;
    }

    public void cancelAppointment(String email) {
        Patient patient = patientRepo.findFirstByPatientEmail(email);
        Appointment appointment = appointmentService.getAppointmentOfPatient(patient);
        appointmentService.cancelAppointment(appointment);
    }
}
