package com.example.feesManagementSystem.Repository;

import com.example.feesManagementSystem.Entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Long> {
}
