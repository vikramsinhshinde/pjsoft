package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Module.StudentAdmin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAdminRepository extends CrudRepository<StudentAdmin, Long> {

    @Query("SELECT sa FROM StudentAdmin sa WHERE sa.emailaddress = :email")
    StudentAdmin findByEmailaddress(@Param("email") String emailaddress);
}
