package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Module.EnquiryAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnquiryAdminRepository extends JpaRepository<EnquiryAdminEntity, Long> {

    @Query("SELECT e FROM EnquiryAdminEntity e WHERE e.emailaddress = :emailaddress")
    EnquiryAdminEntity findByEmailaddress(@Param("emailaddress") String emailaddress);
}
