package com.example.demo.controller;

import com.example.demo.entity.Investment;
import com.example.demo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investments")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public Investment addInvestment(@RequestBody Investment investment) {
        return investmentService.saveInvestment(investment);
    }

    @GetMapping
    public Page<Investment> getAllInvestments(Pageable pageable) {
        return investmentService.getAllInvestments(pageable);
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id) {
        return investmentService.getInvestmentById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment investmentDetails) {
        Optional<Investment> investmentOptional = investmentService.getInvestmentById(id);
        
        if (!investmentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Investment investment = investmentOptional.get();
        investment.setName(investmentDetails.getName());
        investment.setAmount(investmentDetails.getAmount());
        investment.setDate(investmentDetails.getDate());
        investment.setType(investmentDetails.getType());

        Investment updatedInvestment = investmentService.saveInvestment(investment);
        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable Long id) {
        investmentService.deleteInvestment(id);
    }

    // Pagination & Sorting Example: /investments/paginated?page=0&size=5&sort=name,asc
    @GetMapping("/paginated")
    public Page<Investment> getPaginatedInvestments(Pageable pageable) {
        return investmentService.getAllInvestments(pageable);
    }

    // JPQL Example: Find investments by type
    @GetMapping("/by-type/{type}")
    public List<Investment> getInvestmentsByType(@PathVariable String type) {
        return investmentService.findInvestmentsByType(type);
    }
}
