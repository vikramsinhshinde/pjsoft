package com.example.feesManagementSystem.Service;

import com.example.feesManagementSystem.Entity.Standered;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StanderedService {
    Standered saveStandered(Standered classStandered);
    List<Standered> getAllStandered();
   Standered getStanderedById(Long id);
    Standered updateStandered(Long id, Standered classStanderedDetails);
    void deleteStandered(Long id);

    Standered findByStandered(@Param("standered") String standered);
}
