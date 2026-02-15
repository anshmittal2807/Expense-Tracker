package com.agile.expenseTracker.service;


import com.agile.expenseTracker.model.Category;
import com.agile.expenseTracker.repository.IcateoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private IcateoryRepo icateoryRepo;
    public Category saveCategory(Category category){
        if(icateoryRepo.existsBycategoryName(category.getCategoryName())){
            return  icateoryRepo.findBycategoryName(category.getCategoryName());
        }
        return icateoryRepo.save(category);
    }

}
