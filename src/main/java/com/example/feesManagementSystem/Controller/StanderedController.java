package com.example.feesManagementSystem.Controller;

import com.example.feesManagementSystem.Entity.Standered;
import com.example.feesManagementSystem.Service.StanderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://pjsofttech.in/")
@Controller
public class StanderedController {

    @Autowired
    private StanderedService standeredService;

    @PostMapping("/saveStandered")
    @ResponseBody
    public Standered saveStandered(@RequestBody Standered standered) {
        return standeredService.saveStandered(standered);
    }

    @GetMapping("/getallStandered")
    @ResponseBody
    public List<Standered> getallStandered() {
        return standeredService.getAllStandered();
    }

    @GetMapping("/getStanderedById/{id}")
    @ResponseBody
    public Standered getStanderedById(@PathVariable Long id) {
        return standeredService.getStanderedById(id);
    }

    @PutMapping("/updateStandered/{id}")
    @ResponseBody
    public Standered updateStandered(@PathVariable Long id, @RequestBody Standered standered) {
        return standeredService.updateStandered(id, standered);
    }

    @DeleteMapping("/deleteStanderedById/{id}")
    @ResponseBody
    public Void deleteStandered(@PathVariable Long id) {
        standeredService.deleteStandered(id);
        return null;
    }




    @GetMapping("/getstanderedByName/{standard}")
    @ResponseBody
    public Standered getstandard(@PathVariable String standard){
        return standeredService.findByStandered(standard);
    }

}
