package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Module.StudentAdmin;
import com.example.SuperAdmin.Service.StudentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class StudentAdminController {

    @Autowired
    StudentAdminService studentAdminService;

    @PostMapping("/saveStudentAdmin")
    @ResponseBody
    public StudentAdmin saveStudentAdmin(@RequestBody StudentAdmin StudentAdmin){
        return studentAdminService.saveStudentAdmin(StudentAdmin);
    }

    @GetMapping("/getAllStudentAdmin")
    @ResponseBody
    public List<StudentAdmin> getAllStudentAdmin(){
        return studentAdminService.getAllStudentAdmin();
    }

    @GetMapping("/getStudentAdminById/{id}")
    @ResponseBody
    public StudentAdmin getStudentAdminById(@PathVariable  Long id){
        return studentAdminService.getStudentAdmin(id);
    }

    @DeleteMapping("/deleteStudentAdminById/{id}")
    @ResponseBody
    public  void  deleteStudentAdmin(@PathVariable Long id){
        studentAdminService.deleteStudentAdmin(id);
    }

    @PutMapping("/updateStudentAdmin/{id}")
    @ResponseBody
    public StudentAdmin updateStudentAdmin(@RequestBody StudentAdmin StudentAdmin,@PathVariable Long id){
        return studentAdminService.updateStudentAdmin(StudentAdmin, id);
    }

    @PatchMapping("/patchmapping/{id}")
    @ResponseBody
    public StudentAdmin patchStudentAdmin(@RequestBody StudentAdmin StudentAdmin,@PathVariable Long id){
        return studentAdminService.patchStudentAdmin(StudentAdmin, id);
    }

}
