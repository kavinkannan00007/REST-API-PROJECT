package com.example.demo.service;

import com.example.demo.entity.RecurringExpense;
import com.example.demo.repository.RecurringExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecurringExpenseService {

    @Autowired
    private RecurringExpenseRepository expenseRepository;

    public RecurringExpense saveExpense(RecurringExpense expense) {
        return expenseRepository.save(expense);
    }

    public List<RecurringExpense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<RecurringExpense> getExpensesByCategory(String category) {
        return expenseRepository.findAll().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}
