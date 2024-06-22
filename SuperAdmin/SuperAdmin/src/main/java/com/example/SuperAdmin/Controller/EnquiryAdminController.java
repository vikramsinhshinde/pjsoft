package com.example.SuperAdmin.Controller;


import com.example.SuperAdmin.Module.EnquiryAdminEntity;
import com.example.SuperAdmin.Service.EnquiryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://pjsofttech.in/")
public class EnquiryAdminController {

    @Autowired
    EnquiryAdminService enquiryAdminService;

    @GetMapping("/get/enquiries")
    public List<EnquiryAdminEntity> getAllEnquiries() {
        return enquiryAdminService.getAllEnquiries();
    }

    @GetMapping("/getById/enquiries/{id}")
    public ResponseEntity<EnquiryAdminEntity> getEnquiryById(@PathVariable Long id) {
        Optional<EnquiryAdminEntity> enquiry = enquiryAdminService.getEnquiryById(id);
        return enquiry.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save/enquiries")
    public EnquiryAdminEntity createEnquiry(@RequestBody EnquiryAdminEntity enquiry) {
        return enquiryAdminService.createEnquiry(enquiry);
    }

    @PutMapping("/update/enquiries/{id}")
    public ResponseEntity<EnquiryAdminEntity> updateEnquiry(@PathVariable Long id, @RequestBody EnquiryAdminEntity enquiryDetails) {
        try {
            EnquiryAdminEntity updatedEnquiry = enquiryAdminService.updateEnquiry(id, enquiryDetails);
            return ResponseEntity.ok(updatedEnquiry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnquiry(@PathVariable Long id) {
        try {
            enquiryAdminService.deleteEnquiry(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
