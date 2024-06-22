package com.example.SuperAdmin.Service;



import com.example.SuperAdmin.Module.FeesManagementAdmin;

import java.util.List;
import java.util.Optional;

public interface FeesManagementAdminService {

    List<FeesManagementAdmin> findAll();

    Optional<FeesManagementAdmin> findById(Long id);

    FeesManagementAdmin save(FeesManagementAdmin admin);

    FeesManagementAdmin update(Long id, FeesManagementAdmin adminDetails);

    void deleteById(Long id);

    public FeesManagementAdmin findByEmailaddress(String emailaddress);

    public FeesManagementAdmin updateAdminPassword(String email, String password, String confirmPassword);
}
