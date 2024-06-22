package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Module.FeesManagementAdmin;
import com.example.SuperAdmin.Repository.FeesManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeesManagementAdminServiceimpl implements FeesManagementAdminService {

    @Autowired
    FeesManagementRepository feesManagementRepository;

    @Override
    public List<FeesManagementAdmin> findAll() {
        return (List<FeesManagementAdmin>) feesManagementRepository.findAll();
    }

    @Override
    public Optional<FeesManagementAdmin> findById(Long id) {
        return feesManagementRepository.findById(id);
    }

    @Override
    public FeesManagementAdmin save(FeesManagementAdmin admin) {
        return feesManagementRepository.save(admin);
    }

    @Override
    public FeesManagementAdmin update(Long id, FeesManagementAdmin adminDetails) {
        Optional<FeesManagementAdmin> optionalAdmin = feesManagementRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            FeesManagementAdmin admin = optionalAdmin.get();
            admin.setAdminname(adminDetails.getAdminname());
            admin.setEmailaddress(adminDetails.getEmailaddress());
            admin.setPhonenumber(adminDetails.getPhonenumber());
            admin.setPassword(adminDetails.getPassword());
            admin.setConfirmpassword(adminDetails.getConfirmpassword());
            return feesManagementRepository.save(admin);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        feesManagementRepository.deleteById(id);
    }

    @Override
    public FeesManagementAdmin findByEmailaddress(String emailaddress) {
        FeesManagementAdmin admin = feesManagementRepository.findByEmailaddress(emailaddress);
        return admin;
    }

    @Override
    public FeesManagementAdmin updateAdminPassword(String email, String password, String confirmPassword) {
        FeesManagementAdmin feesManagementAdmin=feesManagementRepository.findByEmailaddress(email);
        feesManagementAdmin.setPassword(password);
        feesManagementAdmin.setConfirmpassword(confirmPassword);
        return feesManagementAdmin;
    }
}
