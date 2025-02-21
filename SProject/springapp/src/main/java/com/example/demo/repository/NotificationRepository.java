package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // ✅ JPQL Query to find Notifications by Type
    @Query("SELECT n FROM Notification n WHERE n.type = ?1")
    List<Notification> findByType(String type);
}
