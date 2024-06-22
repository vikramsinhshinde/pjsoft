package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.Module.*;
import com.example.SuperAdmin.Repository.AdminRepository;
import com.example.SuperAdmin.Repository.EmployeeadminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private EnquiryAdminService enquiryAdminService;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeAdminService employeeAdminService;

    @Autowired
    FeesManagementAdminService feesManagementAdminService;

    @Autowired
    private EmployeeadminRepository employeeadminRepository;

    @Autowired
    private StudentAdminService studentAdminService;



    @Override
    public Admin saveAdmin(Admin admin) {

        if( admin.isEmployeemanagementsystem()==true){
            EmployeeAdmin employeeAdmin = new EmployeeAdmin();
            LocalDateTime currentDateTime = LocalDateTime.now();
            employeeAdmin.setAdminname(admin.getAdminname());
            employeeAdmin.setEmailaddress(admin.getEmailaddress());
            employeeAdmin.setId(admin.getId());
            employeeAdmin.setPassword(admin.getPassword());
            employeeAdmin.setPhonenumber(admin.getPhonenumber());
            employeeAdmin.setStatus("Activate");
            employeeAdmin.setConfirmpassword(admin.getConfirmpassword());
            employeeAdmin.setCreatedAt(currentDateTime);
            employeeadminRepository.save(employeeAdmin);
        }

        if( admin.isFeesmanagementsystem()==true){
            FeesManagementAdmin feesManagementAdmin = new FeesManagementAdmin();
            LocalDateTime currentDateTime = LocalDateTime.now();
            feesManagementAdmin.setAdminname(admin.getAdminname());
            feesManagementAdmin.setEmailaddress(admin.getEmailaddress());
            feesManagementAdmin.setId(admin.getId());
            feesManagementAdmin.setPassword(admin.getPassword());
            feesManagementAdmin.setPhonenumber(admin.getPhonenumber());
            feesManagementAdmin.setConfirmpassword(admin.getConfirmpassword());
            feesManagementAdmin.setStatus("Activate");
            feesManagementAdmin.setCreatedAt(currentDateTime);
            feesManagementAdminService.save(feesManagementAdmin);
        }

        if( admin.isStudentmanagementsystem()==true){
            StudentAdmin studentAdmin = new StudentAdmin();
            LocalDateTime currentDateTime = LocalDateTime.now();
            studentAdmin.setAdminname(admin.getAdminname());
            studentAdmin.setEmailaddress(admin.getEmailaddress());
            studentAdmin.setId(admin.getId());
            studentAdmin.setPassword(admin.getPassword());
            studentAdmin.setPhonenumber(admin.getPhonenumber());
            studentAdmin.setConfirmpassword(admin.getConfirmpassword());
            studentAdmin.setCreatedAt(currentDateTime);
            studentAdmin.setStatus("Activate");
            studentAdminService.saveStudentAdmin(studentAdmin);
        }

        if( admin.isEnquirymanagementsystem()==true){
            EnquiryAdminEntity enquiryAdminEntity = new EnquiryAdminEntity();
            LocalDateTime currentDateTime = LocalDateTime.now();
            enquiryAdminEntity.setAdminname(admin.getAdminname());
            enquiryAdminEntity.setEmailaddress(admin.getEmailaddress());
            enquiryAdminEntity.setId(admin.getId());
            enquiryAdminEntity.setPassword(admin.getPassword());
            enquiryAdminEntity.setPhonenumber(admin.getPhonenumber());
            enquiryAdminEntity.setConfirmpassword(admin.getConfirmpassword());
            enquiryAdminEntity.setCreatedAt(currentDateTime);
            enquiryAdminEntity.setStatus("Activate");
            enquiryAdminService.createEnquiry(enquiryAdminEntity);
        }




        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Admin not found with id: " + id));
    }

    @Override
    public Admin updateAdmin(Admin admin, Long id) {
        Admin adminToUpdate = adminRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Admin not found with id: " + id));
        adminToUpdate.setAdminname(admin.getAdminname());
        adminToUpdate.setEmailaddress(admin.getEmailaddress());
        adminToUpdate.setPhonenumber(admin.getPhonenumber());
        return adminRepository.save(adminToUpdate);
    }

    @Override
    public Admin patchAdmin(Admin admin, Long id) {
        Optional<Admin> existingAdmin = adminRepository.findById(id);
        if (existingAdmin.isPresent()) {
            Admin updatedAdmin = existingAdmin.get();
            if (admin.getAdminname() != null) {
                updatedAdmin.setAdminname(admin.getAdminname());
            }
            if (admin.getEmailaddress() != null) {
                updatedAdmin.setEmailaddress(admin.getEmailaddress());
            }
            if (admin.getPhonenumber() != null) {
                updatedAdmin.setPhonenumber(admin.getPhonenumber());
            }
            if (admin.getPassword() != null) {
                updatedAdmin.setPassword(admin.getPassword());
            }
            if (admin.getConfirmpassword() != null) {
                updatedAdmin.setConfirmpassword(admin.getConfirmpassword());
            }
            return adminRepository.save(updatedAdmin);
        }
        return null;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdminPassword(String email, String password, String confirmPassword) {
        Admin admin = adminRepository.findByEmailaddress(email);
        if (admin != null) {
            admin.setPassword(password);
            admin.setConfirmpassword(confirmPassword);
            return adminRepository.save(admin);
        } else {
            throw new NoSuchElementException("Admin not found with email: " + email);
        }
    }
}
