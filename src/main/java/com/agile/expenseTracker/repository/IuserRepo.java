package com.agile.expenseTracker.repository;

import com.agile.expenseTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IuserRepo extends JpaRepository<Users , Integer> {
    public Users findByUserName(String userName);
    public Boolean existsByUserName(String userName);
    public Boolean existsByEmail(String Email);

    public Users findByEmail(String Email);


}
