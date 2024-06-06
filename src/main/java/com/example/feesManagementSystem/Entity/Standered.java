package com.example.feesManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Standered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="tuitionFee", nullable=false)
    private double tuitionFee;
    @Column(name="admissionFee", nullable=false)
    private double admissionFee;
    @Column(name="practicalFee", nullable=false)
    private double practicalFee;
    @Column(name="standard", nullable=false,unique = true)
    private String standard;
    @Column(name="computerClassFee", nullable=false)
    private double computerClassFee;
    @Column(name="examFees", nullable=false)
    private double examFees;
    @Column(name="uniformFee", nullable=false)
    private double uniformFee;
    @Column(name="transportBusFee", nullable=false)
    private double transportBusFee;
    @Column(name="hostelFee", nullable=false)
    private double hostelFee;
    @Column(name="buildingFundFee", nullable=false)
    private double buildingFundFee;
    @Column(name="libraryFees", nullable=false)
    private double libraryFees;
    @Column(name="sportFees", nullable=false)
    private double sportFees;
    @Column(name="GST", nullable=false)
    private double GST;
//    @Column(name="firstInstallment", nullable=false)
//    private double firstInstallment;
//    @Column(name="secondInstallment", nullable=false)
//    private double secondInstallment;
//    @Column(name="thirdInstallment", nullable=false)
//    private double thirdInstallment;
//    @Column(name="firstInstallment", nullable=false)
//    private double firstInstallmentPercentage;
//    @Column(name="secondInstallment", nullable=false)
//    private double secondInstallmentPercentage;
//    @Column(name="thirdInstallment", nullable=false)
//    private double thirdInstallmentPercentage;




}
