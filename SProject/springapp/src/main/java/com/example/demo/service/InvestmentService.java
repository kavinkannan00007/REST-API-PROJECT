package com.example.demo.service;

import com.example.demo.entity.Investment;
import com.example.demo.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // ✅ Marks this interface as a Service Bean
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public Page<Investment> getAllInvestments(Pageable pageable) {
        return investmentRepository.findAll(pageable);
    }

    public Optional<Investment> getInvestmentById(Long id) {
        return investmentRepository.findById(id);
    }

    public Investment updateInvestment(Long id, Investment updatedInvestment) {
        return investmentRepository.findById(id)
                .map(existingInvestment -> {
                    existingInvestment.setName(updatedInvestment.getName());
                    existingInvestment.setAmount(updatedInvestment.getAmount());
                    existingInvestment.setInvestmentDate(updatedInvestment.getInvestmentDate());
                    existingInvestment.setType(updatedInvestment.getType());
                    existingInvestment.setInterestRate(updatedInvestment.getInterestRate());
                    return investmentRepository.save(existingInvestment);
                })
                .orElseThrow(() -> new RuntimeException("Investment not found"));
    }

    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }
}
