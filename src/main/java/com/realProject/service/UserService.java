package com.realProject.service;

import com.realProject.entity.User;
import com.realProject.payload.LoginDto;
import com.realProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;


    public ResponseEntity createUser(User user) {
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent()) {
            return new ResponseEntity<>("Username already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> userByEmailId = userRepository.findByEmailId(user.getEmailId());
        if (userByEmailId.isPresent()) {
            return new ResponseEntity<>("EmailID already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String passencode = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(5));
        user.setPassword(passencode);
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return new ResponseEntity("Created", HttpStatus.CREATED);
    }


    public String usersignin(LoginDto dto) {
        Optional<?> opUser = userRepository.findByUsername(dto.getUsername());
        if (opUser .isPresent()){
            User user = (User) opUser.get();
           if (BCrypt.checkpw(dto.getPassword(),user.getPassword())){
               return  jwtService.generateToken(user.getUsername());
           }
        }
        return null;
    }

    public ResponseEntity<?> createContentManagerAccount(User user) {
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent()) {
            return new ResponseEntity<>("Username already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<User> userByEmailId = userRepository.findByEmailId(user.getEmailId());
        if (userByEmailId.isPresent()) {
            return new ResponseEntity<>("EmailID already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String passencode = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(5));
        user.setPassword(passencode);
        user.setRole("ROLE_CONTENTMANAGER");

        userRepository.save(user);
        return new ResponseEntity("Created", HttpStatus.CREATED);
    }
}

