package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // ✅ JPQL Query: Get Notifications by Type
    List<Notification> findByType(String type);

    // ✅ Pagination and Sorting
    @SuppressWarnings("null")
    Page<Notification> findAll(Pageable pageable);
}
