package com.agile.expenseTracker.service;

import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.repository.IuserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final IuserRepo userRepo;

    public  UserDetailService (IuserRepo repo){
        this.userRepo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        Users user = userRepo.findByUserName(input);
        if(user == null){
            user =  userRepo.findByEmail(input);
        }
        if(user !=  null){
            return new Userdetails(user);
        }
        throw new UsernameNotFoundException("no username found with this " +  input);
    }


}
