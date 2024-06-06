package com.example.feesManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String studentName;
    private String rollNo;
    private String division;
    private String feesType;
    private double feesAmount;
    @Column(name="registrationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    private double discountedAmount;

    private double totalFeesAmount;

    private double pendingFeesAmount;
    private String feesStatus;
    private String feesCollectionType;
    private String transactionId;
    private String gstNo;

    private double discount;
    private double lateFeeCharges;

    private double tuitionFee;
    private double admissionFee;
    private double practicalFee;
    private String standard;
    private double computerClassFee;
    private double examFees;
    private double uniformFee;
    private double transportBusFee;
    private double hostelFee;
    private double buildingFundFee;
    private String medium;
    private double GST;
}
