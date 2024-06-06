package com.example.feesManagementSystem.Service;

import com.example.feesManagementSystem.Entity.Fees;

import java.util.List;

public interface FeesService {
    Fees saveFees(Fees fees);

    Fees getFeesById(Long id);

    List<Fees> getAllFees();

    Fees updateFees(Fees fees, Long id);

    void deleteFees(Long id);
}
