package com.example.SuperAdmin.Controller;


import com.example.SuperAdmin.Module.EmployeeAdmin;
import com.example.SuperAdmin.Module.FeesManagementAdmin;
import com.example.SuperAdmin.Module.StudentAdmin;
import com.example.SuperAdmin.Repository.SuperAdminRepository;
import com.example.SuperAdmin.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://pjsofttech.in/")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    StudentAdminService studentAdminService;

    @Autowired
    EmployeeAdminService employeeAdminService;

    @Autowired
    FeesManagementAdminService feesManagementAdminService;

    @Autowired
    SuperAdminRepository superAdminRepository;




    @PostMapping("/student/adminLogin")
    @ResponseBody
    public ResponseEntity<String> adminLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");



        String result = loginService.adminLogin(email, password);



        switch (result) {
            case "yes":
                return ResponseEntity.ok("Login successful");
            case "no":
                return ResponseEntity.status(401).body("Incorrect password");
            case "passwords do not match":
                return ResponseEntity.status(400).body("Passwords do not match");
            case "student not found":
                return ResponseEntity.status(404).body("Admin not found");
            default:
                return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/employee/adminLogin")
    @ResponseBody
    public ResponseEntity<String> employeeAdminLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String result = loginService.employeeAdminLogin(email,password);

        switch (result) {
            case "yes":
                return ResponseEntity.ok("Login successful");
            case "no":
                return ResponseEntity.status(401).body("Incorrect password");
            case "passwords do not match":
                return ResponseEntity.status(400).body("Passwords do not match");
            case "student not found":
                return ResponseEntity.status(404).body("Admin not found");
            default:
                return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/fees/adminLogin")
    @ResponseBody
    public ResponseEntity<String> feesManagementLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String result = loginService.feesAdminLogin(email, password);

        switch (result) {
            case "yes":
                return ResponseEntity.ok("Login successful");
            case "no":
                return ResponseEntity.status(401).body("Incorrect password");
            case "passwords do not match":
                return ResponseEntity.status(400).body("Passwords do not match");
            case "student not found":
                return ResponseEntity.status(404).body("Admin not found");
            default:
                return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/enquiry/adminLogin")
    @ResponseBody
    public ResponseEntity<String> enquiryManagementLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String result = loginService.enquiryAdminLogin(email, password);

        switch (result) {
            case "yes":
                return ResponseEntity.ok("Login successful");
            case "no":
                return ResponseEntity.status(401).body("Incorrect password");
            case "passwords do not match":
                return ResponseEntity.status(400).body("Passwords do not match");
            case "student not found":
                return ResponseEntity.status(404).body("Admin not found");
            default:
                return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/SuperAdminLogin")
    @ResponseBody
    public ResponseEntity<String> SuperAdminLogin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String result = loginService.superAadminLogin(email,password);

        switch (result) {
            case "yes":
                return ResponseEntity.ok("Login successful");
            case "no":
                return ResponseEntity.status(401).body("Incorrect password");
            case "passwords do not match":
                return ResponseEntity.status(400).body("Passwords do not match");
            case "student not found":
                return ResponseEntity.status(404).body("Admin not found");
            default:
                return ResponseEntity.status(500).body("Internal server error");
        }
    }



//    @PostMapping("/forgotPassword")
//    public String sendOTP(@RequestParam("email") String email){
//        Random random=new Random(100000);
//        int otp=random.nextInt(999999);
//        String message= String.valueOf(otp);
//        String subject="otp from pjsofttech";
//        emailsendService.sendMail(email,subject, message );
//        return "yes";
//    }

    @Autowired
    private OTPService otpService;

    @PostMapping("/forgotPassword")
    @ResponseBody
    public String sendOTP(@RequestParam("email") String email) {
        otpService.generateAndSendOTP(email);
        return "OTP sent to " + email;
    }

    @PostMapping("/verifyOTP")
    @ResponseBody
    public String verifyOTP(@RequestParam("email") String email, @RequestParam("otp") int otp) {
        boolean isValid = otpService.verifyOTP(email, otp);
        return isValid ? "OTP verified successfully" : "Invalid OTP";
    }

    @PutMapping("/reset-password/studentManagementSystem/Admin")
    @ResponseBody
    public ResponseEntity<StudentAdmin> forgotPassword(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword){
        return new ResponseEntity<>(studentAdminService.updateStudentAdminPassword(email,password,confirmPassword), HttpStatus.OK);
    }

    @PutMapping("/reset-password/employeeManagementSystem/Admin")
    @ResponseBody
    public ResponseEntity<EmployeeAdmin> employeeAdminForgotPassword(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword){
        return new ResponseEntity<>(employeeAdminService.updateAdminPassword(email,password,confirmPassword), HttpStatus.OK);
    }

    @PutMapping("/reset-password/feesManagementSystem/Admin")
    @ResponseBody
    public ResponseEntity<FeesManagementAdmin> feesAdminPassword(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword){
        return new ResponseEntity<>(feesManagementAdminService.updateAdminPassword(email,password,confirmPassword), HttpStatus.OK);
    }
}