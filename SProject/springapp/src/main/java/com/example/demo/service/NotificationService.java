package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // ✅ Save Notification (Create & Update)
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // ✅ Get All Notifications (List)
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // ✅ Get Notification by ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // ✅ Delete Notification by ID
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    // ✅ Get Paginated & Sorted Notifications
    public Page<Notification> getAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    // ✅ Find Notifications by Type (JPQL)
    public List<Notification> findNotificationsByType(String type) {
        return notificationRepository.findByType(type);
    }
}
