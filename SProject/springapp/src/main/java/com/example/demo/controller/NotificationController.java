package com.example.demo.controller;

import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications") // Ensuring API path consistency
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // ✅ Create Notification
    @PostMapping("/post")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.saveNotification(notification));
    }

    // ✅ Get All Notifications
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    // ✅ Get Notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update Notification by ID
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Optional<Notification> existingNotification = notificationService.getNotificationById(id);

        if (existingNotification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Notification notification = existingNotification.get();
        notification.setMessage(notificationDetails.getMessage());
        notification.setType(notificationDetails.getType());
        notification.setTimestamp(notificationDetails.getTimestamp());

        return ResponseEntity.ok(notificationService.saveNotification(notification));
    }

    // ✅ Delete Notification by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get Paginated & Sorted Notifications
    @GetMapping("/paginated")
    public ResponseEntity<Page<Notification>> getPaginatedNotifications(Pageable pageable) {
        return ResponseEntity.ok(notificationService.getAllNotifications(pageable));
    }

    // ✅ Find Notifications by Type (JPQL)
    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<Notification>> getNotificationsByType(@PathVariable String type) {
        return ResponseEntity.ok(notificationService.findNotificationsByType(type));
    }
}
