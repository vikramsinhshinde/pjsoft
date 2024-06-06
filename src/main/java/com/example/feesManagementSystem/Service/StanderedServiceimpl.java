package com.example.feesManagementSystem.Service;

import com.example.feesManagementSystem.Entity.Standered;
import com.example.feesManagementSystem.Repository.StanderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StanderedServiceimpl implements StanderedService{

    @Autowired
    StanderedRepository standeredRepository;


    @Override
    public Standered saveStandered(Standered classStandered) {
        return standeredRepository.save(classStandered);
    }

    @Override
    public List<Standered> getAllStandered() {
        return standeredRepository.findAll();
    }

    @Override
    public Standered getStanderedById(Long id) {
        return standeredRepository.findById(id).get();
    }

    @Override
    public Standered updateStandered(Long id, Standered standered) {
        Standered newStandered=standeredRepository.findById(id).get();
        newStandered.setStandard(standered.getStandard());
        newStandered.setTuitionFee(standered.getTuitionFee());
        newStandered.setAdmissionFee(standered.getAdmissionFee());
        newStandered.setPracticalFee(standered.getPracticalFee());
        newStandered.setComputerClassFee(standered.getComputerClassFee());
        newStandered.setExamFees(standered.getExamFees());
        newStandered.setUniformFee(standered.getUniformFee());
        newStandered.setTransportBusFee(standered.getTransportBusFee());
        newStandered.setHostelFee(standered.getHostelFee());
        newStandered.setBuildingFundFee(standered.getBuildingFundFee());
        return standeredRepository.save(newStandered);
    }

    @Override
    public void deleteStandered(Long id) {
        standeredRepository.deleteById(id);
    }

    @Override
    public Standered findByStandered(String standered) {
        return  standeredRepository.findByStandard(standered);
    }
}
