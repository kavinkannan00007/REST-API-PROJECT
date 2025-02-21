package com.example.demo.service;

import com.example.demo.entity.RecurringExpense;
import com.example.demo.repository.RecurringExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringExpenseService {
    @Autowired
    private RecurringExpenseRepository recurringExpenseRepository;

    public RecurringExpense saveExpense(RecurringExpense expense) {
        return recurringExpenseRepository.save(expense);
    }

    public List<RecurringExpense> getAllExpenses() {
        return recurringExpenseRepository.findAll();
    }

    public List<RecurringExpense> getExpensesByCategory(String category) {
        return recurringExpenseRepository.findByCategory(category);
    }
}
