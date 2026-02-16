package com.agile.expenseTracker.controller;

import com.agile.expenseTracker.model.ExpenseRecord;
import com.agile.expenseTracker.model.Users;
import com.agile.expenseTracker.service.ExpenseService;
import com.agile.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private ExpenseService expenseService;
    UserController(UserService userService ){
        this.userService =  userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> signup(@RequestBody Users user) {

        Users savedUser = userService.save(user);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("some error occure", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody Users user){
        String token = userService.verify(user);
        Map<String , String> map =  new HashMap<>();


        if (token != null) {
            map.put("token" , token);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        map.put("error" , "login failed due to invalid creditials");

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/expense")
    public ResponseEntity<ExpenseRecord> saveExpense(@RequestBody ExpenseRecord expense , Authentication authentication){
        return new ResponseEntity<>(expenseService.saveExpense(expense , authentication) ,HttpStatus.OK );
    }

    @PostMapping("/delExpense")
    public ResponseEntity<Map<String , Object>> deleteExpense (@RequestBody Map<String, Integer> map){
        return  new ResponseEntity<>(expenseService.deleteExpense(map.get("id")) , HttpStatus.OK);
    }

    @GetMapping("/expense")
    public ResponseEntity<Map<String , List>> info(Authentication authentication){
        return  new ResponseEntity<>(expenseService.getAllExpenses(authentication.getName()) , HttpStatus.OK);
    }

}
