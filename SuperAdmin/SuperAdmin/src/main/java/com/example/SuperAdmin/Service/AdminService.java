package com.example.SuperAdmin.Service;



import com.example.SuperAdmin.Module.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdmin(Long id);
    Admin updateAdmin(Admin admin, Long id);
    Admin patchAdmin(Admin admin, Long id);
    void deleteAdmin(Long id);
    Admin updateAdminPassword(String email, String password, String confirmPassword);
}