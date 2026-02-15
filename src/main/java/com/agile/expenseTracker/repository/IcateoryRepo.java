package com.agile.expenseTracker.repository;

import com.agile.expenseTracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcateoryRepo extends JpaRepository<Category , Integer> {
}
