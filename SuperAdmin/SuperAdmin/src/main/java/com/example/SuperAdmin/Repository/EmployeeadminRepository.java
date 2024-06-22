package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Module.EmployeeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeadminRepository extends JpaRepository<EmployeeAdmin, Long> {

    @Query("SELECT e FROM EmployeeAdmin e WHERE e.emailaddress = :emailaddress")
    EmployeeAdmin findByEmailaddress(@Param("emailaddress") String emailaddress);


}
