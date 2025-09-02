package com.realProject.service;

import com.realProject.payload.OTPDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

        private final HashMap<String, OTPDetails> otpStorage = new HashMap<>();
        private static final int OTP_EXPIRY_TIME = 5;

        // Generate OTP and store it
        public String generateOtp(String mobileNumber) {
            String otp = String.format("%06d", new Random().nextInt(1000000));
            OTPDetails otpDetails = new OTPDetails(otp,System.currentTimeMillis());
            otpStorage.put(mobileNumber, otpDetails);
            return otp;  // Ideally, send this OTP via SMS
        }

        // Validate OTP
        public boolean validateOtp(String mobileNumber, String otp)
        {
            OTPDetails otpDetails = otpStorage.get(mobileNumber);

            if(otpDetails == null){
                System.out.println("No OTP Found for "+ mobileNumber);
                return false;
            }
            long currentTime = System.currentTimeMillis();
            long otpTime = otpDetails.getTimeStamp();
            long timeDifferences = TimeUnit.MILLISECONDS.toMinutes(currentTime-otpTime);

            if(timeDifferences > OTP_EXPIRY_TIME){
                otpStorage.remove(mobileNumber);
                return false;
            }
            return otpDetails.getOtp().equals(otp);
        }
    }
