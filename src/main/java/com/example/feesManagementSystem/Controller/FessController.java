package com.example.feesManagementSystem.Controller;

import com.example.feesManagementSystem.Entity.Fees;
import com.example.feesManagementSystem.Service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@CrossOrigin(origins ="http://pjsofttech.in/")
@Controller
public class FessController {

    @Autowired
    FeesService feesService;

    @PostMapping("/SaveFees")
    @ResponseBody
    public Fees createFees(@RequestBody Fees fees) {
        Fees savedFees = feesService.saveFees(fees);
        return savedFees;
    }

    @GetMapping("getFeesById/{id}")
    @ResponseBody
    public ResponseEntity<Fees> getFeesById(@PathVariable Long id) {
        Fees fees = feesService.getFeesById(id);
        if (fees != null) {
            return new ResponseEntity<>(fees, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllFees")
    @ResponseBody
    public ResponseEntity<List<Fees>> getAllFees() {
        List<Fees> feesList = feesService.getAllFees();
        return new ResponseEntity<>(feesList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFees/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteFees(@PathVariable Long id) {
        feesService.deleteFees(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateFees/{id}")
    @ResponseBody
    public Fees updateFees(@RequestBody Fees fees, @PathVariable Long id){
        return feesService.updateFees(fees,id);
    }


}
