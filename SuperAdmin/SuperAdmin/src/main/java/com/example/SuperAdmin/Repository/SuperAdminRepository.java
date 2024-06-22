package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Module.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {

    @Query("SELECT s FROM SuperAdmin s WHERE s.emailaddress = :emailaddress")
    SuperAdmin findByEmailaddress(@Param("emailaddress") String emailaddress);
}
