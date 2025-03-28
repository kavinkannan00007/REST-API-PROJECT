package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "recurring_expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private BigDecimal amount;
    private String frequency;

    // ✅ Many-to-One: Each Recurring Expense belongs to one Investment
    @ManyToOne
    @JoinColumn(name = "investment_id", nullable = false)
    private Investment investment;
}
