package com.example.demo.controller;

import com.example.demo.entity.RecurringExpense;
import com.example.demo.service.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-expenses")
public class RecurringExpenseController {

    @Autowired
    private RecurringExpenseService expenseService;

    @PostMapping
    public RecurringExpense addExpense(@RequestBody RecurringExpense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<RecurringExpense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/category/{category}")
    public List<RecurringExpense> getExpensesByCategory(@PathVariable String category) {
        return expenseService.getExpensesByCategory(category);
    }
}
