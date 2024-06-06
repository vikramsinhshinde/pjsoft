package com.example.feesManagementSystem.Repository;

import com.example.feesManagementSystem.Entity.Standered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StanderedRepository extends JpaRepository<Standered, Long> {

    @Query("SELECT s FROM Standered s WHERE s.standard = :standard")
    Standered findByStandard(@Param("standard") String standard);
}