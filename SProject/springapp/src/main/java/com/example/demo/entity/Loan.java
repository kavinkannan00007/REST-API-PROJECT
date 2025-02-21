package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lender;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;

    private double interestRate;

    private int duration; // Add this field

    private String status; // Add this field
}
