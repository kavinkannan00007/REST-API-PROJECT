package com.example.demo.service;

import com.example.demo.entity.Investment;
import com.example.demo.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }

    public List<Investment> findInvestmentsByType(String type) {
        return investmentRepository.findByType(type);
    }
}
