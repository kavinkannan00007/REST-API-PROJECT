package com.example.demo.repository;

import com.example.demo.entity.Investment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    // ✅ Find investments by status using JPQL
    @Query("SELECT i FROM Investment i WHERE i.status = :status")
    List<Investment> findInvestmentsByStatus(@NonNull String status);

    // ✅ Pagination and sorting
    @NonNull
    Page<Investment> findAll(@NonNull Pageable pageable);

    // ✅ Find investments by Loan ID (Fixed incorrect field reference)
    @Query("SELECT i FROM Investment i WHERE i.loan.id = :loanId")
    @NonNull
    Page<Investment> findByLoanId(@NonNull Long loanId, @NonNull Pageable pageable);

    // ✅ Find investments by Recurring Expense ID (Fixed incorrect field reference)
    @Query("SELECT i FROM Investment i JOIN i.recurringExpenses re WHERE re.id = :expenseId")
    @NonNull
    Page<Investment> findByRecurringExpenseId(@NonNull Long expenseId, @NonNull Pageable pageable);
}
