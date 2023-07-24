package com.saikumar.DoctorApp.service;

import com.saikumar.DoctorApp.model.AuthenticationToken;
import com.saikumar.DoctorApp.model.Patient;
import com.saikumar.DoctorApp.repository.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;

    public void saveToken(AuthenticationToken authentication) {
        authenticationTokenRepo.save(authentication);
    }

    public boolean authenticate(String email, String authTokenValue) {
        AuthenticationToken authenticationToken = authenticationTokenRepo.findFirstByTokenValue(authTokenValue);
        if(authenticationToken == null){
            return false;
        }
       return authenticationToken.getPatient().getPatientEmail().equals(email);
    }

    public void signOutPatient(Patient patient) {
        authenticationTokenRepo.delete(authenticationTokenRepo.findFirstByPatient(patient));
    }
}
