package com.example.demo.service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Page<Loan> getAllLoans(Pageable pageable) {
        return loanRepository.findAll(pageable);
    }

    public Optional<Loan> getLoanById(Long id) {  // FIXED METHOD
        return loanRepository.findById(id);
    }

    public Loan updateLoan(Long id, Loan updatedLoan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    existingLoan.setAmount(updatedLoan.getAmount());
                    existingLoan.setInterestRate(updatedLoan.getInterestRate());
                    existingLoan.setDuration(updatedLoan.getDuration());
                    return loanRepository.save(existingLoan);
                })
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}
