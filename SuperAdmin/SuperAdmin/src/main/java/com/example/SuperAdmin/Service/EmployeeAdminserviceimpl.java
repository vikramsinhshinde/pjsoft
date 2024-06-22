package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.Module.EmployeeAdmin;
import com.example.SuperAdmin.Repository.EmployeeadminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAdminserviceimpl implements  EmployeeAdminService{

    @Autowired
    EmployeeadminRepository employeeadminRepository;


    @Override
    public EmployeeAdmin saveEmployeeAdmin(EmployeeAdmin employeeAdmin) {
        return employeeadminRepository.save(employeeAdmin);
    }

    @Override
    public List<EmployeeAdmin> getAllEmployeeAdmin() {
        return employeeadminRepository.findAll();
    }

    @Override
    public EmployeeAdmin getEmployeeAdminById(Long id) {
        return employeeadminRepository.findById(id).get();
    }

    @Override
    public EmployeeAdmin updateEmployeeAdmin(EmployeeAdmin employeeAdmin, Long id) {
        EmployeeAdmin employeeAdmin1=employeeadminRepository.findById(id).get();
        employeeAdmin1.setAdminname(employeeAdmin.getAdminname());
        employeeAdmin1.setEmailaddress(employeeAdmin.getEmailaddress());
        employeeAdmin1.setPhonenumber(employeeAdmin.getPhonenumber());
        return employeeadminRepository.save(employeeAdmin1);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeadminRepository.deleteById(id);
    }

    @Override
    public Optional<EmployeeAdmin> findByEmailaddress(String emailaddress) {
        return Optional.ofNullable(employeeadminRepository.findByEmailaddress(emailaddress));
    }

    @Override
    public EmployeeAdmin updateAdminPassword(String email, String password, String confirmPassword) {
        EmployeeAdmin employeeAdmin=employeeadminRepository.findByEmailaddress(email);
        employeeAdmin.setPassword(password);
        employeeAdmin.setConfirmpassword(confirmPassword);
        return employeeAdmin;
    }
}
