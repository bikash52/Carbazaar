package com.realProject.service;

// TwilioService disabled - SMS functionality commented out
/*
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSms(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();
    }
}
*/

// Mock TwilioService for development without Twilio
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    public void sendSms(String to, String message) {
        // Mock implementation - just log the SMS instead of sending
        System.out.println("Mock SMS - To: " + to + ", Message: " + message);
    }
}

