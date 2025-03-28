package com.example.demo.repository;

import com.example.demo.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {
}
