package com.agile.expenseTracker.repository;

import com.agile.expenseTracker.model.ExpenseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IexpenseRecord extends JpaRepository<ExpenseRecord , Integer> {

}
