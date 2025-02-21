package com.example.demo.repository;

import com.example.demo.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {
    @Query("SELECT r FROM RecurringExpense r WHERE r.category = :category")
    List<RecurringExpense> findByCategory(String category);
}
