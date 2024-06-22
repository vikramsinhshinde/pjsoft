package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Module.FeesManagementAdmin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FeesManagementRepository extends CrudRepository<FeesManagementAdmin, Long > {

    @Query("SELECT f FROM FeesManagementAdmin f WHERE f.emailaddress = :emailaddress")
    FeesManagementAdmin findByEmailaddress(@Param("emailaddress") String emailaddress);

}