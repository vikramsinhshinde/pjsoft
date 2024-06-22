package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Module.EnquiryAdminEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



public interface EnquiryAdminService {

    List<EnquiryAdminEntity> getAllEnquiries();

    Optional<EnquiryAdminEntity> getEnquiryById(Long id);

    EnquiryAdminEntity createEnquiry(EnquiryAdminEntity enquiry);

    EnquiryAdminEntity updateEnquiry(Long id, EnquiryAdminEntity enquiryDetails);

    void deleteEnquiry(Long id);
}
