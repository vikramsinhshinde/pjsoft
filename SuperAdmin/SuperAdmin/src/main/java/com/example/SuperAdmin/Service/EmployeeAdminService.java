package com.example.SuperAdmin.Service;



import com.example.SuperAdmin.Module.EmployeeAdmin;

import java.util.List;
import java.util.Optional;

public interface EmployeeAdminService {

    EmployeeAdmin saveEmployeeAdmin(EmployeeAdmin employeeAdmin);

    List<EmployeeAdmin> getAllEmployeeAdmin();

    EmployeeAdmin getEmployeeAdminById(Long id);

    EmployeeAdmin updateEmployeeAdmin(EmployeeAdmin employeeAdmin, Long id);

    void deleteEmployeeById(Long id);

    Optional<EmployeeAdmin> findByEmailaddress(String emailaddress);

    EmployeeAdmin updateAdminPassword(String email, String password, String confirmPassword);

}
