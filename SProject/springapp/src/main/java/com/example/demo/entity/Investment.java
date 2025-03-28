package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal amount;
    private LocalDate investmentDate;
    private String type; // Example: Stocks, Bonds, Real Estate
    private Double interestRate;
    private String status; // Example: Active, Completed, Pending

    // ✅ Many-to-One: An Investment is linked to one Loan
    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = true)
    private Loan loan;

    // ✅ One-to-Many: An Investment can have multiple Recurring Expenses
    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecurringExpense> recurringExpenses;
}
