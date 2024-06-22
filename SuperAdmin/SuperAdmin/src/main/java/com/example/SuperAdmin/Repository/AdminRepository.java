package com.example.SuperAdmin.Repository;


import com.example.SuperAdmin.Module.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.emailaddress = :email")
    Admin findByEmailaddress(@Param("email") String emailaddress);
}
