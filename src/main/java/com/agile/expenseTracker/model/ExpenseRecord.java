package com.agile.expenseTracker.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

public class ExpenseRecord {
    @Id
    private Integer expenseId;

    private String description;

    private Integer expenseAmount;

    private Date date;

    @OneToOne
    private  Category category;

    private Users user;

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Integer expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExpenseRecord{" +
                "expenseId=" + expenseId +
                ", description='" + description + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", date=" + date +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
