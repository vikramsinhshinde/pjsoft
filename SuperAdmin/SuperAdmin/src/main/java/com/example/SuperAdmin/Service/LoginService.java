package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.Module.*;
import com.example.SuperAdmin.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    SuperAdminRepository superAdminRepository;
    @Autowired
    StudentAdminRepository studentAdminEntityRepository;


    @Autowired
    EmployeeadminRepository employeeadminRepository;

    @Autowired
    FeesManagementRepository feesManagementRepository;

    @Autowired
    EnquiryAdminRepository enquiryAdminRepository;


    public String adminLogin(String email, String password){
        Optional<StudentAdmin> adminOptional = Optional.ofNullable(studentAdminEntityRepository.findByEmailaddress(email));
        StudentAdmin studentAdmin=studentAdminEntityRepository.findByEmailaddress(email);
        LocalDateTime expiryDate = adminOptional.get().getCreatedAt().plusDays(365);
        if (adminOptional.isPresent()) {
            StudentAdmin admin = adminOptional.get();
            if (admin.getPassword().equals(password)) {
                if(LocalDateTime.now().isBefore(expiryDate)){
                    studentAdmin.setStatus("Activate");
                    return "yes";
                }else{
                    studentAdmin.setStatus("Deactivate");
                    return "no";
                }
            } else {
                return "no";
            }

        } else {
            return "Admin not found";
        }
    }

    public String employeeAdminLogin(String email, String password){
        Optional<EmployeeAdmin> employeeOptional = Optional.ofNullable(employeeadminRepository.findByEmailaddress(email));
        LocalDateTime expiryDate = employeeOptional.get().getCreatedAt().plusDays(365);
        if (employeeOptional.isPresent()) {
            EmployeeAdmin employeeAdmin = employeeOptional.get();
            if (employeeAdmin.getPassword().equals(password)) {
                if(LocalDateTime.now().isBefore(expiryDate)){
                    employeeAdmin.setStatus("Activate");
                    return "yes";
                }else{
                    employeeAdmin.setStatus("Deactivate");
                    return "no";
                }
            } else {
                return "no";
            }
        }
        else {
            return "Admin not found";
        }
    }

    public String enquiryAdminLogin(String email, String password){
        Optional<EnquiryAdminEntity> enquiryAdminEntity = Optional.ofNullable(enquiryAdminRepository.findByEmailaddress(email));
        LocalDateTime expiryDate = enquiryAdminEntity.get().getCreatedAt().plusDays(365);
        if (enquiryAdminEntity.isPresent()) {
            EnquiryAdminEntity enquiryAdminEntity1 = enquiryAdminEntity.get();
            if (enquiryAdminEntity1.getPassword().equals(password)) {
                if(LocalDateTime.now().isBefore(expiryDate)){
                    enquiryAdminEntity1.setStatus("Activate");
                    return "yes";
                }else{
                    enquiryAdminEntity1.setStatus("Deactivate");
                    return "no";
                }
            } else {
                return "no";
            }
        }
        else {
            return "Admin not found";
        }
    }

    public String feesAdminLogin(String email, String password){
        Optional<FeesManagementAdmin> feesManagementAdminOptional = Optional.ofNullable(feesManagementRepository.findByEmailaddress(email));
        LocalDateTime expiryDate = feesManagementAdminOptional.get().getCreatedAt().plusDays(365);
        if (feesManagementAdminOptional.isPresent()) {
            FeesManagementAdmin feesManagementAdmin = feesManagementAdminOptional.get();
            if (feesManagementAdmin.getPassword().equals(password)) {
                if(LocalDateTime.now().isBefore(expiryDate)){
                    feesManagementAdmin.setStatus("Activate");
                    return "yes";
                }else{
                    feesManagementAdmin.setStatus("Deactivate");
                    return "no";
                }
            } else {
                return "no";
            }
        } else {
            return "Admin not found";
        }
    }

    public String superAadminLogin(String email, String password){
        Optional<SuperAdmin> superAdminOptional = Optional.ofNullable(superAdminRepository.findByEmailaddress(email));
        if (superAdminOptional.isPresent()) {
            SuperAdmin superAdmin = superAdminOptional.get();
            if (superAdmin.getPassword().equals(password)) {
                return "yes";
            } else {
                return "no";
            }

        } else {
            return "Admin not found";
        }
    }



}
