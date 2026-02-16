package com.agile.expenseTracker.service;

import com.agile.expenseTracker.model.Category;
import com.agile.expenseTracker.model.ExpenseRecord;
import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.repository.IexpenseRecord;
import io.jsonwebtoken.security.PublicJwkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    private final UserService userService;
    private final IexpenseRecord expenseService;
    @Autowired
    private  CategoryService categoryService;

    // Constructor injection
    public ExpenseService(UserService userService, IexpenseRecord expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    public ExpenseRecord saveExpense(ExpenseRecord record, Authentication authentication) {
        String userName = authentication.getName();
        Users user = userService.fetchUserByUsernameOrEmail(userName);
        record.setUser(user);

        Category category =  categoryService.saveCategory(record.getCategory());
        record.setCategory(category);

        return expenseService.save(record);
    }
    public Map<String, Object> deleteExpense(Integer id) {
        Map<String, Object> map = new HashMap<>();

        Optional<ExpenseRecord> record = expenseService.findById(id);

        if (record.isPresent()) {
            expenseService.delete(record.get());
            map.put("Status", true);
            map.put("Message", "Expense deleted successfully");
        } else {
            map.put("Status", false);
            map.put("Message", "Expense not found");
        }

        return map;
    }

    public Map<String , List> getAllExpenses(String userName){
        Map<String , List> map =  new HashMap<>();
        Users user = userService.fetchUserByUsernameOrEmail(userName);
        List<ExpenseRecord> expenseRecordList =  expenseService.findAllByUser(user);
        map.put("ExpenseRecord" , expenseRecordList);
        return map;
    }

    public ExpenseRecord updateExpense(ExpenseRecord record , Authentication authentication){

        String userName = authentication.getName();
        Users user = userService.fetchUserByUsernameOrEmail(userName);
        record.setUser(user);

        Category category =  categoryService.saveCategory(record.getCategory());
        record.setCategory(category);

        return expenseService.save(record);
    }

        public Map<String , List> fetchExpensePages(Integer pageNumber , Integer pageSize , Authentication authentication){

            Pageable pageable =  PageRequest.of(pageNumber , pageSize);
            Map<String , List> map = new HashMap<>();
            Users user =  userService.fetchUserByUsernameOrEmail(authentication.getName());
            Page<ExpenseRecord> page =  expenseService.findAllByUser(user , pageable);
            map.put("record" , page.getContent());
            System.out.println(map);
            return map;
        }


}
