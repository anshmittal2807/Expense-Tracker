package com.agile.expenseTracker.repository;

import com.agile.expenseTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IuserRepo extends JpaRepository<Users , Integer> {
}
