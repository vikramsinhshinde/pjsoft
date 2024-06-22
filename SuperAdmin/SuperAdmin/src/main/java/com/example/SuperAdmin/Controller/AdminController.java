package com.example.SuperAdmin.Controller;



import com.example.SuperAdmin.Module.Admin;
import com.example.SuperAdmin.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@CrossOrigin(origins = "http://pjsofttech.in/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/saveAdmin")
    @ResponseBody
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @GetMapping("/getAllAdmins")
    @ResponseBody
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/getAdminById/{id}")
    @ResponseBody
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.getAdmin(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateAdmin/{id}")
    @ResponseBody
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.updateAdmin(admin, id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/patchAdmin/{id}")
    @ResponseBody
    public ResponseEntity<Admin> patchAdmin(@RequestBody Admin admin, @PathVariable Long id) {
        Admin updatedAdmin = adminService.patchAdmin(admin, id);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAdmin/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

}

