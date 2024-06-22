package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Module.EnquiryAdminEntity;
import com.example.SuperAdmin.Repository.EnquiryAdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service

public class EnquiryAdminServiceimpl implements EnquiryAdminService {

    @Autowired
    EnquiryAdminRepository enquiryAdminRepository;

    public List<EnquiryAdminEntity> getAllEnquiries() {
        return enquiryAdminRepository.findAll();
    }

    public Optional<EnquiryAdminEntity> getEnquiryById(Long id) {
        return enquiryAdminRepository.findById(id);
    }

    public EnquiryAdminEntity createEnquiry(EnquiryAdminEntity enquiry) {
        return enquiryAdminRepository.save(enquiry);
    }

    public EnquiryAdminEntity updateEnquiry(Long id, EnquiryAdminEntity enquiryDetails) {
        EnquiryAdminEntity enquiry = enquiryAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id " + id));

        enquiry.setAdminname(enquiryDetails.getAdminname());
        enquiry.setEmailaddress(enquiryDetails.getEmailaddress());
        enquiry.setPhonenumber(enquiryDetails.getPhonenumber());
        enquiry.setPassword(enquiryDetails.getPassword());
        enquiry.setConfirmpassword(enquiryDetails.getConfirmpassword());
        enquiry.setCreatedAt(enquiryDetails.getCreatedAt());
        enquiry.setStatus(enquiryDetails.getStatus());

        return enquiryAdminRepository.save(enquiry);
    }

    public void deleteEnquiry(Long id) {
        EnquiryAdminEntity enquiry = enquiryAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id " + id));
        enquiryAdminRepository.delete(enquiry);
    }
}
