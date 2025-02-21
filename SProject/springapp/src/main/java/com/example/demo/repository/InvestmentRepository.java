package com.example.demo.repository;

import com.example.demo.entity.Investment;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> 
{
    @Query("SELECT i FROM Investment i WHERE i.type = :type")
    List<Investment> findByType(String type);
}
