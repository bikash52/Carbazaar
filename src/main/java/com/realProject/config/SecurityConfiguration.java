package com.realProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfiguration {
            @Autowired
            private  JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        //h(cd)^2
        http.csrf().disable().cors().disable();

        //haap
       http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

       // http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/signup",
//                        "/api/v1/auth/signin",
//                        "/api/v1/auth/Content-Manager-signup",
//                        "/api/v1/auth/generate-otp",
//                        "/api/v1/auth/verify-otp")
               // .permitAll();
//                .requestMatchers("/api/v1/cars/add/car")
//                .hasRole("CONTENTMANAGER")
//                .anyRequest()
//                .authenticated();
        return http.build();
    }

}
