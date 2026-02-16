package com.agile.expenseTracker.service;

import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.repository.IuserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private IuserRepo repo;
    private final BCryptPasswordEncoder encoder;
    private JwtService jwtService;
    private AuthenticationManager authManager;

    public UserService(BCryptPasswordEncoder encoder, AuthenticationManager authManager, JwtService jwtService, IuserRepo repo) {
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.repo = repo;
    }

    public Users save (Users user){
        if(repo.existsByEmail(user.getEmail()) || repo.existsByUserName(user.getEmail())){
            return repo.findByUserName(user.getUserName());
        }
        user.setPassword(encoder.encode(user.getPassword()));
       Users savedUser =  repo.save(user);
       return savedUser;
    }

    public String verify(Users user){

        Authentication auth =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());

        } else {
            return "failed";

        }
    }

    public Users fetchUserByUsernameOrEmail(String input){
        Users user =  repo.findByUserName(input);
        if(user == null){
            user =  repo.findByEmail(input);
        }
        return user;
    }


}
