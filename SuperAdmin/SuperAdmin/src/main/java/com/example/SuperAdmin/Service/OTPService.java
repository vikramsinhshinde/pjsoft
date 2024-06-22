package com.example.SuperAdmin.Service;

public interface OTPService {

    void generateAndSendOTP(String email);
    boolean verifyOTP(String email, int otp);


}