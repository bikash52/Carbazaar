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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "User authentication and registration endpoints")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private OTPService otpService;

        @Autowired
        private JWTService jwtService;

    @Operation(
            summary = "User Registration",
            description = "Register a new user account in the CarBazzar platform"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @Parameter(description = "User registration details", required = true)
            @RequestBody User user
            ){
        ResponseEntity<?> response = userService.createUser(user);
        return response;
    }



    @Operation(
            summary = "User Login",
            description = "Authenticate user and receive JWT token for API access"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login successful, JWT token returned", 
                    content = @Content(schema = @Schema(implementation = JWTDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/signin")
    public ResponseEntity<?> usersignin(
            @Parameter(description = "User login credentials", required = true)
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
