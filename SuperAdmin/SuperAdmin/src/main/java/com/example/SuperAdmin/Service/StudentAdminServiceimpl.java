package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.Module.StudentAdmin;
import com.example.SuperAdmin.Repository.StudentAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentAdminServiceimpl implements StudentAdminService{

    @Autowired
    StudentAdminRepository StudentAdminEntityRepository;



    @Override
    public StudentAdmin saveStudentAdmin(StudentAdmin studentAdmin) {
        return StudentAdminEntityRepository.save(studentAdmin);
    }

    @Override
    public List<StudentAdmin> getAllStudentAdmin() {
        return (List<StudentAdmin>) StudentAdminEntityRepository.findAll();
    }

    @Override
    public StudentAdmin getStudentAdmin(Long id) {
        return StudentAdminEntityRepository.findById(id).get();
    }

    @Override
    public StudentAdmin updateStudentAdmin(StudentAdmin StudentAdmin, Long id) {
        Optional<StudentAdmin> optionalStudentAdmin = StudentAdminEntityRepository.findById(id);
        if (optionalStudentAdmin.isPresent()) {
            StudentAdmin StudentAdminToUpdate = optionalStudentAdmin.get();
            StudentAdminToUpdate.setAdminname(StudentAdmin.getAdminname());
            StudentAdminToUpdate.setEmailaddress(StudentAdmin.getEmailaddress());
            StudentAdminToUpdate.setPhonenumber(StudentAdmin.getPhonenumber());
            return StudentAdminEntityRepository.save(StudentAdminToUpdate);
        } else {
            throw new NoSuchElementException("StudentAdmin not found with id: " + id);
        }
    }

    @Override
    public StudentAdmin patchStudentAdmin(StudentAdmin StudentAdmin, Long id) {
        Optional<StudentAdmin> existingStudentAdmin = StudentAdminEntityRepository.findById(id);
        if (existingStudentAdmin.isPresent()) {
            StudentAdmin updatedStudentAdmin = existingStudentAdmin.get();
            if (StudentAdmin.getAdminname() != null) {
                updatedStudentAdmin.setAdminname(StudentAdmin.getAdminname());
            }
            if (StudentAdmin.getEmailaddress() != null) {
                updatedStudentAdmin.setEmailaddress(StudentAdmin.getEmailaddress());
            }
            if (StudentAdmin.getPhonenumber() != null) {
                updatedStudentAdmin.setPhonenumber(StudentAdmin.getPhonenumber());
            }
            if (StudentAdmin.getPassword() != null) {
                updatedStudentAdmin.setPassword(StudentAdmin.getPassword());
            }
            return StudentAdminEntityRepository.save(updatedStudentAdmin);
        }
        return null;
    }

    @Override
    public void deleteStudentAdmin(Long id) {
        StudentAdminEntityRepository.deleteById(id);
    }

    @Override
    public StudentAdmin updateStudentAdminPassword(String email, String password, String confirmPassword){
        StudentAdmin StudentAdmin=StudentAdminEntityRepository.findByEmailaddress(email);
        StudentAdmin.setPassword(password);
        StudentAdmin.setConfirmpassword(confirmPassword);
        StudentAdminEntityRepository.save(StudentAdmin);
        return StudentAdmin;
    }

}
