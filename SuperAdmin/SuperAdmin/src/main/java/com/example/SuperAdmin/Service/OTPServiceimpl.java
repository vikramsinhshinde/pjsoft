package com.example.SuperAdmin.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPServiceimpl implements OTPService{

    private final Map<String, Integer> otpStorage = new HashMap<>();

    @Autowired
    private EmailsendService emailSendService;

    @Override
    public void generateAndSendOTP(String email) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(999999); // Generate 6-digit OTP
        otpStorage.put(email, otp);

        String message = String.valueOf(otp);
        String subject = "OTP from PJSoftTech";
        emailSendService.sendMail(email, subject, message);
    }


    @Override
    public boolean verifyOTP(String email, int otp) {
        if (!otpStorage.containsKey(email)) {
            return false;
        }
        int storedOtp = otpStorage.get(email);
        if (storedOtp == otp) {
            otpStorage.remove(email);
            return true;
        }
        return false;
    }



}
