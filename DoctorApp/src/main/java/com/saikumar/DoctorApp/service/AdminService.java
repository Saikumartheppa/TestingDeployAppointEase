package com.saikumar.DoctorApp.service;

import com.saikumar.DoctorApp.repository.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;
}
