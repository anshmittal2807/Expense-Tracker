package com.agile.expenseTracker.controller;

import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.repository.IuserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final IuserRepo userRepo;
    private final BCryptPasswordEncoder encoder;


    UserController(IuserRepo userRepo , BCryptPasswordEncoder encoder){
        this.userRepo =  userRepo;

        this.encoder = encoder;
    }

    @PostMapping("/save")
    public ResponseEntity<Users> signup(@RequestBody Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<Users>(userRepo.save(user) , HttpStatus.CREATED);

    }

}
