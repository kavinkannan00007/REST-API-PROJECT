package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // ✅ Create Notification
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // ✅ Get All Notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // ✅ Get Notification by ID
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    // ✅ Update Notification
    public Notification updateNotification(Long id, Notification updatedNotification) {
        Notification existing = getNotificationById(id);
        existing.setMessage(updatedNotification.getMessage());
        existing.setTimestamp(updatedNotification.getTimestamp());
        existing.setType(updatedNotification.getType());
        return notificationRepository.save(existing);
    }

    // ✅ Delete Notification
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    // ✅ Get Notifications by Type (JPQL Query)
    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository.findByType(type);
    }

    // ✅ Get Notifications with Pagination & Sorting
    public Page<Notification> getNotificationsPaged(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }
}
