package com.example.feesManagementSystem.Service;

import com.example.feesManagementSystem.Entity.Fees;
import com.example.feesManagementSystem.Entity.Standered;
import com.example.feesManagementSystem.Repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeesServiceImpl implements FeesService{

@Autowired
    FeesRepository feesRepository;

@Autowired
StanderedService standeredService;



    @Override
    public Fees saveFees(Fees fees) {
        Long id = fees.getId();
        Standered standered = standeredService.findByStandered(fees.getStandard());
        double totalFees;
        if (standered == null) {
            return null;
        } else {
            fees.setStandard(standered.getStandard());
            totalFees = standered.getTuitionFee() + standered.getAdmissionFee() + standered.getComputerClassFee() + standered.getExamFees()
                    + standered.getUniformFee() + standered.getTransportBusFee() + standered.getHostelFee() + standered.getBuildingFundFee()
                    + standered.getPracticalFee()+fees.getLateFeeCharges()+standered.getLibraryFees()+standered.getSportFees()+fees.getLateFeeCharges();
            totalFees=totalFees*standered.getGST()/100;
            fees.setTotalFeesAmount(totalFees);
        }
        double discountedFeesAmount =   totalFees-(totalFees*fees.getDiscount()/100);
        fees.setDiscountedAmount(discountedFeesAmount);
        if (discountedFeesAmount>fees.getFeesAmount()  ) {

            fees.setFeesStatus("incomplete");
            fees.setPendingFeesAmount(discountedFeesAmount-fees.getFeesAmount());
        } else {
            fees.setFeesStatus("Complete");
        }
        fees.setUniformFee(standered.getUniformFee());
        fees.setTuitionFee(standered.getTuitionFee());
        fees.setHostelFee(standered.getHostelFee());
        fees.setAdmissionFee(standered.getAdmissionFee());
        fees.setExamFees(standered.getExamFees());
        fees.setTotalFeesAmount(totalFees);
        fees.setPracticalFee(standered.getPracticalFee());
        fees.setComputerClassFee(standered.getComputerClassFee());
        fees.setTransportBusFee(standered.getTransportBusFee());
        fees.setBuildingFundFee(standered.getBuildingFundFee());
        fees.setDiscountedAmount(discountedFeesAmount);
        fees.setGST(standered.getGST());
        return feesRepository.save(fees);
    }

    @Override
    public Fees getFeesById(Long id) {
        return feesRepository.findById(id).get();
    }

    @Override
    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public Fees updateFees(Fees fees, Long id) {
        Fees newFees=feesRepository.findById(id).get();
        Standered standered = standeredService.findByStandered(fees.getStandard());
        double totalFees;
        if (standered == null) {
            return null;
        } else {
            newFees.setStandard(standered.getStandard());
            totalFees = standered.getTuitionFee() + standered.getAdmissionFee() + standered.getComputerClassFee() +
                    standered.getExamFees() + standered.getUniformFee() + standered.getTransportBusFee()
                    + standered.getHostelFee() + standered.getBuildingFundFee() + standered.getPracticalFee()+
                    fees.getLateFeeCharges()+standered.getLibraryFees()+standered.getSportFees()+fees.getLateFeeCharges();
            newFees.setTotalFeesAmount(totalFees);
        }
        double discountedFeesAmount =   totalFees-(totalFees*fees.getDiscount()/100);
        newFees.setFeesAmount(fees.getFeesAmount());
        newFees.setDiscountedAmount(discountedFeesAmount);
        if (discountedFeesAmount>fees.getFeesAmount()  ) {

            newFees.setFeesStatus("incomplete");
            newFees.setPendingFeesAmount(newFees.getPendingFeesAmount()-fees.getFeesAmount());
        } else {
            newFees.setFeesStatus("Complete");
        }
        newFees.setStandard(fees.getStandard());
        newFees.setDiscount(fees.getDiscount());
        newFees.setDivision(fees.getDivision());
        newFees.setGstNo(fees.getGstNo());
        newFees.setTransactionId(fees.getTransactionId());
        newFees.setFeesCollectionType(fees.getFeesCollectionType());
        newFees.setRollNo(fees.getRollNo());
        newFees.setStudentName(fees.getStudentName());
        newFees.setFeesType(fees.getFeesType());
        newFees.setRegistrationDate(fees.getRegistrationDate());
        newFees.setMedium(fees.getMedium());
        return feesRepository.save(newFees);
    }

    @Override
    public void deleteFees(Long id) {
        feesRepository.deleteById(id);
    }


}
