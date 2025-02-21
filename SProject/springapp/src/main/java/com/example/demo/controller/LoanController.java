package com.example.demo.controller;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans") // Ensure consistency in API paths
public class LoanController {
    @Autowired
    private LoanService loanService;

    // ✅ Create Loan
    @PostMapping("/post/loan")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.saveLoan(loan);
        return ResponseEntity.ok(savedLoan);
    }

    // ✅ Get All Loans (Pagination & Sorting)
    @GetMapping("/loan")
    public ResponseEntity<Page<Loan>> getAllLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getAllLoans(pageable));
    }

    // ✅ Get Loan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loanDetails) {
        Optional<Loan> existingLoan = loanService.getLoanById(id);

        if (existingLoan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Loan loan = existingLoan.get();
        loan.setAmount(loanDetails.getAmount());
        loan.setInterestRate(loanDetails.getInterestRate());
        loan.setDuration(loanDetails.getDuration());
        loan.setStatus(loanDetails.getStatus());

        return ResponseEntity.ok(loanService.saveLoan(loan));
    }

    // ✅ Delete Loan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get Paginated & Sorted Loans
    @GetMapping("/paginated")
    public ResponseEntity<Page<Loan>> getPaginatedLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getAllLoans(pageable));
    }

    // ✅ Find Loans by Status (JPQL)
    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable String status) {
        return ResponseEntity.ok(loanService.findLoansByStatus(status));
    }
}
