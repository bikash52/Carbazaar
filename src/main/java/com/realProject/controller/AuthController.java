package com.realProject.controller;

import com.realProject.entity.User;
import com.realProject.payload.JWTDto;
import com.realProject.payload.LoginDto;
import com.realProject.repository.UserRepository;
import com.realProject.service.JWTService;
import com.realProject.service.OTPService;
import com.realProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private OTPService otpService;

        @Autowired
        private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
            ){
        ResponseEntity<?> response = userService.createUser(user);
        return response;
    }



    @PostMapping("/signin")
    public ResponseEntity<?> usersignin(
            @RequestBody LoginDto  dto
            ){
        String jwtToken = userService.usersignin(dto);
        if(jwtToken != null){
            JWTDto tokenDto = new JWTDto();
            tokenDto.setToken(jwtToken);
            tokenDto.setTokenType("JWT");

            return new ResponseEntity<>(tokenDto,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Username/Password", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/message")
    public String getMessage(){
        return "Hello";
    }

    @PostMapping("/Content-Manager-signup")
    public ResponseEntity<?> createContentManagerAccount(
            @RequestBody User user
    ){
        ResponseEntity<?> response = userService.createContentManagerAccount(user);
        return response;
    }

    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String mobileNumber) {

        Optional<User> opUser = userRepository.findByMobileNumber(mobileNumber);
        if(opUser.isPresent()){
            String otp = otpService.generateOtp(mobileNumber);
            return ResponseEntity.ok("OTP sent successfully: " + otp); // Replace with SMS service
        }
        return new ResponseEntity<>("User not Found",HttpStatus.UNAUTHORIZED);
    }

    // API to verify OTP
    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String mobileNumber,
            @RequestParam String otp) {

        boolean status = otpService.validateOtp(mobileNumber, otp);
        if(status){
            //generating JWT TOken
            Optional<User> opUser = userRepository.findByMobileNumber(mobileNumber);

            if(opUser.isPresent()){
                String jwtToken = jwtService.generateToken(opUser.get().getUsername());
                return jwtToken;
            }
        }
        return status ? "OTP Validate Successfully" : "Invalid OTP";
    }
}
