package com.example.demo.controller;

import com.example.demo.entity.Investment;
import com.example.demo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<Investment> addInvestment(@RequestBody Investment investment) {
        Investment savedInvestment = investmentService.saveInvestment(investment);
        return ResponseEntity.ok(savedInvestment);
    }

    @GetMapping
    public ResponseEntity<Page<Investment>> getAllInvestments(Pageable pageable) {
        return ResponseEntity.ok(investmentService.getAllInvestments(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        return investmentService.getInvestmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment updatedInvestment) {
        Investment investment = investmentService.updateInvestment(id, updatedInvestment);
        return ResponseEntity.ok(investment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}
