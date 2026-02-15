package com.agile.expenseTracker.service;

import com.agile.expenseTracker.model.Category;
import com.agile.expenseTracker.model.ExpenseRecord;
import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.repository.IexpenseRecord;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final UserService userService;
    private final IexpenseRecord expenseRecord;
    @Autowired
    private  CategoryService categoryService;

    // Constructor injection
    public ExpenseService(UserService userService, IexpenseRecord expenseRecord) {
        this.userService = userService;
        this.expenseRecord = expenseRecord;
    }

    public ExpenseRecord saveExpense(ExpenseRecord record, Authentication authentication) {
        String userName = authentication.getName();
        Users user = userService.fetchUserByUsernameOrEmail(userName);
        record.setUser(user);

        Category category =  categoryService.saveCategory(record.getCategory());
        record.setCategory(category);

        return expenseRecord.save(record);
    }
}
