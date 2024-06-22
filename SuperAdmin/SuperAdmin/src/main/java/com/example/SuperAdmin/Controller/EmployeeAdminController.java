package com.example.SuperAdmin.Controller;


import com.example.SuperAdmin.Module.EmployeeAdmin;
import com.example.SuperAdmin.Service.EmployeeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://pjsofttech.in/")
public class EmployeeAdminController {

    @Autowired
    private EmployeeAdminService employeeAdminService;

    // Create a new EmployeeAdmin
    @PostMapping("/saveEmployeeAdmin")
    @ResponseBody
    public ResponseEntity<EmployeeAdmin> createEmployeeAdmin(@RequestBody EmployeeAdmin employeeAdmin) {
        EmployeeAdmin createdEmployeeAdmin = employeeAdminService.saveEmployeeAdmin(employeeAdmin);
        return ResponseEntity.ok(createdEmployeeAdmin);
    }

    // Get all EmployeeAdmins
    @GetMapping("/getEmployeeAllAdmin")
    @ResponseBody
    public ResponseEntity<List<EmployeeAdmin>> getAllEmployeeAdmins() {
        List<EmployeeAdmin> employeeAdmins = employeeAdminService.getAllEmployeeAdmin();
        return ResponseEntity.ok(employeeAdmins);
    }

    // Get a single EmployeeAdmin by ID
    @GetMapping("/getEmployeeById/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeAdmin> getEmployeeAdminById(@PathVariable Long id) {
        EmployeeAdmin employeeAdmin = employeeAdminService.getEmployeeAdminById(id);
        return ResponseEntity.ok(employeeAdmin);
    }

    // Update an existing EmployeeAdmin
    @PutMapping("/updateEmployee/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeAdmin> updateEmployeeAdmin(@RequestBody EmployeeAdmin employeeAdmin, @PathVariable Long id) {
        EmployeeAdmin updatedEmployeeAdmin = employeeAdminService.updateEmployeeAdmin(employeeAdmin, id);
        return ResponseEntity.ok(updatedEmployeeAdmin);
    }

    // Delete an EmployeeAdmin by ID
    @DeleteMapping("/deleteEmployee/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployeeAdminById(@PathVariable Long id) {
        employeeAdminService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}

