package com.example.SuperAdmin.Service;


import com.example.SuperAdmin.Module.StudentAdmin;

import java.util.List;

public interface StudentAdminService {
    StudentAdmin saveStudentAdmin(StudentAdmin StudentAdmin);
    List<StudentAdmin> getAllStudentAdmin();
    StudentAdmin getStudentAdmin(Long id);
    StudentAdmin updateStudentAdmin(StudentAdmin StudentAdmin, Long id);
    StudentAdmin patchStudentAdmin(StudentAdmin StudentAdmin, Long id);
    void deleteStudentAdmin(Long id);
    StudentAdmin updateStudentAdminPassword(String email, String password, String confirmPassword);
}