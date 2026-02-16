package com.agile.expenseTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ExpenseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in DB
    private Integer expenseId;

    private String description;

    private Integer expenseAmount;

    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @ManyToOne
    @JsonIgnore
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
