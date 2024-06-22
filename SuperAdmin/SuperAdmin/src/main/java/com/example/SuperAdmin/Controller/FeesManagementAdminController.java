package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Module.FeesManagementAdmin;
import com.example.SuperAdmin.Service.FeesManagementAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://pjsofttech.in/")
public class FeesManagementAdminController {

    @Autowired
    FeesManagementAdminService feesManagementAdminService;

    @GetMapping("/getAllFeesAdmins")
    @ResponseBody
    public List<FeesManagementAdmin> getAllAdmins() {
        return feesManagementAdminService.findAll();
    }

    @GetMapping("/getFeesAdminByid/{id}")
    @ResponseBody
    public ResponseEntity<FeesManagementAdmin> getAdminById(@PathVariable Long id) {
        Optional<FeesManagementAdmin> admin = feesManagementAdminService.findById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveFeesAdmin")
    @ResponseBody
    public FeesManagementAdmin createAdmin(@RequestBody FeesManagementAdmin admin) {
        return feesManagementAdminService.save(admin);
    }

    @PutMapping("/updateFeesAdmin/{id}")
    @ResponseBody
    public ResponseEntity<FeesManagementAdmin> updateAdmin(@PathVariable Long id, @RequestBody FeesManagementAdmin adminDetails) {
        FeesManagementAdmin updatedAdmin = feesManagementAdminService.update(id, adminDetails);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteFeesAdmin/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        Optional<FeesManagementAdmin> admin = feesManagementAdminService.findById(id);
        if (admin.isPresent()) {
            feesManagementAdminService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
