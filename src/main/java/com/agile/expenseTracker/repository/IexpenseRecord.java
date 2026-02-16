package com.agile.expenseTracker.repository;

import com.agile.expenseTracker.model.ExpenseRecord;
import com.agile.expenseTracker.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IexpenseRecord extends JpaRepository<ExpenseRecord , Integer> {

    List<ExpenseRecord> findAllByUser(Users user);
    Page<ExpenseRecord> findAllByUser(Users users , Pageable pageable);
}
